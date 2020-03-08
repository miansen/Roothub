package wang.miansen.roothub.modules.app.dto;

import java.util.Date;

import wang.miansen.roothub.common.annotation.DTO2DO;
import wang.miansen.roothub.common.annotation.DTO2VO;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.modules.app.enums.AppStatusEnum;
import wang.miansen.roothub.modules.user.dto.UserDTO;

/**
 * 应用 DTO
 * 
 * @author miansen.wang
 * @date 2020-03-08
 */
public class AppDTO implements BaseDTO {

	private static final long serialVersionUID = 3024140261047780966L;

	/**
	 * 应用ID
	 */
	private String appId;

	/**
	 * 应用类别
	 */
	@DTO2DO(sources = {"appCategoryDTO.appCategoryId"}, targets = {
			"appCategoryId"}, policy = ConverPolicy.COPY_PROPERTIES)
	@DTO2VO(sources = {"appCategoryDTO.appCategoryId", "appCategoryDTO.appCategoryName"}, targets = {"appCategoryId",
			"appCategoryName"}, policy = ConverPolicy.COPY_PROPERTIES)
	private AppCategoryDTO appCategoryDTO;

	/**
	 * 创建人
	 */
	@DTO2DO(sources = {"userDTO.userId"}, targets = {"userId"}, policy = ConverPolicy.COPY_PROPERTIES)
	@DTO2VO(sources = {"userDTO.userId", "userDTO.userName"}, targets = {"userId",
			"userName"}, policy = ConverPolicy.COPY_PROPERTIES)
	private UserDTO userDTO;

	/**
	 * 应用名称
	 */
	private String appName;

	/**
	 * 应用描述
	 */
	private String appDesc;

	/**
	 * 应用图标
	 */
	private String appIcon;

	/**
	 * 应用首页
	 */
	private String appIndex;

	/**
	 * 应用状态
	 */
	@DTO2DO(targets = {"appStatus"}, policy = ConverPolicy.ENUM_CONVER_CODE)
	@DTO2VO(targets = {"appStatus"}, policy = ConverPolicy.ENUM_CONVER_DESC)
	private AppStatusEnum appStatusEnum;

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

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public AppCategoryDTO getAppCategoryDTO() {
		return appCategoryDTO;
	}

	public void setAppCategoryDTO(AppCategoryDTO appCategoryDTO) {
		this.appCategoryDTO = appCategoryDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppDesc() {
		return appDesc;
	}

	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}

	public String getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}

	public String getAppIndex() {
		return appIndex;
	}

	public void setAppIndex(String appIndex) {
		this.appIndex = appIndex;
	}

	public AppStatusEnum getAppStatusEnum() {
		return appStatusEnum;
	}

	public void setAppStatusEnum(AppStatusEnum appStatusEnum) {
		this.appStatusEnum = appStatusEnum;
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
		return "AppDTO [appId=" + appId + ", appCategoryDTO=" + appCategoryDTO + ", userDTO=" + userDTO + ", appName="
				+ appName + ", appDesc=" + appDesc + ", appIcon=" + appIcon + ", appIndex=" + appIndex
				+ ", appStatusEnum=" + appStatusEnum + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ "]";
	}

}
