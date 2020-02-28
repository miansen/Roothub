package wang.miansen.roothub.common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import wang.miansen.roothub.common.annotation.DO2DTO;
import wang.miansen.roothub.common.annotation.DTO2DO;
import wang.miansen.roothub.common.annotation.DTO2VO;
import wang.miansen.roothub.common.annotation.VO2DTO;
import wang.miansen.roothub.common.beans.ConverInfo;
import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.entity.BaseDO;
import wang.miansen.roothub.common.enums.BaseMasterDataEnum;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.common.enums.ConverType;
import wang.miansen.roothub.common.exception.FatalBeanException;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.common.vo.BaseVO;
import wang.miansen.roothub.modules.comment.dto.CommentDTO;
import wang.miansen.roothub.modules.comment.vo.CommentVO;
import wang.miansen.roothub.modules.node.dto.NodeDTO;
import wang.miansen.roothub.modules.node.model.Node;
import wang.miansen.roothub.modules.node.vo.NodeVO;
import wang.miansen.roothub.modules.user.dto.UserRoleRelDTO;

/**
 * DO DTO VO 互相转换的工具类
 * 
 * @author miansen.wang
 * @date 2020-01-04
 * @since 3.0
 */
public class BeanUtils {

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
	 * @throws FatalBeanException
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

	/**
	 * 编码转枚举
	 * 
	 * @param target 目标对象
	 * @param targetPropertyName 目标对象的属性名
	 * @param code 编码
	 * @param enumClass 枚举 Class
	 * @throws FatalBeanException
	 */
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

	/**
	 * 枚举转编码
	 * 
	 * @param target 目标对象
	 * @param targetPropertyName 目标对象的属性名
	 * @param baseMasterDataEnum 枚举对象
	 * @throws FatalBeanException
	 */
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

	/**
	 * 说明转枚举
	 * 
	 * @param target 目标对象
	 * @param targetPropertyName 目标对象的属性名
	 * @param desc 说明
	 * @param enumClass 枚举 Class
	 * @throws FatalBeanException
	 */
	public static void descConverEnum(Object target, String targetPropertyName, String desc,
			Class<? extends BaseMasterDataEnum> enumClass) throws FatalBeanException {
		try {
			Assert.notNull(target, "Target must not be null");
			Assert.notNull(targetPropertyName, "target property name must not be null");
			Assert.notNull(desc, "Desc must not be null");
			Assert.notNull(enumClass, "Enum class must not be null");

			Method values = enumClass.getMethod("values");
			BaseMasterDataEnum[] baseMasterDataEnums = (BaseMasterDataEnum[]) values.invoke(null, null);
			for (BaseMasterDataEnum baseMasterDataEnum : baseMasterDataEnums) {
				if (Objects.equals(baseMasterDataEnum.getDesc(), desc)) {
					Method writeMethod = ReflectionUtils.getWriteMethod(targetPropertyName, target.getClass());
					writeMethod.invoke(target, baseMasterDataEnum);
				}
			}
		} catch (Throwable e) {
			throw new FatalBeanException("Could not desc conver enum [target: '" + target.getClass().getSimpleName()
					+ "', targetPropertyName: '" + targetPropertyName + "', desc: '" + desc + "', enumClass: '"
					+ enumClass.getSimpleName() + "']", e);
		}
	}

	/**
	 * 枚举转说明
	 * 
	 * @param target 目标对象
	 * @param targetPropertyName 目标对象的属性名
	 * @param baseMasterDataEnum 枚举对象
	 * @throws FatalBeanException
	 */
	public static void enumConverDesc(Object target, String targetPropertyName, BaseMasterDataEnum baseMasterDataEnum)
			throws FatalBeanException {
		try {
			Assert.notNull(target, "Target must not be null");
			Assert.notNull(targetPropertyName, "Target property name must not be null");
			Assert.notNull(baseMasterDataEnum, "Enum must not be null");

			Method writeMethod = ReflectionUtils.getWriteMethod(targetPropertyName, target.getClass());
			writeMethod.invoke(target, baseMasterDataEnum.getDesc());
		} catch (Throwable e) {
			throw new FatalBeanException("Could not enum conver desc [target: '" + target.getClass().getSimpleName()
					+ "', targetPropertyName: '" + targetPropertyName + "', enum: '"
					+ baseMasterDataEnum.getClass().getSimpleName() + "']", e);
		}
	}

	/**
	 * 时间转字符串
	 * 
	 * @param target 目标对象
	 * @param targetPropertyName 目标对象的属性名
	 * @param date 时间对象
	 * @throws FatalBeanException
	 */
	public static void dateConverString(Object target, String targetPropertyName, Date date) throws FatalBeanException {
		try {
			Assert.notNull(target, "Target must not be null");
			Assert.notNull(targetPropertyName, "Target property name must not be null");
			Assert.notNull(date, "Date must not be null");

			Method writeMethod = ReflectionUtils.getWriteMethod(targetPropertyName, target.getClass());
			writeMethod.invoke(target, DateUtils.formatDateTime(date));
		} catch (Throwable e) {
			throw new FatalBeanException("Could not date conver string [target: '" + target.getClass().getSimpleName()
					+ "', targetPropertyName: '" + targetPropertyName + "', date: '" + date + "']", e);
		}
	}

	/**
	 * 字符串转时间
	 * 
	 * @param target 目标对象
	 * @param targetPropertyName 目标对象的属性名
	 * @param dateString 时间字符串
	 * @throws FatalBeanException
	 */
	public static void stringConverDate(Object target, String targetPropertyName, String dateString)
			throws FatalBeanException {
		try {
			Assert.notNull(target, "Target must not be null");
			Assert.notNull(targetPropertyName, "Target property name must not be null");
			Assert.notNull(dateString, "Date string must not be null");

			Method writeMethod = ReflectionUtils.getWriteMethod(targetPropertyName, target.getClass());
			writeMethod.invoke(target, DateUtils.string2Date(dateString, DateUtils.FORMAT_DATETIME));
		} catch (Throwable e) {
			throw new FatalBeanException(
					"Could not string conver date [target: '" + target.getClass().getSimpleName()
							+ "', targetPropertyName: '" + targetPropertyName + "', dateString: '" + dateString + "']",
					e);
		}
	}

	/**
	 * 属性复制
	 * 
	 * @param source 来源对象
	 * @param target 目标对象
	 * @param property 属性名
	 * @throws FatalBeanException
	 */
	public static void copyPropertie(Object source, Object target, String property) throws FatalBeanException {
		try {
			Assert.notNull(source, "Source must not be null");
			Assert.notNull(target, "Target must not be null");
			Assert.notNull(property, "Property must not be null");

			Method readMethod = ReflectionUtils.getReadMethod(property, source.getClass());
			Object object = readMethod.invoke(source);
			Method writeMethod = ReflectionUtils.getWriteMethod(property, target.getClass());
			writeMethod.invoke(target, object);
		} catch (Throwable e) {
			throw new FatalBeanException(
					"Could not copy property '" + target.getClass().getSimpleName() + "' from source to target", e);
		}
	}

	/**
	 * DO 转 DTO
	 * 
	 * @param baseDO
	 * @param baseDTO
	 * @throws FatalBeanException
	 */
	public static BaseDTO DO2DTO(BaseDO baseDO, BaseDTO baseDTO) throws FatalBeanException {
		try {
			if (baseDO == null) {
				return null;
			}
			Assert.notNull(baseDTO, "BaseDTO must not be null");

			org.springframework.beans.BeanUtils.copyProperties(baseDO, baseDTO);
			List<ConverInfo> converInfoList = getConverInfoList(baseDO.getClass(), DO2DTO.class);
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
								String targetPropertyName = targets[i];
								idConverDTO(baseDTO, targetPropertyName, id, service);
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
								String targetPropertyName = targets[i];
								codeConverEnum(baseDTO, targetPropertyName, code, enumClass);
							}
						}
						break;
					default:
						break;
				}
			}
		} catch (Throwable e) {
			throw new FatalBeanException("Could not DO2DTO [DO: '" + baseDO.getClass().getSimpleName() + "', DTO: '"
					+ baseDTO.getClass().getSimpleName() + "']", e);
		}
		return baseDTO;
	}

	public static BaseDTO DO2DTO(BaseDO baseDO, Class<? extends BaseDTO> dtoClass) throws FatalBeanException {
		try {
			return DO2DTO(baseDO, dtoClass.newInstance());
		} catch (Throwable e) {
			throw new FatalBeanException("Could not DO2DTO [DO: '" + baseDO.getClass().getSimpleName() + "', DTO: '"
					+ dtoClass.getSimpleName() + "']", e);
		}
	}
	/**
	 * DTO 转 DO
	 * 
	 * @param baseDTO
	 * @param baseDO
	 * @throws FatalBeanException
	 */
	public static BaseDO DTO2DO(BaseDTO baseDTO, BaseDO baseDO) throws FatalBeanException {
		try {
			if (baseDTO == null) {
				return null;
			}
			// Assert.notNull(baseDTO, "BaseDTO must not be null");
			Assert.notNull(baseDO, "BaseDO must not be null");

			org.springframework.beans.BeanUtils.copyProperties(baseDTO, baseDO);
			List<ConverInfo> converInfoList = getConverInfoList(baseDTO.getClass(), DTO2DO.class);
			for (ConverInfo converInfo : converInfoList) {
				ConverPolicy policy = converInfo.getPolicy();
				Field field = converInfo.getField();
				String fieldName = field.getName();
				String[] targets = converInfo.getTargets();
				String[] sources = converInfo.getSources();
				switch (policy) {
					case COPY_PROPERTIES:
						Method sourceReadMethod = ReflectionUtils.getReadMethod(fieldName, baseDTO.getClass());
						Object source = sourceReadMethod.invoke(baseDTO);
						if (source != null) {
							for (int i = 0; i < targets.length; i++) {
								String targetPropertyName = targets[i];
								// 特别处理
								if (sources.length > 0) {
									Object value = doExpression(sources[i], baseDTO);
									Method writeMethod = ReflectionUtils.getWriteMethod(targetPropertyName,
											baseDO.getClass());
									writeMethod.invoke(baseDO, value);
								} else {
									copyPropertie(source, baseDO, targetPropertyName);
								}
							}
						}
						break;
					case ENUM_CONVER_CODE:
						Method enumReadMethod = ReflectionUtils.getReadMethod(fieldName, baseDTO.getClass());
						BaseMasterDataEnum baseMasterDataEnum = (BaseMasterDataEnum) enumReadMethod.invoke(baseDTO);
						if (baseMasterDataEnum != null) {
							for (int i = 0; i < targets.length; i++) {
								String targetPropertyName = targets[i];
								enumConverCode(baseDO, targetPropertyName, baseMasterDataEnum);
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
		return baseDO;
	}

	public static BaseDO DTO2DO(BaseDTO baseDTO, Class<? extends BaseDO> doClass) throws FatalBeanException {
		try {
			return DTO2DO(baseDTO, doClass.newInstance());
		} catch (Throwable e) {
			throw new FatalBeanException("Could not DTO2DO [DTO: '" + baseDTO.getClass().getSimpleName() + "', DO: '"
					+ doClass.getSimpleName() + "']", e);
		}
	}
	
	/**
	 * DTO 转 VO
	 * 
	 * @param baseDTO
	 * @param baseVO
	 * @throws FatalBeanException
	 */
	public static BaseVO DTO2VO(BaseDTO baseDTO, BaseVO baseVO) throws FatalBeanException {
		try {
			if (baseDTO == null) {
				return null;
			}
			Assert.notNull(baseVO, "BaseVO must not be null");

			org.springframework.beans.BeanUtils.copyProperties(baseDTO, baseVO);
			List<ConverInfo> converInfoList = getConverInfoList(baseDTO.getClass(), DTO2VO.class);
			for (ConverInfo converInfo : converInfoList) {
				ConverPolicy policy = converInfo.getPolicy();
				Field field = converInfo.getField();
				String fieldName = field.getName();
				String[] targets = converInfo.getTargets();
				String[] sources = converInfo.getSources();
				switch (policy) {
					case COPY_PROPERTIES:
						Method sourceReadMethod = ReflectionUtils.getReadMethod(fieldName, baseDTO.getClass());
						Object source = sourceReadMethod.invoke(baseDTO);
						if (source != null) {
							for (int i = 0; i < targets.length; i++) {
								String targetPropertyName = targets[i];
								if (sources.length > 0) {
									Object value = doExpression(sources[i], baseDTO);
									Method writeMethod = ReflectionUtils.getWriteMethod(targetPropertyName,
											baseVO.getClass());
									writeMethod.invoke(baseVO, value);
								} else {
									copyPropertie(source, baseVO, targetPropertyName);
								}
							}
						}
						break;
					case ENUM_CONVER_DESC:
						Method enumReadMethod = ReflectionUtils.getReadMethod(fieldName, baseDTO.getClass());
						BaseMasterDataEnum baseMasterDataEnum = (BaseMasterDataEnum) enumReadMethod.invoke(baseDTO);
						if (baseMasterDataEnum != null) {
							for (int i = 0; i < targets.length; i++) {
								String targetPropertyName = targets[i];
								enumConverDesc(baseVO, targetPropertyName, baseMasterDataEnum);
							}
						}
						break;
					case DATE_CONVER_STRING:
						Method dateReadMethod = ReflectionUtils.getReadMethod(fieldName, baseDTO.getClass());
						Date date = (Date) dateReadMethod.invoke(baseDTO);
						if (date != null) {
							for (int i = 0; i < targets.length; i++) {
								String targetPropertyName = targets[i];
								dateConverString(baseVO, targetPropertyName, date);
							}
						}
						break;
					default:
						break;
				}
			}
		} catch (Throwable e) {
			throw new FatalBeanException("Could not DTO2VO [DTO: '" + baseDTO.getClass().getSimpleName() + "', VO: '"
					+ baseVO.getClass().getSimpleName() + "']", e);
		}
		return baseVO;
	}

	public static BaseVO DTO2VO(BaseDTO baseDTO, Class<? extends BaseVO> voClass) throws FatalBeanException {
		try {
			return DTO2VO(baseDTO, voClass.newInstance());
		} catch (Throwable e) {
			throw new FatalBeanException("Could not DTO2VO [DTO: '" + baseDTO.getClass().getSimpleName() + "', VO: '"
					+ voClass.getSimpleName() + "']", e);
		}
		
	}
	
	/**
	 * VO 转 DTO
	 * 
	 * @param baseVO
	 * @param baseDTO
	 * @throws FatalBeanException
	 */
	public static BaseDTO VO2DTO(BaseVO baseVO, BaseDTO baseDTO) throws FatalBeanException {
		try {
			if (baseVO == null) {
				return null;
			}
			Assert.notNull(baseDTO, "BaseDTO must not be null");

			org.springframework.beans.BeanUtils.copyProperties(baseVO, baseDTO);
			List<ConverInfo> converInfoList = getConverInfoList(baseVO.getClass(), VO2DTO.class);
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
						Method idReadMethod = ReflectionUtils.getReadMethod(fieldName, baseVO.getClass());
						String id = (String) idReadMethod.invoke(baseVO);
						if (StringUtils.notEmpty(id)) {
							for (int i = 0; i < targets.length; i++) {
								String targetPropertyName = targets[i];
								idConverDTO(baseDTO, targetPropertyName, id, service);
							}
						}
						break;
					case DESC_CONVER_ENUM:
						Class<? extends BaseMasterDataEnum> enumClass = converInfo.getEnumClass();
						Assert.notNull(enumClass,
								"The conver policy is DESC_CONVER_ENUM, so enum class must not be null");
						Method descReadMethod = ReflectionUtils.getReadMethod(fieldName, baseVO.getClass());
						String desc = (String) descReadMethod.invoke(baseVO);
						if (StringUtils.notEmpty(desc)) {
							for (int i = 0; i < targets.length; i++) {
								String targetPropertyName = targets[i];
								descConverEnum(baseDTO, targetPropertyName, desc, enumClass);
							}
						}
						break;
					case STRING_CONVER_DATE:
						Method dateStringReadMethod = ReflectionUtils.getReadMethod(fieldName, baseVO.getClass());
						String dateString = (String) dateStringReadMethod.invoke(baseVO);
						if (StringUtils.notEmpty(dateString)) {
							for (int i = 0; i < targets.length; i++) {
								String targetPropertyName = targets[i];
								stringConverDate(baseDTO, targetPropertyName, dateString);
							}
						}
						break;
					default:
						break;
				}
			}
		} catch (Throwable e) {
			throw new FatalBeanException("Could not VO2DTO [VO: '" + baseVO.getClass().getSimpleName() + "', DTO: '"
					+ baseDTO.getClass().getSimpleName() + "']", e);
		}
		return baseDTO;
	}
	
	public static BaseDTO VO2DTO(BaseVO baseVO, Class<? extends BaseDTO> dtoClass) throws FatalBeanException {
		try {
			return VO2DTO(baseVO, dtoClass.newInstance());
		} catch (Throwable e) {
			throw new FatalBeanException("Could not VO2DTO [VO: '" + baseVO.getClass().getSimpleName() + "', DTO: '"
					+ dtoClass.getSimpleName() + "']", e);
		}
	}

	public static <T> Page<T> DTOPage2VOPage(Page<? extends BaseDTO> DTOPage, Class<T> clazz)
			throws FatalBeanException {
		List<? extends BaseDTO> list = DTOPage.getList();
		list.stream().map(dto -> {
			BaseVO vo = null;
			try {
				vo = (BaseVO) clazz.newInstance();
			} catch (Throwable e) {
				throw new FatalBeanException("Could not DTOPage2VOPage [DTO Page: '"
						+ DTOPage.getClass().getSimpleName() + "', VO Class: '" + clazz.getSimpleName() + "']", e);
			}
			DTO2VO(dto, vo);
			return vo;
		}).collect(Collectors.toList());
		return new Page<T>((List<T>) list, DTOPage.getPageNumber(), DTOPage.getPageSize(), DTOPage.getTotalRow());
	}

	/**
	 * 处理表达式
	 * <p>支持 {@code object.object.object...} 的形式，前提是要有对应的 {@code get} 方法
	 * 
	 * @param expression 表达式
	 * @param Object 表达式所属的对象
	 * @return Object
	 * @throws Exception
	 */
	private static Object doExpression(String expression, Object object) throws Exception {
		Assert.notNull(expression, "Expression must not be null");
		Assert.notNull(object, "Object must not be null");

		Object value = null;
		Object next = object;
		String[] segments = expression.split("\\.");
		if (segments.length == 1) {
			Method readMethod = ReflectionUtils.getReadMethod(segments[0], next.getClass());
			value = readMethod.invoke(object);
		} else {
			for (int i = 0; i < segments.length - 1; i++) {
				Method readMethod = ReflectionUtils.getReadMethod(segments[i], next.getClass());
				next = readMethod.invoke(next);
				value = doExpression(segments[i + 1], next);
			}
		}
		return value;
	}

	/**
	 * 获取类中标有指定注解的字段，并包装成 {@link ConverInfo} 对象返回。
	 * 
	 * @param clazz 字段所属的类
	 * @param annotationClass 指定的注解
	 * @return {@link ConverInfo} List
	 */
	private static List<ConverInfo> getConverInfoList(Class<?> clazz, Class<? extends Annotation> annotationClass) {
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
					ConverInfo converInfo = new ConverInfo(field, ConverType.DTO2DO, dto2do.targets(), dto2do.policy());
					converInfo.setSources(dto2do.sources());
					converInfoList.add(converInfo);
				}
				if (annotation instanceof DTO2VO) {
					DTO2VO dto2vo = (DTO2VO) annotation;
					ConverInfo converInfo = new ConverInfo(field, ConverType.DTO2VO, dto2vo.targets(), dto2vo.policy());
					converInfo.setSources(dto2vo.sources());
					converInfoList.add(converInfo);
				}
				if (annotation instanceof VO2DTO) {
					VO2DTO vo2dto = (VO2DTO) annotation;
					converInfoList.add(new ConverInfo(field, ConverType.VO2DTO, vo2dto.targets(), vo2dto.policy(),
							vo2dto.service(), vo2dto.enumClass()));
				}
			}
		});
		return converInfoList;
	}

	public static Object getSource(String expression) {
		return null;
	}
	
	public static void main(String[] args) {
		UserRoleRelDTO dto1 = new UserRoleRelDTO();
		dto1.setRoleId("1");
		UserRoleRelDTO dto2 = new UserRoleRelDTO();
		dto2.setRoleId("2");
		UserRoleRelDTO dto3 = new UserRoleRelDTO();
		dto3.setRoleId("3");
		
		List<UserRoleRelDTO> userRoleRelDTOList = new ArrayList<>();
		userRoleRelDTOList.add(dto1);
		userRoleRelDTOList.add(dto2);
		userRoleRelDTOList.add(dto3);
		
		List<String> roleIds = userRoleRelDTOList.stream().map(dto -> dto.getRoleId()).collect(Collectors.toList());
		roleIds.forEach(System.out::println);
	}

}
