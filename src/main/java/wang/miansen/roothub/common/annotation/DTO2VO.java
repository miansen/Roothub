package wang.miansen.roothub.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import wang.miansen.roothub.common.enums.ConverPolicy;

/**
 * DTO 转 VO 注解
 * @author miansen.wang
 * @date 2020-02-11
 * @since 3.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DTO2VO {

	/**
	 * 来源属性名
	 * <p>如果为空，则默认为注解字段名
	 * @return 来源属性名数组
	 */
	String[] sources() default {};
	
	/**
	 * 目标属性名
	 * @return 目标属性名数组
	 */
	String[] targets() default {};
	
	/**
	 * 转换的策略
	 * @return 转换的策略枚举
	 */
	ConverPolicy policy();
	
}
