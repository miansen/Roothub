package wang.miansen.roothub.common.dao.mapper.annotation;

import wang.miansen.roothub.common.dao.mapper.enums.IdType;

import java.lang.annotation.*;

/**
 * 此注解作用是标注实体类对应的数据库表的主键名和类型。
 * <p>当 dao 层的接口继承了 {@link BaseDao}，可以在对应的实体类的字段上使用此注解。
 * <p>注意：如果不使用此注解标明主键，则主键默认为空。
 * @Author: miansen.wang
 * @Date: 2019/10/20 18:01
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Id {

    /**
     * 主键名称
     * @return
     */
    String value() default "";

    /**
     * 主键类型
     * @return
     */
    IdType type() default IdType.NONE;
}
