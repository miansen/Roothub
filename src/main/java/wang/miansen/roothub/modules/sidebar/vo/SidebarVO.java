package wang.miansen.roothub.modules.sidebar.vo;

import wang.miansen.roothub.common.annotation.VO2DTO;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.common.vo.BaseVO;

/**
 * @author miansen.wang
 * @date 2020-03-06
 */
public class SidebarVO implements BaseVO {
	
	private static final long serialVersionUID = 5736009149025063660L;

	/**
	 * 侧边栏ID
	 */
	private String sidebarId;
	
	/**
	 * 父级侧边栏
	 */
	@VO2DTO(targets = {"parentSidebarDTO"}, policy = ConverPolicy.VO_CONVER_DTO)
	private SidebarVO parentSidebarVO;
	
	/**
	 * 权限ID
	 */
	@VO2DTO(targets = {"permissionDTO"}, policy = ConverPolicy.ID_CONVER_DTO, service = "permissionServiceImpl")
	private String permissionId;
	
	/**
	 * 权限名
	 */
	private String permissionName;

	/**
	 * 权限值
	 */
	private String permissionValue;
	
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
	 * 侧边栏的名字
	 */
	private String sidebarName;
	
	/**
	 * 点击侧边栏时发送的请求URL
	 */
	private String sidebarUrl;
	
	/**
	 * 侧边栏的图标
	 */
	private String sidebarIcon;
	
	/**
	 * 排序
	 */
	private Integer sidebarSort;
	
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
		return sidebarId;
	}

	public void setPrimaryKey(String primaryKey) {
		this.sidebarId = primaryKey;
	}

	public String getSidebarId() {
		return sidebarId;
	}

	public void setSidebarId(String sidebarId) {
		this.sidebarId = sidebarId;
	}

	public SidebarVO getParentSidebarVO() {
		return parentSidebarVO;
	}

	public void setParentSidebarVO(SidebarVO parentSidebarVO) {
		this.parentSidebarVO = parentSidebarVO;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionValue() {
		return permissionValue;
	}

	public void setPermissionValue(String permissionValue) {
		this.permissionValue = permissionValue;
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

	public String getSidebarName() {
		return sidebarName;
	}

	public void setSidebarName(String sidebarName) {
		this.sidebarName = sidebarName;
	}

	public String getSidebarUrl() {
		return sidebarUrl;
	}

	public void setSidebarUrl(String sidebarUrl) {
		this.sidebarUrl = sidebarUrl;
	}
	
	public String getSidebarIcon() {
		return sidebarIcon;
	}

	public void setSidebarIcon(String sidebarIcon) {
		this.sidebarIcon = sidebarIcon;
	}

	public Integer getSidebarSort() {
		return sidebarSort;
	}

	public void setSidebarSort(Integer sidebarSort) {
		this.sidebarSort = sidebarSort;
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
		return "SidebarVO [sidebarId=" + sidebarId + ", parentSidebarVO=" + parentSidebarVO + ", permissionId="
				+ permissionId + ", permissionName=" + permissionName + ", permissionValue=" + permissionValue
				+ ", userId=" + userId + ", userName=" + userName + ", sidebarName=" + sidebarName + ", sidebarUrl="
				+ sidebarUrl + ", sidebarIcon=" + sidebarIcon + ", sidebarSort=" + sidebarSort + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "]";
	}

}
