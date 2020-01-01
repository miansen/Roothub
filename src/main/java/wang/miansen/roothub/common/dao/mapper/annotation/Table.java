package wang.miansen.roothub.common.dao.mapper.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 此注解作用是标注实体类对应的数据库表名。
 * <p>当 dao 层的接口继承了 {@link BaseDao}，可以在对应的实体类上使用此注解。
 * <p>注意：如果不使用此注解标明表名，则表名默认为实体类的简单类名。
 * @Author: miansen.wang
 * @Date: 2019/9/1 15:51
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Table {

    /**
     * 表名
     * @return
     */
    String value() default "";
}
