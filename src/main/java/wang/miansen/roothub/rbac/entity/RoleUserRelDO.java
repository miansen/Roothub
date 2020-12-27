package wang.miansen.roothub.rbac.entity;

import java.util.Date;

import lombok.Data;

import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.dao.mapper.enums.IdType;

/**
 * 角色用户关联关系
 *
 * @author miansen.wang
 * @date 2020-12-27 10:11
 */
@Data
@Table(value = "role_user_rel")
public class RoleUserRelDO {

    /**
     * 主键ID
     */
    @Id(value = "role_user_rel_id", type = IdType.AUTO)
    private Long roleUserRelId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 用户ID
     */
    private Long userId;

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
