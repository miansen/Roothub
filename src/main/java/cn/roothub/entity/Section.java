package cn.roothub.entity;

import java.util.Date;

/**
 * 板块实体
 * Table: root_section
 * @author sen
 * 2018年5月5日
 * 下午8:58:27
 * TODO
 */
public class Section {

	/**
	 * 板块标识
	 */
	private Integer sectionId;
	
	/**
	 * 板块名称
	 */
	private String sectionName;
	
	/**
	 * 板块标签
	 */
	private String sectionTab;
	
	/**
	 * 板块描述
	 */
	private String sectionDesc;
	
	/**
	 * 板块帖子数目
	 */
	private Integer sectionTopicNum;
	
	/**
	 * 是否显示，0不显示 1显示
	 */
	private Boolean showStatus;
	
	/**
	 * 板块排序
	 */
	private Integer displayIndex;
	
	/**
	 * 默认显示板块 0默认，1显示
	 */
	private Boolean defaultShow;
	
	/**
	 * 模块父节点
	 */
	private Integer pid;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 更新时间
	 */
	private Date updateDate;
	
	/**
	 * 板块状态 1000:有效 1100:无效 1200:未生效
	 */
	private String statusCd;

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getSectionTab() {
		return sectionTab;
	}

	public void setSectionTab(String sectionTab) {
		this.sectionTab = sectionTab;
	}

	public String getSectionDesc() {
		return sectionDesc;
	}

	public void setSectionDesc(String sectionDesc) {
		this.sectionDesc = sectionDesc;
	}

	public Integer getSectionTopicNum() {
		return sectionTopicNum;
	}

	public void setSectionTopicNum(Integer sectionTopicNum) {
		this.sectionTopicNum = sectionTopicNum;
	}

	public Boolean getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(Boolean showStatus) {
		this.showStatus = showStatus;
	}

	public Integer getDisplayIndex() {
		return displayIndex;
	}

	public void setDisplayIndex(Integer displayIndex) {
		this.displayIndex = displayIndex;
	}

	public Boolean getDefaultShow() {
		return defaultShow;
	}

	public void setDefaultShow(Boolean defaultShow) {
		this.defaultShow = defaultShow;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
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

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	@Override
	public String toString() {
		return "RootSection [sectionId=" + sectionId + ", sectionName=" + sectionName + ", sectionTab=" + sectionTab
				+ ", sectionDesc=" + sectionDesc + ", sectionTopicNum=" + sectionTopicNum + ", showStatus=" + showStatus
				+ ", displayIndex=" + displayIndex + ", defaultShow=" + defaultShow + ", pid=" + pid + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", statusCd=" + statusCd + "]";
	}
	
}
