package wang.miansen.roothub.modules.node.model;

import java.util.Date;

/**
 * 节点板块实体
 * Table: node_tab
 * @author sen
 * 2018年5月5日
 * 下午8:58:27
 * TODO
 */
public class NodeTab {

	private Integer nodeTabId;//节点板块id
	private String nodeTabCode;//节点板块编码
	private String nodeTabTitle;//节点板块名称
	private String nodeTabDesc;//板块描述
	private boolean isDelete;//是否删除，false:否 true:是'
	private Integer nodeTabOrder;//节点板块排序
	private Date createDate;//创建时间
	private Date updateDate;//更新时间
	public Integer getNodeTabId() {
		return nodeTabId;
	}
	public void setNodeTabId(Integer nodeTabId) {
		this.nodeTabId = nodeTabId;
	}
	public String getNodeTabCode() {
		return nodeTabCode;
	}
	public void setNodeTabCode(String nodeTabCode) {
		this.nodeTabCode = nodeTabCode;
	}
	public String getNodeTabTitle() {
		return nodeTabTitle;
	}
	public void setNodeTabTitle(String nodeTabTitle) {
		this.nodeTabTitle = nodeTabTitle;
	}
	public String getNodeTabDesc() {
		return nodeTabDesc;
	}
	public void setNodeTabDesc(String nodeTabDesc) {
		this.nodeTabDesc = nodeTabDesc;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getNodeTabOrder() {
		return nodeTabOrder;
	}
	public void setNodeTabOrder(Integer nodeTabOrder) {
		this.nodeTabOrder = nodeTabOrder;
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
		return "NodeTab [nodeTabId=" + nodeTabId + ", nodeTabCode=" + nodeTabCode + ", nodeTabTitle=" + nodeTabTitle
				+ ", nodeTabDesc=" + nodeTabDesc + ", isDelete=" + isDelete + ", nodeTabOrder=" + nodeTabOrder
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
}
