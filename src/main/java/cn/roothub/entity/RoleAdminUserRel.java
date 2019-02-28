package cn.roothub.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author miansen.wang
 * @date 2019年2月28日 上午10:25:37
 */
public class RoleAdminUserRel implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer roleAdminUserRelId;
	
	private Integer RoleId;
	
	private Integer adminUserId;
	
	private Date createDate;
	
	private Date updateDate;

	public Integer getRoleAdminUserRelId() {
		return roleAdminUserRelId;
	}

	public void setRoleAdminUserRelId(Integer roleAdminUserRelId) {
		this.roleAdminUserRelId = roleAdminUserRelId;
	}

	public Integer getRoleId() {
		return RoleId;
	}

	public void setRoleId(Integer roleId) {
		RoleId = roleId;
	}

	public Integer getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
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
		return "RoleAdminUserRel [roleAdminUserRelId=" + roleAdminUserRelId + ", RoleId=" + RoleId + ", adminUserId="
				+ adminUserId + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
}
