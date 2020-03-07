package wang.miansen.roothub.modules.sidebar.dto;

import java.util.Date;
import java.util.List;

import wang.miansen.roothub.common.annotation.DTO2DO;
import wang.miansen.roothub.common.annotation.DTO2VO;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.modules.permission.dto.PermissionDTO;
import wang.miansen.roothub.modules.user.dto.UserDTO;

/**
 * @author miansen.wang
 * @date 2020-03-06
 */
public class SidebarDTO implements BaseDTO {

	private static final long serialVersionUID = 1484626306612233693L;

	/**
	 * 侧边栏ID
	 */
	private String sidebarId;
	
	/**
	 * 父级侧边栏
	 */
	@DTO2DO(sources = {"parentSidebarDTO.sidebarId"}, targets = {"parentSidebarId"}, policy = ConverPolicy.COPY_PROPERTIES)
	@DTO2VO(targets = {"parentSidebarVO"}, policy = ConverPolicy.DTO_CONVER_VO)
	private SidebarDTO parentSidebarDTO;
	
	private List<SidebarDTO> childrenList;
	
	/**
	 * 权限
	 */
	@DTO2DO(sources = {"permissionDTO.permissionId"}, targets = {"permissionId"}, policy = ConverPolicy.COPY_PROPERTIES)
	@DTO2VO(sources = {"permissionDTO.permissionId", "permissionDTO.permissionName"}, targets = {"permissionId", "permissionName"}, policy = ConverPolicy.COPY_PROPERTIES)
	private PermissionDTO permissionDTO;
	
	/**
	 * 创建人
	 */
	@DTO2DO(sources = {"userDTO.userId"}, targets = {"userId"}, policy = ConverPolicy.COPY_PROPERTIES)
	@DTO2VO(sources = {"userDTO.userId", "userDTO.userName"}, targets = {"userId", "userName"}, policy = ConverPolicy.COPY_PROPERTIES)
	private UserDTO userDTO;
	
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
	@DTO2VO(targets = {"createDate"}, policy = ConverPolicy.DATE_CONVER_STRING)
	private Date createDate;
	
	/**
	 * 更新时间
	 */
	@DTO2VO(targets = {"updateDate"}, policy = ConverPolicy.DATE_CONVER_STRING)
	private Date updateDate;

	public String getSidebarId() {
		return sidebarId;
	}

	public void setSidebarId(String sidebarId) {
		this.sidebarId = sidebarId;
	}

	public SidebarDTO getParentSidebarDTO() {
		return parentSidebarDTO;
	}

	public void setParentSidebarDTO(SidebarDTO parentSidebarDTO) {
		this.parentSidebarDTO = parentSidebarDTO;
	}
	
	public List<SidebarDTO> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<SidebarDTO> childrenList) {
		this.childrenList = childrenList;
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
		return "SidebarDTO [sidebarId=" + sidebarId + ", parentSidebarDTO=" + parentSidebarDTO + ", childrenList="
				+ childrenList + ", permissionDTO=" + permissionDTO + ", userDTO=" + userDTO + ", sidebarName="
				+ sidebarName + ", sidebarUrl=" + sidebarUrl + ", sidebarIcon=" + sidebarIcon + ", sidebarSort="
				+ sidebarSort + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}
