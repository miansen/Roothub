package wang.miansen.roothub.modules.role.model;

import java.util.Date;

import wang.miansen.roothub.common.entity.BaseDO;

/**
 * 角色权限关联关系实体类
 * 
 * @author miansen.wang
 * @date 2020-02-23
 */
public class RolePermissionRel implements BaseDO {

	private static final long serialVersionUID = 8450105568951941552L;

	private String rolePermissionRelId;

	private String roleId;

	private String permissionId;

	private Date createDate;

	private Date updateDate;

	public String getRolePermissionRelId() {
		return rolePermissionRelId;
	}

	public void setRolePermissionRelId(String rolePermissionRelId) {
		this.rolePermissionRelId = rolePermissionRelId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "RolePermissionRel [rolePermissionRelId=" + rolePermissionRelId + ", roleId=" + roleId
				+ ", permissionId=" + permissionId + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}
