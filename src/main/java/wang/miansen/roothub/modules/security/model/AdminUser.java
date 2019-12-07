package wang.miansen.roothub.modules.security.model;

import java.util.Date;
import java.util.List;

/**
 * @author miansen.wang
 * @date 2019年2月26日 上午11:42:14
 */
public class AdminUser {
	
	private Integer adminUserId;
	
	private String username;
	
	private String password;
	
	private String avatar;
	
	private Date createDate;
	
	private Date updateDate;
	
	private List<Role> roles;

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
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "AdminUser [adminUserId=" + adminUserId + ", username=" + username + ", password=" + password
				+ ", avatar=" + avatar + ", createDate=" + createDate + ", updateDate=" + updateDate + ", roles="
				+ roles + "]";
	}

}
