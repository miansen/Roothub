package wang.miansen.roothub.modules.security.vo;

import wang.miansen.roothub.common.annotation.VO2DTO;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.common.vo.BaseVO;

/**
 * 资源类别 VO
 * 
 * @author miansen.wang
 * @date 2020-03-13
 */
public class ResourceCategoryVO implements BaseVO {

	private static final long serialVersionUID = -4489042601325330174L;

	/**
	 * 资源类别ID
	 */
	private String resourceCategoryId;
	
	/**
	 * 创建人ID
	 */
	@VO2DTO(targets = {"userDTO"}, policy = ConverPolicy.ID_CONVER_DTO, service = "userServiceImpl")
	private String userId;
	
	/**
	 * 创建人名称
	 */
	private String userName;

	/**
	 * 资源类别名称
	 */
	private String resourceCategoryName;

	/**
	 * 创建时间
	 */
	@VO2DTO(targets = {"createDate"}, policy = ConverPolicy.STRING_CONVER_DATE)
	private String createDate;

	/**
	 * 更新时间
	 */
	@VO2DTO(targets = {"updateDate"}, policy = ConverPolicy.STRING_CONVER_DATE)
	private String updateDate;
	
	@Override
	public String getPrimaryKey() {
		return resourceCategoryId;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		this.resourceCategoryId = primaryKey;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getResourceCategoryName() {
		return resourceCategoryName;
	}

	public void setResourceCategoryName(String resourceCategoryName) {
		this.resourceCategoryName = resourceCategoryName;
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
	public String toString() {
		return "ResourceCategoryVO [resourceCategoryId=" + resourceCategoryId + ", userId=" + userId + ", userName="
				+ userName + ", resourceCategoryName=" + resourceCategoryName + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}

}
