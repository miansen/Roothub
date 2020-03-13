package wang.miansen.roothub.modules.security.dto;

import java.util.Date;

import wang.miansen.roothub.common.annotation.DTO2VO;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.enums.ConverPolicy;

/**
 * 资源类别 DTO
 * 
 * @author miansen.wang
 * @date 2020-03-13
 */
public class ResourceCategoryDTO implements BaseDTO {

	private static final long serialVersionUID = 2817184862414737098L;

	/**
	 * 资源类别ID
	 */
	private String resourceCategoryId;

	/**
	 * 资源类别名称
	 */
	private String resourceCategoryName;

	/**
	 * 创建时间
	 */
	@DTO2VO(targets = {"createDate"}, policy = ConverPolicy.DATE_CONVER_STRING)
	private Date createDate;

	/**
	 * 更新时间
	 */
	@DTO2VO(targets = {"updateDate"}, policy = ConverPolicy.DATE_CONVER_STRING)
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
		return "ResourceCategoryDTO [resourceCategoryId=" + resourceCategoryId + ", resourceCategoryName="
				+ resourceCategoryName + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
}
