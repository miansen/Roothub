package wang.miansen.roothub.modules.user.dto;

import java.util.Date;

import wang.miansen.roothub.common.dto.BaseDTO;

/**
 * 用户和角色多对多关联 DTO
 * 
 * @author miansen.wang
 * @date 2020-02-23
 */
public class UserRoleRelDTO implements BaseDTO {

	private static final long serialVersionUID = -635272527915998246L;

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
		return "UserRoleRelDTO [userRoleRelId=" + userRoleRelId + ", userId=" + userId + ", roleId=" + roleId
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}
