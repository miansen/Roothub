package wang.miansen.roothub.common.util;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeansException;

import wang.miansen.roothub.common.annotation.Conver;
import wang.miansen.roothub.common.annotation.DO2DTO;
import wang.miansen.roothub.common.annotation.DTO2DO;
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
	 * @param targetPropertyName 目标对象的属性名
	 * @param id id 值
	 * @param serviceBeanName service bean name
	 */
	@SuppressWarnings("unchecked")
	public static void idConverDTO(Object target, String targetPropertyName, String id, String serviceBeanName)
			throws FatalBeanException {
		try {
			Assert.notNull(target, "Target must not be null");
			Assert.notNull(targetPropertyName, "TargetPropertyName must not be null");
			Assert.notNull(id, "Id must not be null");
			Assert.notNull(serviceBeanName, "Service bean name must not be null");

			BaseService<? extends BaseDO, ? extends BaseDTO> service = (BaseService<? extends BaseDO, ? extends BaseDTO>) ApplicationContextUtils
					.getBean(serviceBeanName);
			Object object = service.getById(id);
			Method writeMethod = ReflectionUtils.getWriteMethod(targetPropertyName, target.getClass());
			writeMethod.invoke(target, object);
		} catch (Throwable e) {
			throw new FatalBeanException("Could not id conver dto [target: '" + target.getClass().getSimpleName()
					+ "', targetPropertyName: '" + targetPropertyName + "', id: '" + id + "', serviceBeanName: '"
					+ serviceBeanName + "']", e);
		}

	}

	public static void DTOConverId(Object target, String targetPropertyName, BaseDTO dto) throws FatalBeanException {
		try {
			Assert.notNull(target, "Target must not be null");
			Assert.notNull(targetPropertyName, "Target property name must not be null");
			Assert.notNull(dto, "DTO must not be null");

			// Method readMethod = ReflectionUtils.getReadMethod(targetPropertyName, dto.getClass());
			// Object id = readMethod.invoke(dto);
			Method writeMethod = ReflectionUtils.getWriteMethod(targetPropertyName, target.getClass());
			writeMethod.invoke(target, dto.getPrimaryKey());
		} catch (Throwable e) {
			throw new FatalBeanException("Could not dto conver id [target: '" + target.getClass().getSimpleName()
					+ "', targetPropertyName: '" + targetPropertyName + "', DTO: '" + dto.getClass().getSimpleName()
					+ "]", e);
		}
	}

	public static void codeConverEnum(Object target, String targetPropertyName, Integer code,
			Class<? extends BaseMasterDataEnum> enumClass) throws FatalBeanException {
		try {
			Assert.notNull(target, "Target must not be null");
			Assert.notNull(targetPropertyName, "target property name must not be null");
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
			throw new FatalBeanException("Could not code conver enum [target: '" + target.getClass().getSimpleName()
					+ "', targetPropertyName: '" + targetPropertyName + "', code: '" + code + "', enumClass: '"
					+ enumClass.getSimpleName() + "']", e);
		}
	}

	public static void enumConverCode(Object target, String targetPropertyName, BaseMasterDataEnum baseMasterDataEnum)
			throws FatalBeanException {
		try {
			Assert.notNull(target, "Target must not be null");
			Assert.notNull(targetPropertyName, "Target property name must not be null");
			Assert.notNull(baseMasterDataEnum, "Enum must not be null");

			Method writeMethod = ReflectionUtils.getWriteMethod(targetPropertyName, target.getClass());
			writeMethod.invoke(target, baseMasterDataEnum.getCode());
		} catch (Throwable e) {
			throw new FatalBeanException("Could not enum conver code [target: '" + target.getClass().getSimpleName()
					+ "', targetPropertyName: '" + targetPropertyName + "', enum: '"
					+ baseMasterDataEnum.getClass().getSimpleName() + "']", e);
		}
	}

	public static void DO2DTO(BaseDO baseDO, BaseDTO baseDTO) throws FatalBeanException {
		try {
			Assert.notNull(baseDO, "BaseDO must not be null");
			Assert.notNull(baseDTO, "BaseDTO must not be null");

			org.springframework.beans.BeanUtils.copyProperties(baseDO, baseDTO);
			List<ConverInfo> converInfoList = getConverDTOField(baseDO.getClass(), DO2DTO.class);
			for (ConverInfo converInfo : converInfoList) {
				ConverPolicy policy = converInfo.getPolicy();
				Field field = converInfo.getField();
				String fieldName = field.getName();
				String[] targets = converInfo.getTargets();
				switch (policy) {
					case ID_CONVER_DTO:
						String service = converInfo.getService();
						Assert.notNull(service,
								"The conver policy is ID_CONVER_DTO, so service bean name must not be null");
						Method idReadMethod = ReflectionUtils.getReadMethod(fieldName, baseDO.getClass());
						String id = (String) idReadMethod.invoke(baseDO);
						if (StringUtils.notEmpty(id)) {
							for (int i = 0; i < targets.length; i++) {
								String target = targets[i];
								idConverDTO(baseDTO, target, id, service);
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

	public static void DTO2DO(BaseDTO baseDTO, BaseDO baseDO) throws FatalBeanException {
		try {
			Assert.notNull(baseDTO, "BaseDTO must not be null");
			Assert.notNull(baseDO, "BaseDO must not be null");

			org.springframework.beans.BeanUtils.copyProperties(baseDTO, baseDO);
			List<ConverInfo> converInfoList = getConverDTOField(baseDTO.getClass(), DTO2DO.class);
			for (ConverInfo converInfo : converInfoList) {
				ConverPolicy policy = converInfo.getPolicy();
				Field field = converInfo.getField();
				String fieldName = field.getName();
				String[] targets = converInfo.getTargets();
				switch (policy) {
					case DTO_CONVER_ID:
						Method dtoReadMethod = ReflectionUtils.getReadMethod(fieldName, baseDTO.getClass());
						BaseDTO dto = (BaseDTO) dtoReadMethod.invoke(baseDTO);
						if (dto != null) {
							for (int i = 0; i < targets.length; i++) {
								String target = targets[i];
								DTOConverId(baseDO, target, dto);
							}
						}
						break;
					case ENUM_CONVER_CODE:
						Method enumReadMethod = ReflectionUtils.getReadMethod(fieldName, baseDTO.getClass());
						BaseMasterDataEnum baseMasterDataEnum = (BaseMasterDataEnum) enumReadMethod.invoke(baseDTO);
						if (baseMasterDataEnum != null) {
							for (int i = 0; i < targets.length; i++) {
								String target = targets[i];
								enumConverCode(baseDO, target, baseMasterDataEnum);
							}
						}
						break;
					default:
						break;
				}
			}
		} catch (Throwable e) {
			throw new FatalBeanException("Could not DTO2DO [DTO: '" + baseDTO.getClass().getSimpleName() + "', DO: '"
					+ baseDO.getClass().getSimpleName() + "']", e);
		}
	}

	/**
	 * 获取类中标有指定注解的字段，并包装成 {@link ConverInfo} 对象返回。
	 * @param clazz 字段所属的类
	 * @param annotationClass 指定的注解
	 * @return {@link ConverInfo} List
	 */
	private static List<ConverInfo> getConverDTOField(Class<?> clazz, Class<? extends Annotation> annotationClass) {
		List<ConverInfo> converInfoList = new ArrayList<>();
		List<Field> fieldList = ReflectionUtils.getFieldList(clazz);
		fieldList.forEach(field -> {
			Annotation annotation = field.getAnnotation(annotationClass);
			if (annotation != null) {
				if (annotation instanceof DO2DTO) {
					DO2DTO do2dto = (DO2DTO) annotation;
					converInfoList.add(new ConverInfo(field, ConverType.DO2DTO, do2dto.targets(), do2dto.policy(),
							do2dto.service(), do2dto.enumClass()));
				}
				if (annotation instanceof DTO2DO) {
					DTO2DO dto2do = (DTO2DO) annotation;
					converInfoList.add(new ConverInfo(field, ConverType.DTO2DO, dto2do.targets(), dto2do.policy()));
				}
			}
		});
		return converInfoList;
	}

}
