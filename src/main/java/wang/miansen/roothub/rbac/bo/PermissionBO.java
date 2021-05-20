package wang.miansen.roothub.rbac.bo;

import java.util.Date;

import lombok.Data;

/**
 * 权限 BO
 *
 * @author miansen.wang
 * @date 2020-12-27 15:20
 */
@Data
public class PermissionBO {

    /**
     * 权限ID
     */
    private Long permissionId;

    /**
     * 系统ID
     */
    private Long systemId;

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 系统编码
     */
    private String systemCode;

    /**
     * 模块ID
     */
    private Long moduleId;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 模块编码
     */
    private String moduleCode;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 权限描述
     */
    private String permissionDesc;

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
