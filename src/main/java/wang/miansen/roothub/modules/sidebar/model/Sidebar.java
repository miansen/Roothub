package wang.miansen.roothub.modules.sidebar.model;

import java.util.Date;

import wang.miansen.roothub.common.annotation.DO2DTO;
import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.dao.mapper.enums.IdType;
import wang.miansen.roothub.common.entity.BaseDO;
import wang.miansen.roothub.common.enums.ConverPolicy;

/**
 * @author miansen.wang
 * @date 2020-03-06
 */
@Table(value = "sidebar")
public class Sidebar implements BaseDO {
	
	private static final long serialVersionUID = -4251615965305941108L;
	
	/**
	 * 侧边栏ID
	 */
	@Id(value = "sidebar_id", type = IdType.UUID)
	private String sidebarId;
	
	/**
	 * 父级ID
	 */
	@DO2DTO(targets = {"parentSidebarDTO"}, policy = ConverPolicy.ID_CONVER_DTO, service = "sidebarServiceImpl")
	private String parentSidebarId;
	
	/**
	 * 权限ID
	 */
	@DO2DTO(targets = {"parentSidebarDTO"}, policy = ConverPolicy.ID_CONVER_DTO, service = "permissionServiceImpl")
	private String permissionId;
	
	/**
	 * 创建人ID
	 */
	@DO2DTO(targets = {"userDTO"}, policy = ConverPolicy.ID_CONVER_DTO, service = "userServiceImpl")
	private String userId;
	
	/**
	 * 侧边栏的名字
	 */
	private String sidebarName;
	
	/**
	 * 点击侧边栏时发送的请求URL
	 */
	private String sidebarUrl;
	
	/**
	 * 排序
	 */
	private Integer sidebarSort;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 更新时间
	 */
	private Date updateDate;

	public String getSidebarId() {
		return sidebarId;
	}

	public void setSidebarId(String sidebarId) {
		this.sidebarId = sidebarId;
	}

	public String getParentSidebarId() {
		return parentSidebarId;
	}

	public void setParentSidebarId(String parentSidebarId) {
		this.parentSidebarId = parentSidebarId;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
		return "Sidebar {sidebarId=" + sidebarId + ", parentSidebarId=" + parentSidebarId + ", permissionId="
				+ permissionId + ", userId=" + userId + ", sidebarName=" + sidebarName + ", sidebarUrl=" + sidebarUrl
				+ ", sidebarSort=" + sidebarSort + ", createDate=" + createDate + ", updateDate=" + updateDate + "}";
	}

}
