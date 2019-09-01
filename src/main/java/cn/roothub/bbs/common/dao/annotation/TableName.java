package cn.roothub.bbs.common.dao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 此注解作用是标注 Model 对应的表名。
 * 当 dao 层的接口继承了 IBaseDao，可以在接口上标明此注解。
 * @Author: miansen.wang
 * @Date: 2019/9/1 15:51
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TableName {

    String value() default "";
}
