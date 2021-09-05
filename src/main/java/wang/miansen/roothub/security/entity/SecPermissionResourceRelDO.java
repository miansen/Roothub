package wang.miansen.roothub.security.entity;

import java.util.Date;

import lombok.Data;

import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.dao.mapper.enums.IdType;

/**
 * 权限资源关联关系
 *
 * @author 龙德
 * @date 2020-12-27 10:04
 */
@Data
@Table(value = "sec_permission_resource_rel")
public class SecPermissionResourceRelDO {

    /**
     * 主键ID
     */
    @Id(value = "permission_resource_rel_id", type = IdType.AUTO)
    private Long permissionResourceRelId;

    /**
     * 权限ID
     */
    private Long permissionId;

    /**
     * 资源ID
     */
    private Long resourceId;

    /**
     * 创建人ID
     */
    private Long createUserId;

    /**
     * 创建人名称
     */
    private String createUserName;

    /**
     * 更新人ID
     */
    private Long updateUserId;

    /**
     * 更新人名称
     */
    private String updateUserName;

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
