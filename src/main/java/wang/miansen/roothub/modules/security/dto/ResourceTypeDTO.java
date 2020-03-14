package wang.miansen.roothub.modules.security.dto;

import wang.miansen.roothub.common.dto.BaseDTO;

/**
 * 资源类型 DTO
 * 
 * @author miansen.wang
 * @date 2020-03-14
 */
public class ResourceTypeDTO implements BaseDTO {

	private static final long serialVersionUID = -7506656176498338788L;

	/**
	 * 资源类型ID
	 */
	private String resourceTypeId;
	
	/**
	 * 资源类型编码
	 */
	private Integer resourceTypeCode;

	/**
	 * 资源类型名称
	 */
	private String resourceTypeName;

	public String getResourceTypeId() {
		return resourceTypeId;
	}

	public void setResourceTypeId(String resourceTypeId) {
		this.resourceTypeId = resourceTypeId;
	}
	
	public Integer getResourceTypeCode() {
		return resourceTypeCode;
	}

	public void setResourceTypeCode(Integer resourceTypeCode) {
		this.resourceTypeCode = resourceTypeCode;
	}

	public String getResourceTypeName() {
		return resourceTypeName;
	}

	public void setResourceTypeName(String resourceTypeName) {
		this.resourceTypeName = resourceTypeName;
	}

	@Override
	public String toString() {
		return "ResourceTypeDTO [resourceTypeId=" + resourceTypeId + ", resourceTypeCode=" + resourceTypeCode
				+ ", resourceTypeName=" + resourceTypeName + "]";
	}

}
