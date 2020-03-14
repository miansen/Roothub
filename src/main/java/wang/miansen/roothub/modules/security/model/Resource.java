package wang.miansen.roothub.modules.security.model;

import java.util.Date;

import wang.miansen.roothub.common.annotation.DO2DTO;
import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.dao.mapper.enums.IdType;
import wang.miansen.roothub.common.entity.BaseDO;
import wang.miansen.roothub.common.enums.ConverPolicy;

/**
 * 资源实体类
 * 
 * @author miansen.wang
 * @date 2020-03-13
 */
@Table(value = "resource")
public class Resource implements BaseDO {

	private static final long serialVersionUID = 248310394613977823L;

	/**
	 * 资源ID
	 */
	@Id(value = "resource_id", type = IdType.UUID)
	private String resourceId;

	/**
	 * 资源类型ID
	 */
	@DO2DTO(targets = {"resourceTypeDTO"}, policy = ConverPolicy.ID_CONVER_DTO, service = "resourceTypeServiceImpl")
	private String resourceTypeId;

	/**
	 * 资源类别ID
	 */
	@DO2DTO(targets = {
			"resourceCategoryDTO"}, policy = ConverPolicy.ID_CONVER_DTO, service = "resourceCategoryServiceImpl")
	private String resourceCategoryId;

	/**
	 * 创建人ID
	 */
	@DO2DTO(targets = {"userDTO"}, policy = ConverPolicy.ID_CONVER_DTO, service = "userServiceImpl")
	private String userId;

	/**
	 * 资源名称
	 */
	private String resourceName;

	/**
	 * 资源值
	 */
	private String resourceValue;

	/**
	 * 资源描述
	 */
	private String resourceDesc;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 更新时间
	 */
	private Date updateDate;

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceTypeId() {
		return resourceTypeId;
	}

	public void setResourceTypeId(String resourceTypeId) {
		this.resourceTypeId = resourceTypeId;
	}

	public String getResourceCategoryId() {
		return resourceCategoryId;
	}

	public void setResourceCategoryId(String resourceCategoryId) {
		this.resourceCategoryId = resourceCategoryId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceValue() {
		return resourceValue;
	}

	public void setResourceValue(String resourceValue) {
		this.resourceValue = resourceValue;
	}

	public String getResourceDesc() {
		return resourceDesc;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
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
		return "Resource [resourceId=" + resourceId + ", resourceTypeId=" + resourceTypeId + ", resourceCategoryId="
				+ resourceCategoryId + ", userId=" + userId + ", resourceName=" + resourceName + ", resourceValue="
				+ resourceValue + ", resourceDesc=" + resourceDesc + ", createDate=" + createDate + ", updateDate="
				+ updateDate + "]";
	}

}
