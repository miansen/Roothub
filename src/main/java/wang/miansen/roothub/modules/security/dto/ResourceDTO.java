package wang.miansen.roothub.modules.security.dto;

import java.util.Date;
import java.util.List;

import wang.miansen.roothub.common.annotation.DTO2DO;
import wang.miansen.roothub.common.annotation.DTO2VO;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.modules.permission.dto.PermissionDTO;
import wang.miansen.roothub.modules.user.dto.UserDTO;

/**
 * 资源 DTO
 * 
 * @author miansen.wang
 * @date 2020-03-13
 */
public class ResourceDTO implements BaseDTO {

	private static final long serialVersionUID = -3326652244402084192L;

	/**
	 * 资源ID
	 */
	private String resourceId;

	/**
	 * 资源类型 DTO
	 */
	@DTO2DO(sources = {"resourceTypeDTO.resourceTypeId"}, targets = {
			"resourceTypeId"}, policy = ConverPolicy.COPY_PROPERTIES)
	@DTO2VO(sources = {"resourceTypeDTO.resourceTypeId", "resourceTypeDTO.resourceTypeName"}, targets = {
			"resourceTypeId", "resourceTypeName"}, policy = ConverPolicy.COPY_PROPERTIES)
	private ResourceTypeDTO resourceTypeDTO;

	/**
	 * 资源类别DTO
	 */
	@DTO2DO(sources = {"resourceCategoryDTO.resourceCategoryId"}, targets = {
			"resourceCategoryId"}, policy = ConverPolicy.COPY_PROPERTIES)
	@DTO2VO(sources = {"resourceCategoryDTO.resourceCategoryId",
			"resourceCategoryDTO.resourceCategoryName"}, targets = {"resourceCategoryId",
					"resourceCategoryName"}, policy = ConverPolicy.COPY_PROPERTIES)
	private ResourceCategoryDTO resourceCategoryDTO;

	/**
	 * 创建人DTO
	 */
	@DTO2DO(sources = {"userDTO.userId"}, targets = {"userId"}, policy = ConverPolicy.COPY_PROPERTIES)
	@DTO2VO(sources = {"userDTO.userId", "userDTO.userName"}, targets = {"userId",
			"userName"}, policy = ConverPolicy.COPY_PROPERTIES)
	private UserDTO userDTO;

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
	 * 资源所属的权限
	 */
	private List<PermissionDTO> permissionDTOList;

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

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public ResourceTypeDTO getResourceTypeDTO() {
		return resourceTypeDTO;
	}

	public void setResourceTypeDTO(ResourceTypeDTO resourceTypeDTO) {
		this.resourceTypeDTO = resourceTypeDTO;
	}

	public ResourceCategoryDTO getResourceCategoryDTO() {
		return resourceCategoryDTO;
	}

	public void setResourceCategoryDTO(ResourceCategoryDTO resourceCategoryDTO) {
		this.resourceCategoryDTO = resourceCategoryDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
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

	public List<PermissionDTO> getPermissionDTOList() {
		return permissionDTOList;
	}

	public void setPermissionDTOList(List<PermissionDTO> permissionDTOList) {
		this.permissionDTOList = permissionDTOList;
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
		return "ResourceDTO {resourceId=" + resourceId + ", resourceTypeDTO=" + resourceTypeDTO
				+ ", resourceCategoryDTO=" + resourceCategoryDTO + ", userDTO=" + userDTO + ", resourceName="
				+ resourceName + ", resourceValue=" + resourceValue + ", resourceDesc=" + resourceDesc
				+ ", permissionDTOList=" + permissionDTOList + ", createDate=" + createDate + ", updateDate="
				+ updateDate + "}";
	}

}
