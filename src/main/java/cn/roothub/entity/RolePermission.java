package cn.roothub.entity;

/**
 * @author miansen.wang
 * @date 2019年2月26日 下午1:22:43
 */
public class RolePermission {
	
	private Integer rolePermissionId;
	
	private Integer roleId;
	
	private Integer permissionId;

	public Integer getRolePermissionId() {
		return rolePermissionId;
	}

	public void setRolePermissionId(Integer rolePermissionId) {
		this.rolePermissionId = rolePermissionId;
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
		return "RolePermission [rolePermissionId=" + rolePermissionId + ", roleId=" + roleId + ", permissionId="
				+ permissionId + "]";
	}
}
