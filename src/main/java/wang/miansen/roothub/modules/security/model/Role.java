package wang.miansen.roothub.modules.security.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author miansen.wang
 * @date 2019年2月26日 下午12:45:44
 */
public class Role implements Serializable{
	
	private static final long serialVersionUID = -5491520720033222765L;

	private Integer roleId;
	
	private String roleName;
	
	private Date createDate;
	
	private Date updateDate;
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", createDate=" + createDate + ", updateDate="
				+ updateDate + "]";
	}
	
}
