package cn.roothub.entity;

/**
 * @author miansen.wang
 * @date 2019年2月26日 下午1:22:43
 */
public class RolePermissionRel {
	
	private Integer rolePermissionRelId;
	
	private Integer roleId;
	
	private Integer permissionId;

	public Integer getRolePermissionRelId() {
		return rolePermissionRelId;
	}

	public void setRolePermissionRelId(Integer rolePermissionRelId) {
		this.rolePermissionRelId = rolePermissionRelId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	@Override
	public String toString() {
		return "RolePermissionRel [rolePermissionRelId=" + rolePermissionRelId + ", roleId=" + roleId
				+ ", permissionId=" + permissionId + "]";
	}
}
