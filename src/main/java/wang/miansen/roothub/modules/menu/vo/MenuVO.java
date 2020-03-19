package wang.miansen.roothub.modules.menu.vo;

import wang.miansen.roothub.common.annotation.VO2DTO;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.common.vo.BaseVO;

/**
 * 菜单 VO
 * 
 * @author miansen.wang
 * @date 2020-03-06
 */
public class MenuVO implements BaseVO {

	private static final long serialVersionUID = 5736009149025063660L;

	/**
	 * 菜单ID
	 */
	private String menuId;

	/**
	 * 父级菜单
	 */
	@VO2DTO(targets = {"parentMenuDTO"}, policy = ConverPolicy.VO_CONVER_DTO)
	private MenuVO parentMenuVO;

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
	 * 菜单的名称
	 */
	private String menuName;

	/**
	 * 点击菜单时发送的请求URL
	 */
	private String menuUrl;

	/**
	 * 菜单的图标
	 */
	private String menuIcon;

	/**
	 * 排序
	 */
	private Integer menuSort;

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

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public MenuVO getParentMenuVO() {
		return parentMenuVO;
	}

	public void setParentMenuVO(MenuVO parentMenuVO) {
		this.parentMenuVO = parentMenuVO;
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

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public Integer getMenuSort() {
		return menuSort;
	}

	public void setMenuSort(Integer menuSort) {
		this.menuSort = menuSort;
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
	public String getPrimaryKey() {
		return menuId;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		this.menuId = primaryKey;
	}

	@Override
	public String toString() {
		return "MenuVO {menuId=" + menuId + ", parentMenuVO=" + parentMenuVO + ", permissionId=" + permissionId
				+ ", permissionName=" + permissionName + ", permissionValue=" + permissionValue + ", userId=" + userId
				+ ", userName=" + userName + ", menuName=" + menuName + ", menuUrl=" + menuUrl + ", menuIcon="
				+ menuIcon + ", menuSort=" + menuSort + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ "}";
	}

}
