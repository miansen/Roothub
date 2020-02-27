package wang.miansen.roothub.modules.role.vo;

import wang.miansen.roothub.common.annotation.VO2DTO;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.common.vo.BaseVO;

/**
 * 角色 VO
 * @author miansen.wang
 * @date 2020-02-23
 */
public class RoleVO implements BaseVO {

	private static final long serialVersionUID = -8313103767808600801L;

	private String roleId;

	private String roleName;

	private String roleDesc;

	@VO2DTO(targets = {"createDate"}, policy = ConverPolicy.STRING_CONVER_DATE)
	private String createDate;

	@VO2DTO(targets = {"updateDate"}, policy = ConverPolicy.STRING_CONVER_DATE)
	private String updateDate;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String getPrimaryKey() {
		return roleId;
	}
	
	@Override
	public void setPrimaryKey(String primaryKey) {
		this.roleId = primaryKey;
	}
	
	@Override
	public String toString() {
		return "RoleVO [roleId=" + roleId + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "]";
	}

}
