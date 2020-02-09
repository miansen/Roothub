package wang.miansen.roothub.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import wang.miansen.roothub.common.annotation.Conver;
import wang.miansen.roothub.common.beans.ConverInfo;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.entity.BaseDO;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.common.enums.ConverType;
import wang.miansen.roothub.common.exception.FatalBeanException;
import wang.miansen.roothub.common.service.BaseService;

/**
 * DO DTO VO 互相转换的工具类
 * 
 * @author miansen.wang
 * @date 2020-01-04
 * @since 3.0
 */
public class BeanUtils {


	public static <T, R> R conver(T t, Class<R> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据指定的 {@code serviceName}，从容器中获取到对应 service 对象，
	 * 前提是 service 对象必须是 {@link BaseService} 的子类。然后将指定的 {@code id} 
	 * 当做参数，调用 {@code getById()} 方法获取到 DTO 对象并设置到指定的目标对象 {@code target} 
	 * 的指定属性 {@code propertyName} 中。
	 * 
	 * @param target 目标对象
	 * @param propertyName 属性名
	 * @param id 指定的 id
	 * @param serviceName service bean name
	 */
	@SuppressWarnings("unchecked")
	public static void id2DTO(Object target, String propertyName, String id, String serviceName) {
		try {
			Assert.notNull(target, "Target must not be null");
			Assert.notNull(propertyName, "PropertyName must not be null");
			Assert.notNull(id, "Id must not be null");
			Assert.notNull(serviceName, "ServiceName must not be null");
			
			BaseService<? extends BaseDO, ? extends BaseDTO> service = (BaseService<? extends BaseDO, ? extends BaseDTO>) ApplicationContextUtils.getBean(serviceName);
			BaseDTO dto = service.getById(id);
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, target.getClass());
			Method writeMethod = propertyDescriptor.getWriteMethod();
			writeMethod.invoke(target, dto);
		} catch (Throwable e) {
			throw new FatalBeanException("Failed id2DTO [target: '" + target.getClass().getSimpleName()
					+ "', propertyName: '" + propertyName + "', id: '" + id + "']", e);
		}

	}

	public static void DO2DTO(BaseDO baseDO, BaseDTO baseDTO) {
		try {
			Assert.notNull(baseDO, "BaseDO must not be null");
			Assert.notNull(baseDTO, "BaseDTO must not be null");
			org.springframework.beans.BeanUtils.copyProperties(baseDO, baseDTO);
			List<ConverInfo> converInfoList = getConverDTOField(baseDO);
			for (ConverInfo converInfo : converInfoList) {
				ConverPolicy strategy = converInfo.getStrategy();
				Field field = converInfo.getField();
				String fieldName = field.getName();
				// Object fieldValue = field.get(baseDO);
				String[] targets = converInfo.getTargets();
				String service = converInfo.getService();
				switch (strategy) {
					case ID2DTO:
						Assert.notNull(service, "The ConverStrategy is ID2DTO, so service bean name must not be null");
						PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, baseDO.getClass());
						Method readMethod = propertyDescriptor.getReadMethod();
						String id = (String) readMethod.invoke(baseDO);
						if (StringUtils.notEmpty(id)) {
							for (int i = 0; i < targets.length; i++) {
								String target = targets[i];
								id2DTO(baseDTO, target, id, service);
							}
						}
						break;
					default:
						break;
				}
			}
		} catch (Throwable e) {
			throw new FatalBeanException("Failed DO2DTO [baseDO: '" + baseDO.getClass().getSimpleName()
					+ "', baseDTO: '" + baseDTO.getClass().getSimpleName() + "']", e);
		}
	}

	/**
	 * 获取标有 {@link Conver} 注解并且 ConverType 为 DTO 的描述信息
	 * @param baseDO
	 * @return List
	 */
	private static List<ConverInfo> getConverDTOField(BaseDO baseDO) {
		List<ConverInfo> converInfoList = new ArrayList<>();
		List<Field> fieldList = ReflectionUtils.getFieldList(baseDO.getClass());
		fieldList.forEach(field -> {
			Conver conver = field.getAnnotation(Conver.class);
			if (conver != null) {
				ConverType type = conver.type();
				if (ConverType.DTO == type) {
					converInfoList.add(new ConverInfo(field, conver.targets(), ConverType.DTO, conver.policy(),
							conver.service()));
				}
			}
		});
		return converInfoList;
	}

}
