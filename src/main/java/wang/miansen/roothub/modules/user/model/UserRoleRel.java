package wang.miansen.roothub.modules.user.model;

import java.util.Date;

import wang.miansen.roothub.common.entity.BaseDO;

/**
 * 用户和角色多对多关联实体类
 * 
 * @author miansen.wang
 * @date 2020-02-23
 */
public class UserRoleRel implements BaseDO {

	private static final long serialVersionUID = -5789421283890332085L;

	private String userRoleRelId;

	private String userId;

	private String roleId;

	private Date createDate;

	private Date updateDate;

	public String getUserRoleRelId() {
		return userRoleRelId;
	}

	public void setUserRoleRelId(String userRoleRelId) {
		this.userRoleRelId = userRoleRelId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
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

	@Override
	public String toString() {
		return "UserRoleRel [userRoleRelId=" + userRoleRelId + ", userId=" + userId + ", roleId=" + roleId
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}
