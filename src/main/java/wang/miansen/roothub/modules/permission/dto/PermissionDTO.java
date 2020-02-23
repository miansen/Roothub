package wang.miansen.roothub.modules.permission.dto;

import java.util.Date;

import wang.miansen.roothub.common.annotation.DTO2DO;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.enums.ConverPolicy;

/**
 * 权限 DTO
 * 
 * @author miansen.wang
 * @date 2020-02-23
 */
public class PermissionDTO implements BaseDTO {

	private static final long serialVersionUID = 8058129268320642525L;

	/**
	 * 权限ID
	 */
	private String permissionId;

	/**
	 * 权限名
	 */
	private String permissionName;

	/**
	 * 权限值
	 */
	private String permissionValue;

	/**
	 * 父级权限
	 */
	@DTO2DO(sources = {"parentPermissionDTO.permissionId"}, targets = {"permissionId"}, policy = ConverPolicy.COPY_PROPERTIES)
	private PermissionDTO parentPermissionDTO;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 更新时间
	 */
	private Date updateDate;

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionValue() {
		return permissionValue;
	}

	public void setPermissionValue(String permissionValue) {
		this.permissionValue = permissionValue;
	}

	public PermissionDTO getParentPermissionDTO() {
		return parentPermissionDTO;
	}

	public void setParentPermissionDTO(PermissionDTO parentPermissionDTO) {
		this.parentPermissionDTO = parentPermissionDTO;
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
		return "PermissionDTO [permissionId=" + permissionId + ", permissionName=" + permissionName
				+ ", permissionValue=" + permissionValue + ", parentPermissionDTO=" + parentPermissionDTO
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}
