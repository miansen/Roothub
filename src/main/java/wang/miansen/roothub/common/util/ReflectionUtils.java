package wang.miansen.roothub.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.aop.support.AopUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	 * 获取直接父类的泛型
	 * @param clazz 直接父类的 class 对象
	 * @param index 泛型所在位置
	 * @return Class
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
	 * @param clazz 类的 class 对象
	 * @return Field List 集合
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

}
