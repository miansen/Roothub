package cn.roothub.entity;

import java.util.Date;

/**
 * 
 * @author miansen.wang
 * @date 2018年11月3日 上午11:38:30
 */
public class Node {
	
	private Integer nodeId;
	private String nodeCode;
	private String nodeTitle;
	private String nodeImg;
	private String nodeBgImg;
	private String nodeDesc;
	private Integer tabId;
	private Date createDate;
	private Date updateDate;
	private boolean isDelete;
	public Integer getNodeId() {
		return nodeId;
	}
	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeCode() {
		return nodeCode;
	}
	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	public String getNodeTitle() {
		return nodeTitle;
	}
	public void setNodeTitle(String nodeTitle) {
		this.nodeTitle = nodeTitle;
	}
	public String getNodeImg() {
		return nodeImg;
	}
	public void setNodeImg(String nodeImg) {
		this.nodeImg = nodeImg;
	}
	public String getNodeBgImg() {
		return nodeBgImg;
	}
	public void setNodeBgImg(String nodeBgImg) {
		this.nodeBgImg = nodeBgImg;
	}
	public String getNodeDesc() {
		return nodeDesc;
	}
	public void setNodeDesc(String nodeDesc) {
		this.nodeDesc = nodeDesc;
	}
	public Integer getTabId() {
		return tabId;
	}
	public void setTabId(Integer tabId) {
		this.tabId = tabId;
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
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	@Override
	public String toString() {
		return "Node [nodeId=" + nodeId + ", nodeCode=" + nodeCode + ", nodeTitle=" + nodeTitle + ", nodeImg=" + nodeImg
				+ ", nodeBgImg=" + nodeBgImg + ", nodeDesc=" + nodeDesc + ", tabId=" + tabId + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", isDelete=" + isDelete + "]";
	}
	
}
