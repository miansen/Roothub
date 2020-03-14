package wang.miansen.roothub.modules.security.dto;

import java.util.Date;

import wang.miansen.roothub.common.annotation.DTO2DO;
import wang.miansen.roothub.common.annotation.DTO2VO;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.modules.user.dto.UserDTO;

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
	 * 创建人
	 */
	@DTO2DO(sources = {"userDTO.userId"}, targets = {"userId"}, policy = ConverPolicy.COPY_PROPERTIES)
	@DTO2VO(sources = {"userDTO.userId", "userDTO.userName"}, targets = {"userId",
			"userName"}, policy = ConverPolicy.COPY_PROPERTIES)
	private UserDTO userDTO;

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

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
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
		return "ResourceCategoryDTO [resourceCategoryId=" + resourceCategoryId + ", userDTO=" + userDTO
				+ ", resourceCategoryName=" + resourceCategoryName + ", createDate=" + createDate + ", updateDate="
				+ updateDate + "]";
	}

}
