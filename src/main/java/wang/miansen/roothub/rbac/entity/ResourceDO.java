package wang.miansen.roothub.rbac.entity;

import java.util.Date;

import lombok.Data;

import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.dao.mapper.enums.IdType;

/**
 * 资源实体类
 *
 * @author miansen.wang
 * @date 2020-12-22 21:15:54
 * @since 3.0
 */
@Data
@Table(value = "resource")
public class ResourceDO {

    /**
     * 资源ID
     */
    @Id(value = "resource_id", type = IdType.AUTO)
    private Long resourceId;

    /**
     * 模块ID
     */
    private Long moduleId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源值
     */
    private String resourceValue;

    /**
     * 资源描述
     */
    private String resourceDesc;

    /**
     * 是否禁用（0：启用，1：禁用）
     */
    private Boolean isDisabled;

    /**
     * 是否删除（0：有效，1：删除）
     */
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
