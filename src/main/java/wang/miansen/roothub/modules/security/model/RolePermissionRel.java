package wang.miansen.roothub.modules.security.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author miansen.wang
 * @date 2019年2月26日 下午1:22:43
 */
public class RolePermissionRel implements Serializable{
	
	private static final long serialVersionUID = -2722110977172451408L;

	private Integer rolePermissionRelId;
	
	private Integer roleId;
	
	private Integer permissionId;

	private Date createDate;
	
	private Date updateDate;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "RolePermissionRel [rolePermissionRelId=" + rolePermissionRelId + ", roleId=" + roleId
				+ ", permissionId=" + permissionId + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
}
