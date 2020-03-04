package wang.miansen.roothub.modules.node.vo;

import wang.miansen.roothub.common.annotation.VO2DTO;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.common.vo.BaseVO;

/**
 * 节点 VO
 * 
 * @author miansen.wang
 * @date 2020-02-15
 */
public class NodeVO implements BaseVO {

	private static final long serialVersionUID = -5069765161106805619L;

	/**
	 * 节点ID
	 */
	private String nodeId;
	
	/**
	 * 父节点ID
	 */
	@VO2DTO(targets = {"parentNodeDTO"}, policy = ConverPolicy.ID_CONVER_DTO, service = "nodeServiceImpl")
	private String parentNodeId;
	
	/**
	 * 父节点名字
	 */
	private String parentNodeName;
	
	/**
	 * 节点编码
	 */
	private String nodeCode;
	
	/**
	 * 节点名字
	 */
	private String nodeName;
	
	/**
	 * 节点说明
	 */
	private String nodeDesc;
	
	/**
	 * 节点图标
	 */
	private String avatar;
	
	/**
	 * 链接
	 */
	private String url;
	
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
	
	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getParentNodeId() {
		return parentNodeId;
	}

	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}

	public String getParentNodeName() {
		return parentNodeName;
	}

	public void setParentNodeName(String parentNodeName) {
		this.parentNodeName = parentNodeName;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeDesc() {
		return nodeDesc;
	}

	public void setNodeDesc(String nodeDesc) {
		this.nodeDesc = nodeDesc;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		return nodeId;
	}
	
	@Override
	public void setPrimaryKey(String primaryKey) {
		this.nodeId = primaryKey;
	}

	@Override
	public String toString() {
		return "NodeVO {nodeId=" + nodeId + ", parentNodeId=" + parentNodeId + ", parentNodeName=" + parentNodeName
				+ ", nodeCode=" + nodeCode + ", nodeName=" + nodeName + ", nodeDesc=" + nodeDesc + ", avatar=" + avatar
				+ ", url=" + url + ", createDate=" + createDate + ", updateDate=" + updateDate + "}";
	}

}
