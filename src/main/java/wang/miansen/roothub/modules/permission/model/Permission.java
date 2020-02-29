package wang.miansen.roothub.modules.permission.model;

import java.util.Date;

import wang.miansen.roothub.common.annotation.DO2DTO;
import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.dao.mapper.enums.IdType;
import wang.miansen.roothub.common.entity.BaseDO;
import wang.miansen.roothub.common.enums.ConverPolicy;

/**
 * 权限实体类
 * 
 * @author miansen.wang
 * @date 2020-02-23
 */
@Table(value = "permission")
public class Permission implements BaseDO {

	private static final long serialVersionUID = -7554322590178419319L;

	/**
	 * 权限ID
	 */
	@Id(value = "permission_id", type = IdType.UUID)
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
	 * 权限描述
	 */
	private String permissionDesc;

	/**
	 * 父级权限ID
	 */
	@DO2DTO(targets = {"parentPermissionDTO"}, policy = ConverPolicy.ID_CONVER_DTO, service = "permissionServiceImpl")
	private String parentPermissionId;

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
	
	public String getPermissionDesc() {
		return permissionDesc;
	}

	public void setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc;
	}

	public String getParentPermissionId() {
		return parentPermissionId;
	}

	public void setParentPermissionId(String parentPermissionId) {
		this.parentPermissionId = parentPermissionId;
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
		return "Permission [permissionId=" + permissionId + ", permissionName=" + permissionName + ", permissionValue="
				+ permissionValue + ", permissionDesc=" + permissionDesc + ", parentPermissionId=" + parentPermissionId
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}
