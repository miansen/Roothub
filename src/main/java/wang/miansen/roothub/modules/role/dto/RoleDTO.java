package wang.miansen.roothub.modules.role.dto;

import java.util.Date;
import java.util.List;

import wang.miansen.roothub.common.annotation.DTO2VO;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.modules.permission.dto.PermissionDTO;

/**
 * 角色 DTO
 * 
 * @author miansen.wang
 * @date 2020-02-23
 */
public class RoleDTO implements BaseDTO {

	private static final long serialVersionUID = -288839287336790134L;

	/**
	 * 角色ID
	 */
	private String roleId;

	/**
	 * 角色名字
	 */
	private String roleName;

	/**
	 * 角色描述
	 */
	private String roleDesc;

	/**
	 * 权限
	 */
	private List<PermissionDTO> permissionDTOs;

	@DTO2VO(targets = {"createDate"}, policy = ConverPolicy.DATE_CONVER_STRING)
	private Date createDate;

	@DTO2VO(targets = {"updateDate"}, policy = ConverPolicy.DATE_CONVER_STRING)
	private Date updateDate;

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

	public List<PermissionDTO> getPermissionDTOs() {
		return permissionDTOs;
	}

	public void setPermissionDTOs(List<PermissionDTO> permissionDTOs) {
		this.permissionDTOs = permissionDTOs;
	}

	@Override
	public String toString() {
		return "RoleDTO [roleId=" + roleId + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", permissionDTOs="
				+ permissionDTOs + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}
