package wang.miansen.roothub.modules.menu.dto;

import java.util.Date;
import java.util.List;

import wang.miansen.roothub.common.annotation.DTO2DO;
import wang.miansen.roothub.common.annotation.DTO2VO;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.modules.permission.dto.PermissionDTO;
import wang.miansen.roothub.modules.user.dto.UserDTO;

/**
 * 菜单 DTO
 * 
 * @author miansen.wang
 * @date 2020-03-06
 */
public class MenuDTO implements BaseDTO {

	private static final long serialVersionUID = 1484626306612233693L;

	/**
	 * 菜单ID
	 */
	private String menuId;

	/**
	 * 父级菜单
	 */
	@DTO2DO(sources = {"parentMenuDTO.menuId"}, targets = {"parentMenuId"}, policy = ConverPolicy.COPY_PROPERTIES)
	@DTO2VO(targets = {"parentMenuVO"}, policy = ConverPolicy.DTO_CONVER_VO)
	private MenuDTO parentMenuDTO;

	/**
	 * 子级菜单
	 */
	private List<MenuDTO> childrenMenuDTOList;

	/**
	 * 权限
	 */
	@DTO2DO(sources = {"permissionDTO.permissionId"}, targets = {"permissionId"}, policy = ConverPolicy.COPY_PROPERTIES)
	@DTO2VO(sources = {"permissionDTO.permissionId", "permissionDTO.permissionName"}, targets = {"permissionId",
			"permissionName"}, policy = ConverPolicy.COPY_PROPERTIES)
	private PermissionDTO permissionDTO;

	/**
	 * 创建人
	 */
	@DTO2DO(sources = {"userDTO.userId"}, targets = {"userId"}, policy = ConverPolicy.COPY_PROPERTIES)
	@DTO2VO(sources = {"userDTO.userId", "userDTO.userName"}, targets = {"userId",
			"userName"}, policy = ConverPolicy.COPY_PROPERTIES)
	private UserDTO userDTO;

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
	@DTO2VO(targets = {"createDate"}, policy = ConverPolicy.DATE_CONVER_STRING)
	private Date createDate;

	/**
	 * 更新时间
	 */
	@DTO2VO(targets = {"updateDate"}, policy = ConverPolicy.DATE_CONVER_STRING)
	private Date updateDate;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public MenuDTO getParentMenuDTO() {
		return parentMenuDTO;
	}

	public void setParentMenuDTO(MenuDTO parentMenuDTO) {
		this.parentMenuDTO = parentMenuDTO;
	}

	public List<MenuDTO> getChildrenMenuDTOList() {
		return childrenMenuDTOList;
	}

	public void setChildrenMenuDTOList(List<MenuDTO> childrenMenuDTOList) {
		this.childrenMenuDTOList = childrenMenuDTOList;
	}

	public PermissionDTO getPermissionDTO() {
		return permissionDTO;
	}

	public void setPermissionDTO(PermissionDTO permissionDTO) {
		this.permissionDTO = permissionDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
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
		return "MenuDTO {menuId=" + menuId + ", parentMenuDTO=" + parentMenuDTO + ", childrenMenuDTOList="
				+ childrenMenuDTOList + ", permissionDTO=" + permissionDTO + ", userDTO=" + userDTO + ", menuName="
				+ menuName + ", menuUrl=" + menuUrl + ", menuIcon=" + menuIcon + ", menuSort=" + menuSort
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "}";
	}

}
