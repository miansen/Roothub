package wang.miansen.roothub.common.entity;

import java.io.Serializable;

/**
 * 该接口作为 entity 层的基础接口，起到标识的作用，建议大部分 entity 层的类实现该接口。
 * <p>注意：如果 {@code Dao} 层继承了 {@link BaseDao}，那么对应的 entity 必须实现该接口。
 * 
 * @author miansen.wang
 * @date 2019-8-19 22:51
 * @since 3.0
 */
public interface BaseDO extends Serializable {
}
