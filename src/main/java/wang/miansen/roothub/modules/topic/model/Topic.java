package wang.miansen.roothub.modules.topic.model;

import java.util.Date;

import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.enums.IdType;
import wang.miansen.roothub.common.entity.BaseDO;

/**
 * 帖子实体类
 * @author miansen.wang
 * @date 2018-5-5 9:19:57
 * @version 3.0
 * @since 1.0
 */
public class Topic implements BaseDO {

	/**
	 * 帖子ID
	 */
	@Id(value = "topic_id", type = IdType.AUTO)
	private Integer topicId;
	
	/**
	 * 节点ID
	 */
	private Integer nodeId;
	
	/**
	 * 作者ID
	 */
	private Integer userId;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 正文
	 */
	private String content;
	
	/**
	 * 摘录
	 */
	private String excerpt;
	
	/**
	 * 封面
	 */
	private String avatar;
	
	/**
	 * 链接
	 */
	private String url;
	
	/**
	 * true 置顶 false 默认
	 */
	private Boolean top;
	
	/**
	 * true 精华 false 默认
	 */
	private Boolean good;
	
	/**
	 * 浏览量
	 */
	private Integer viewCount;
	
	/**
	 * 转载量
	 */
	private Integer shareCount;
	
	/**
	 * 好评量
	 */
	private Integer goodCount;
	
	/**
	 * 差评量
	 */
	private Integer badCount;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 更新时间
	 */
	private Date updateDate;
	
	/**
	 * 类型，1000（文本）、1100（图片）、1200（视频）、1300（链接）
	 */
	private String type;
	
	/**
	 * 状态，1000（有效）、1100（无效）、1200（未生效）
	 */
	private String status;
	
	/**
	 * 备注
	 */
	private String remark;

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getExcerpt() {
		return excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
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

	public Boolean getTop() {
		return top;
	}

	public void setTop(Boolean top) {
		this.top = top;
	}

	public Boolean getGood() {
		return good;
	}

	public void setGood(Boolean good) {
		this.good = good;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	public Integer getGoodCount() {
		return goodCount;
	}

	public void setGoodCount(Integer goodCount) {
		this.goodCount = goodCount;
	}

	public Integer getBadCount() {
		return badCount;
	}

	public void setBadCount(Integer badCount) {
		this.badCount = badCount;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Topic {topicId=" + topicId + ", nodeId=" + nodeId + ", userId=" + userId + ", title=" + title
				+ ", content=" + content + ", excerpt=" + excerpt + ", avatar=" + avatar + ", url=" + url + ", top="
				+ top + ", good=" + good + ", viewCount=" + viewCount + ", shareCount=" + shareCount + ", goodCount="
				+ goodCount + ", badCount=" + badCount + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", type=" + type + ", status=" + status + ", remark=" + remark + "}";
	}

}
