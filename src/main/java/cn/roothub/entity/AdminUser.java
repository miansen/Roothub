package cn.roothub.entity;

import java.util.Date;

/**
 * @author miansen.wang
 * @date 2019年2月26日 上午11:42:14
 */
public class AdminUser {
	
	private Integer adminUserId;
	
	private String username;
	
	private String password;
	
	private Integer roleId;
	
	private Date createDate;
	
	private Date updateDate;
	
	private Role role;

	public Integer getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "AdminUser [adminUserId=" + adminUserId + ", username=" + username + ", password=" + password
				+ ", roleId=" + roleId + ", createDate=" + createDate + ", updateDate=" + updateDate + ", role=" + role
				+ "]";
	}
	
}
