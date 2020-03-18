package wang.miansen.roothub.modules.security.model;

import java.util.Date;

import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.dao.mapper.enums.IdType;
import wang.miansen.roothub.common.entity.BaseDO;

/**
 * 权限与资源多对多关联关系实体类
 * 
 * @author miansen.wang
 * @date 2020-03-18
 */
@Table(value = "permission_resource_rel")
public class PermissionResourceRel implements BaseDO {

	private static final long serialVersionUID = 828362395209822648L;

	/**
	 * 主键
	 */
	@Id(value = "permission_resource_rel_id", type = IdType.UUID)
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
		return "PermissionResourceRel {permissionResourceRelId=" + permissionResourceRelId + ", permissionId="
				+ permissionId + ", resourceId=" + resourceId + ", createDate=" + createDate + ", updateDate="
				+ updateDate + "}";
	}

}
