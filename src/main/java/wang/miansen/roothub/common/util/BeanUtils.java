package wang.miansen.roothub.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import wang.miansen.roothub.common.annotation.Conver;
import wang.miansen.roothub.common.annotation.DO2DTO;
import wang.miansen.roothub.common.beans.ConverInfo;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.entity.BaseDO;
import wang.miansen.roothub.common.enums.BaseMasterDataEnum;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.common.enums.ConverType;
import wang.miansen.roothub.common.exception.FatalBeanException;
import wang.miansen.roothub.common.exception.ReflectionException;
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
	 * 当做参数，调用 {@code getById()} 方法获取到对象并设置到指定的目标对象 {@code target} 
	 * 的指定属性 {@code propertyName} 中。
	 * 
	 * @param target 目标对象
	 * @param propertyName 属性名
	 * @param id 指定的 id
	 * @param serviceName service bean name
	 */
	@SuppressWarnings("unchecked")
	public static void idConverObject(Object target, String targetPropertyName, String id, String serviceName)
			throws FatalBeanException {
		try {
			Assert.notNull(target, "Target must not be null");
			Assert.notNull(targetPropertyName, "targetPropertyName must not be null");
			Assert.notNull(id, "Id must not be null");
			Assert.notNull(serviceName, "ServiceName must not be null");

			BaseService<? extends BaseDO, ? extends BaseDTO> service = (BaseService<? extends BaseDO, ? extends BaseDTO>) ApplicationContextUtils
					.getBean(serviceName);
			Object object = service.getById(id);
			Method writeMethod = ReflectionUtils.getWriteMethod(targetPropertyName, target.getClass());
			writeMethod.invoke(target, object);
		} catch (Throwable e) {
			throw new FatalBeanException(
					"Failed idConverObject [target: '" + target.getClass().getSimpleName() + "', targetPropertyName: '"
							+ targetPropertyName + "', id: '" + id + "', serviceName: '" + serviceName + "']",
					e);
		}

	}

	public static void codeConverEnum(Object target, String targetPropertyName, Integer code,
			Class<? extends BaseMasterDataEnum> enumClass) throws FatalBeanException {
		try {
			Assert.notNull(target, "Target must not be null");
			Assert.notNull(targetPropertyName, "targetPropertyName must not be null");
			Assert.notNull(code, "Code must not be null");
			Assert.notNull(enumClass, "Enum class must not be null");

			Method values = enumClass.getMethod("values");
			BaseMasterDataEnum[] baseMasterDataEnums = (BaseMasterDataEnum[]) values.invoke(null, null);
			for (BaseMasterDataEnum baseMasterDataEnum : baseMasterDataEnums) {
				if (Objects.equals(baseMasterDataEnum.getCode(), code)) {
					Method writeMethod = ReflectionUtils.getWriteMethod(targetPropertyName, target.getClass());
					writeMethod.invoke(target, baseMasterDataEnum);
				}
			}
		} catch (Throwable e) {
			throw new FatalBeanException("Could not code conver enum data [target: '"
					+ target.getClass().getSimpleName() + "', targetPropertyName: '" + targetPropertyName + "', code: '"
					+ code + "', enumClass: '" + enumClass.getSimpleName() + "']", e);
		}
	}

	public static void DO2DTO(BaseDO baseDO, BaseDTO baseDTO) throws FatalBeanException {
		try {
			Assert.notNull(baseDO, "BaseDO must not be null");
			Assert.notNull(baseDTO, "BaseDTO must not be null");
			org.springframework.beans.BeanUtils.copyProperties(baseDO, baseDTO);
			List<ConverInfo> converInfoList = getConverDTOField(baseDO);
			for (ConverInfo converInfo : converInfoList) {
				ConverPolicy policy = converInfo.getPolicy();
				Field field = converInfo.getField();
				String fieldName = field.getName();
				// Object fieldValue = field.get(baseDO);
				String[] targets = converInfo.getTargets();
				switch (policy) {
					case ID_CONVER_OBJECT:
						String service = converInfo.getService();
						Assert.notNull(service,
								"The conver policy is ID_CONVER_OBJECT, so service bean name must not be null");
						Method idReadMethod = ReflectionUtils.getReadMethod(fieldName, baseDO.getClass());
						String id = (String) idReadMethod.invoke(baseDO);
						if (StringUtils.notEmpty(id)) {
							for (int i = 0; i < targets.length; i++) {
								String target = targets[i];
								idConverObject(baseDTO, target, id, service);
							}
						}
						break;
					case CODE_CONVER_ENUM:
						Class<? extends BaseMasterDataEnum> enumClass = converInfo.getEnumClass();
						Assert.notNull(enumClass,
								"The conver policy is CODE_CONVER_ENUM, so enum class must not be null");
						Method codeReadMethod = ReflectionUtils.getReadMethod(fieldName, baseDO.getClass());
						Integer code = (Integer) codeReadMethod.invoke(baseDO);
						if (code != null) {
							for (int i = 0; i < targets.length; i++) {
								String target = targets[i];
								codeConverEnum(baseDTO, target, code, enumClass);
							}
						}
					default:
						break;
				}
			}
		} catch (Throwable e) {
			throw new FatalBeanException("Could not DO2DTO [DO: '" + baseDO.getClass().getSimpleName() + "', DTO: '"
					+ baseDTO.getClass().getSimpleName() + "']", e);
		}
	}

	/**
	 * 获取标有 {@link DO2DTO} 注解的字段
	 * @param baseDO
	 * @return List
	 */
	private static List<ConverInfo> getConverDTOField(BaseDO baseDO) {
		List<ConverInfo> converInfoList = new ArrayList<>();
		List<Field> fieldList = ReflectionUtils.getFieldList(baseDO.getClass());
		fieldList.forEach(field -> {
			DO2DTO do2dto = field.getAnnotation(DO2DTO.class);
			if (do2dto != null) {
				converInfoList.add(new ConverInfo(field, ConverType.DO2DTO, do2dto.targets(), do2dto.policy(),
						do2dto.service(), do2dto.enumClass()));
			}
		});
		return converInfoList;
	}

}
