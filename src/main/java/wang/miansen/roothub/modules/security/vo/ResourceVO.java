package wang.miansen.roothub.modules.security.vo;

import wang.miansen.roothub.common.annotation.VO2DTO;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.common.vo.BaseVO;

/**
 * 资源 VO
 * 
 * @author miansen.wang
 * @date 2020-03-13
 */
public class ResourceVO implements BaseVO {

	private static final long serialVersionUID = 5905389412417304050L;

	/**
	 * 资源ID
	 */
	private String resourceId;

	/**
	 * 资源类型ID
	 */
	@VO2DTO(targets = {"resourceTypeDTO"}, policy = ConverPolicy.ID_CONVER_DTO, service = "resourceTypeServiceImpl")
	private String resourceTypeId;

	/**
	 * 资源类型名称
	 */
	private String resourceTypeName;

	/**
	 * 资源类别ID
	 */
	@VO2DTO(targets = {
			"resourceCategoryDTO"}, policy = ConverPolicy.ID_CONVER_DTO, service = "resourceCategoryServiceImpl")
	private String resourceCategoryId;

	/**
	 * 资源类别名称
	 */
	private String resourceCategoryName;

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
	@VO2DTO(targets = {"createDate"}, policy = ConverPolicy.STRING_CONVER_DATE)
	private String createDate;

	/**
	 * 更新时间
	 */
	@VO2DTO(targets = {"updateDate"}, policy = ConverPolicy.STRING_CONVER_DATE)
	private String updateDate;

	@Override
	public String getPrimaryKey() {
		return resourceId;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		this.resourceId = primaryKey;
	}

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

	public String getResourceTypeName() {
		return resourceTypeName;
	}

	public void setResourceTypeName(String resourceTypeName) {
		this.resourceTypeName = resourceTypeName;
	}

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
		return "ResourceVO [resourceId=" + resourceId + ", resourceTypeId=" + resourceTypeId + ", resourceTypeName="
				+ resourceTypeName + ", resourceCategoryId=" + resourceCategoryId + ", resourceCategoryName="
				+ resourceCategoryName + ", userId=" + userId + ", userName=" + userName + ", resourceName="
				+ resourceName + ", resourceValue=" + resourceValue + ", resourceDesc=" + resourceDesc + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "]";
	}

}
