package wang.miansen.roothub.modules.security.model;

import java.util.Date;

import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.dao.mapper.enums.IdType;
import wang.miansen.roothub.common.entity.BaseDO;

/**
 * 资源类别实体类
 * 
 * @author miansen.wang
 * @date 2020-03-13
 */
@Table(value = "resource_category")
public class ResourceCategory implements BaseDO {

	private static final long serialVersionUID = 666295338495228528L;

	/**
	 * 资源类别ID
	 */
	@Id(value = "resource_category_id", type = IdType.UUID)
	private String resourceCategoryId;

	/**
	 * 资源类别名称
	 */
	private String resourceCategoryName;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 更新时间
	 */
	private Date updateDate;

	public String getResourceCategoryId() {
		return resourceCategoryId;
	}

	public void setResourceCategoryId(String resourceCategoryId) {
		this.resourceCategoryId = resourceCategoryId;
	}

	public String getResourceCategoryName() {
		return resourceCategoryName;
	}

	public void setResourceCategoryName(String resourceCategoryName) {
		this.resourceCategoryName = resourceCategoryName;
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
		return "ResourceCategory [resourceCategoryId=" + resourceCategoryId + ", resourceCategoryName="
				+ resourceCategoryName + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}
