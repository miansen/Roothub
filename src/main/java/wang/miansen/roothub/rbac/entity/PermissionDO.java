package wang.miansen.roothub.rbac.entity;

import java.util.Date;

import lombok.Data;

import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.dao.mapper.enums.IdType;

/**
 * 权限实体类
 *
 * @author miansen.wang
 * @date 2020-12-27 10:00
 */
@Data
@Table(value = "permission")
public class PermissionDO {

    /**
     * 权限ID
     */
    @Id(value = "permission_id", type = IdType.AUTO)
    private Long permissionId;

    /**
     * 模块ID
     */
    private Long moduleId;

    /**
     * 父权限ID
     */
    private Long pid;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限编码
     */
    private String permissionCode;

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
