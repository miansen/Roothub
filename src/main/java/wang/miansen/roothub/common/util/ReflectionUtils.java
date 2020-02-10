package wang.miansen.roothub.common.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.aop.support.AopUtils;

import wang.miansen.roothub.common.exception.ReflectionException;

/**
 * 反射工具类
 * 
 * @author miansen.wang
 * @date 2019-12-29
 * @since 3.0
 */
public final class ReflectionUtils {

	private static final Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

	/**
	 * 获取直接父类的泛型的 Class 对象
	 * <p>注意：如果获取不到将返回 {@code Object.class}
	 * 
	 * @param clazz 指定要获取的 Class 对象
	 * @param index 泛型所在位置
	 * @return 返回直接父类的泛型的 Class 对象，如果获取不到将返回 {@code Object.class}
	 */
	public static Class<?> getSuperClassGenericType(final Class<?> clazz, int index) {
		// 获取直接继承的父类（包含泛型参数）
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			logger.warn(String.format("Warn: %s's superclass not ParameterizedType", clazz.getSimpleName()));
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index > params.length || index < 0) {
			logger.warn(String.format("Warn: Index: %s, Size of %s's Parameterized Type: %s .", index,
					clazz.getSimpleName(), params.length));
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(String.format("Warn: %s not set the actual class on superclass generic parameter",
					clazz.getSimpleName()));
			return Object.class;
		}
		return (Class<?>) params[index];
	}

	/**
	 * 获取指定类的所有字段
	 * <p>此方法会过滤掉被 static 修饰的字段和被 Transient 修饰的字段
	 * 
	 * @param clazz 指定要获取的 Class 对象
	 * @return 返回字段的 List 集合
	 */
	public static List<Field> getFieldList(Class<?> clazz) {
		if (Objects.isNull(clazz) || Object.class.equals(clazz)) {
			return Collections.emptyList();
		}
		// 如果 class 是代理对象，则需要获取原来的 class
		if (AopUtils.isAopProxy(clazz) || AopUtils.isJdkDynamicProxy(clazz) || AopUtils.isCglibProxy(clazz)) {
			clazz = AopUtils.getTargetClass(clazz);
		}
		return Stream.of(clazz.getDeclaredFields()).filter(field -> {
			// 排除被 static 修饰的字段（Field 的 getModifiers() 方法返回 int 类型值表示该字段的修饰符）
			return !Modifier.isStatic(field.getModifiers());
		}).filter(field -> {
			// 排除被 Transient 修饰的字段
			return !Modifier.isTransient(field.getModifiers());
		}).collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * 获取指定字段的 set 方法
	 * 
	 * @param fieldName 指定的字段名
	 * @param clazz 字段所属的类
	 * @return Method
	 * @throws ReflectionException
	 */
	public static Method getWriteMethod(String fieldName, Class<?> clazz) throws ReflectionException {
		try {
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, clazz);
			return propertyDescriptor.getWriteMethod();
		} catch (IntrospectionException e) {
			throw new ReflectionException("Could not get write method", e);
		}
	}

	/**
	 * 获取指定字段的 get 方法
	 * 
	 * @param fieldName 指定的字段名
	 * @param clazz 字段所属的类
	 * @return Method
	 * @throws ReflectionException
	 */
	public static Method getReadMethod(String fieldName, Class<?> clazz) throws ReflectionException {
		try {
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, clazz);
			return propertyDescriptor.getReadMethod();
		} catch (IntrospectionException e) {
			throw new ReflectionException("Could not get read method", e);
		}
	}

}
