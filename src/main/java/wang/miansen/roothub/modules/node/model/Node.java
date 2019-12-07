package wang.miansen.roothub.modules.node.model;

import java.util.Date;

/**
 * @author miansen.wang
 * @date 2018年11月3日 上午11:38:30
 */
public class Node {

	private Integer nodeId;
	private String nodeCode;
	private String nodeTitle;
	private String avatarNormal;
	private String avatarMini;
	private String avatarLarge;
	private String nodeDesc;
	private Integer tabId;
	private String url;
	private String parentNodeCode;
	private Date createDate;
	private Date updateDate;
	private boolean isDelete;
	private Integer countTopic;

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

	public String getAvatarNormal() {
		return avatarNormal;
	}

	public void setAvatarNormal(String avatarNormal) {
		this.avatarNormal = avatarNormal;
	}

	public String getAvatarMini() {
		return avatarMini;
	}

	public void setAvatarMini(String avatarMini) {
		this.avatarMini = avatarMini;
	}

	public String getAvatarLarge() {
		return avatarLarge;
	}

	public void setAvatarLarge(String avatarLarge) {
		this.avatarLarge = avatarLarge;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentNodeCode() {
		return parentNodeCode;
	}

	public void setParentNodeCode(String parentNodeCode) {
		this.parentNodeCode = parentNodeCode;
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

	public boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getCountTopic() {
		return countTopic;
	}

	public void setCountTopic(Integer countTopic) {
		this.countTopic = countTopic;
	}

	@Override
	public String toString() {
		return "Node [nodeId=" + nodeId + ", nodeCode=" + nodeCode + ", nodeTitle=" + nodeTitle + ", avatarNormal="
				+ avatarNormal + ", avatarMini=" + avatarMini + ", avatarLarge=" + avatarLarge + ", nodeDesc="
				+ nodeDesc + ", tabId=" + tabId + ", url=" + url + ", parentNodeCode=" + parentNodeCode
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", isDelete=" + isDelete
				+ ", countTopic=" + countTopic + "]";
	}

}
