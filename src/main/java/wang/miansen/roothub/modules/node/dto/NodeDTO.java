package wang.miansen.roothub.modules.node.dto;

import java.util.Date;

import wang.miansen.roothub.common.annotation.DTO2DO;
import wang.miansen.roothub.common.annotation.DTO2VO;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.enums.ConverPolicy;

/**
 * 节点 DTO
 * 
 * @author miansen.wang
 * @date 2018年11月3日 上午11:38:30
 */
public class NodeDTO implements BaseDTO {

	private static final long serialVersionUID = 281937364772145384L;

	/**
	 * 节点ID
	 */
	private String nodeId;

	/**
	 * 父节点
	 */
	@DTO2DO(sources = {"parentNodeDTO.nodeId"}, targets = {"parentNodeId"}, policy = ConverPolicy.COPY_PROPERTIES)
	@DTO2VO(sources = {"parentNodeDTO.nodeId", "parentNodeDTO.nodeName"}, targets = {"parentNodeId",
			"parentNodeName"}, policy = ConverPolicy.COPY_PROPERTIES)
	private NodeDTO parentNodeDTO;

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
	@DTO2VO(targets = {"createDate"}, policy = ConverPolicy.DATE_CONVER_STRING)
	private Date createDate;

	/**
	 * 更新时间
	 */
	@DTO2VO(targets = {"updateDate"}, policy = ConverPolicy.DATE_CONVER_STRING)
	private Date updateDate;

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public NodeDTO getParentNodeDTO() {
		return parentNodeDTO;
	}

	public void setParentNodeDTO(NodeDTO parentNodeDTO) {
		this.parentNodeDTO = parentNodeDTO;
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
		return "NodeDTO {nodeId=" + nodeId + ", parentNodeDTO=" + parentNodeDTO + ", nodeCode=" + nodeCode
				+ ", nodeName=" + nodeName + ", nodeDesc=" + nodeDesc + ", avatar=" + avatar + ", url=" + url
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "}";
	}

}
