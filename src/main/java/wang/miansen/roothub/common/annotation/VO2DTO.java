package wang.miansen.roothub.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import wang.miansen.roothub.common.enums.BaseMasterDataEnum;
import wang.miansen.roothub.common.enums.ConverPolicy;

/**
 * VO 转 DTO 注解
 * @author miansen.wang
 * @date 2020-02-11
 * @since 3.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface VO2DTO {

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
	
	/**
	 * Service bean Name
	 * <p>注意：如果转换的策略是 {@link ConverPolicy.ID2DTO}，那么此属性不能为空。
	 * @return Service bean Name
	 */
	String service() default "";
	
	/**
	 * 主数据枚举类的 class 对象
	 * <p>注意：
	 * @return 主数据枚举类的 class 对象
	 */
	Class<? extends BaseMasterDataEnum> enumClass() default BaseMasterDataEnum.class;
	
}
