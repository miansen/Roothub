package wang.miansen.roothub.modules.security.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author miansen.wang
 * @date 2019年2月28日 上午10:25:37
 */
public class AdminUserRoleRel implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer adminUserRoleRelId;
	
	private Integer adminUserId;
	
	private Integer RoleId;
	
	private Date createDate;
	
	private Date updateDate;

	public Integer getAdminUserRoleRelId() {
		return adminUserRoleRelId;
	}

	public void setAdminUserRoleRelId(Integer adminUserRoleRelId) {
		this.adminUserRoleRelId = adminUserRoleRelId;
	}

	public Integer getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}

	public Integer getRoleId() {
		return RoleId;
	}

	public void setRoleId(Integer roleId) {
		RoleId = roleId;
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
		return "AdminUserRoleRel [adminUserRoleRelId=" + adminUserRoleRelId + ", adminUserId=" + adminUserId
				+ ", RoleId=" + RoleId + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}
