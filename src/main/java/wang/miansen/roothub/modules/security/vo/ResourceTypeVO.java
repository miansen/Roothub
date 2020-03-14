package wang.miansen.roothub.modules.security.vo;

import wang.miansen.roothub.common.vo.BaseVO;

/**
 * 资源类型 VO
 * 
 * @author miansen.wang
 * @date 2020-03-14
 */
public class ResourceTypeVO implements BaseVO {

	private static final long serialVersionUID = -7325788013692274567L;

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
	public String getPrimaryKey() {
		return resourceTypeId;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		this.resourceTypeId = primaryKey;
	}

	@Override
	public String toString() {
		return "ResourceTypeVO [resourceTypeId=" + resourceTypeId + ", resourceTypeCode=" + resourceTypeCode
				+ ", resourceTypeName=" + resourceTypeName + "]";
	}

}
