package wang.miansen.roothub.modules.app.vo;

import wang.miansen.roothub.common.annotation.VO2DTO;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.common.vo.BaseVO;

/**
 * 应用类别 VO
 * 
 * @author miansen.wang
 * @date 2020-03-08
 */
public class AppCategoryVO implements BaseVO {

	private static final long serialVersionUID = -7554545252945989179L;

	/**
	 * 应用类别ID
	 */
	private String appCategoryId;

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
	 * 应用类别名称
	 */
	private String appCategoryName;
	
	/**
	 * 应用类别描述
	 */
	private String appCategoryDesc;

	/**
	 * 创建时间
	 */
	@VO2DTO(targets = {"createDate"}, policy = ConverPolicy.STRING_CONVER_DATE)
	private String createDate;

	/**
	 * 更新时间
	 */
	@VO2DTO(targets = {"createDate"}, policy = ConverPolicy.STRING_CONVER_DATE)
	private String updateDate;

	@Override
	public String getPrimaryKey() {
		return appCategoryId;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		this.appCategoryId = primaryKey;
	}

	public String getAppCategoryId() {
		return appCategoryId;
	}

	public void setAppCategoryId(String appCategoryId) {
		this.appCategoryId = appCategoryId;
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

	public String getAppCategoryName() {
		return appCategoryName;
	}

	public void setAppCategoryName(String appCategoryName) {
		this.appCategoryName = appCategoryName;
	}
	
	public String getAppCategoryDesc() {
		return appCategoryDesc;
	}

	public void setAppCategoryDesc(String appCategoryDesc) {
		this.appCategoryDesc = appCategoryDesc;
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
		return "AppCategoryVO [appCategoryId=" + appCategoryId + ", userId=" + userId + ", userName=" + userName
				+ ", appCategoryName=" + appCategoryName + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ "]";
	}

}
