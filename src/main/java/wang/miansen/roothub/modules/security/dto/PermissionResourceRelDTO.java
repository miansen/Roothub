package wang.miansen.roothub.modules.security.dto;

import java.util.Date;

import wang.miansen.roothub.common.dto.BaseDTO;

/**
 * 权限与资源多对多关联关系 DTO
 * 
 * @author miansen.wang
 * @date 2020-03-18
 */
public class PermissionResourceRelDTO implements BaseDTO {
	
	private static final long serialVersionUID = -8004797835671054072L;

	/**
	 * 主键
	 */
	private String permissionResourceRelId;

	/**
	 * 权限ID
	 */
	private String permissionId;
	
	/**
	 * 资源ID
	 */
	private String resourceId;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 更新时间
	 */
	private Date updateDate;

	public String getPermissionResourceRelId() {
		return permissionResourceRelId;
	}

	public void setPermissionResourceRelId(String permissionResourceRelId) {
		this.permissionResourceRelId = permissionResourceRelId;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
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
		return "PermissionResourceRelDTO {permissionResourceRelId=" + permissionResourceRelId + ", permissionId="
				+ permissionId + ", resourceId=" + resourceId + ", createDate=" + createDate + ", updateDate="
				+ updateDate + "}";
	}

}
