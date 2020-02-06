package wang.miansen.roothub.modules.post.vo;

import wang.miansen.roothub.common.vo.BaseVO;

/**
 * @author miansen.wang
 * @date 2020-01-19
 */
public class PostVO implements BaseVO {

	private static final long serialVersionUID = 4899815331871315247L;

	/**
	 * 帖子 ID
	 */
	private String topicId;
	
	/**
	 * 节点 ID
	 */
	private Integer nodeId;
	
	/**
	 * 节点名称
	 */
	private String nodeName;
	
	/**
	 * 用户 ID
	 */
	private Integer userId;
	
	/**
	 * 用户名称
	 */
	private String userName;
	
	/**
	 * 帖子标题
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
	 * true 精华 false默认
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
	private Integer postGoodCount;
	
	/**
	 * 创建时间
	 */
	private String createDate;
	
	/**
	 * 更新时间
	 */
	private String updateDate;
	
	/**
	 * 类型：文本、图片、视频、链接
	 */
	private String type;
	
	/**
	 * 状态：有效、无效、未生效
	 */
	private String status;
	
	/**
	 * 备注
	 */
	private String remark;

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Integer getPostGoodCount() {
		return postGoodCount;
	}

	public void setPostGoodCount(Integer postGoodCount) {
		this.postGoodCount = postGoodCount;
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
		return "TopicVO {topicId=" + topicId + ", nodeId=" + nodeId + ", nodeName=" + nodeName + ", userId=" + userId
				+ ", userName=" + userName + ", title=" + title + ", content=" + content + ", excerpt=" + excerpt
				+ ", avatar=" + avatar + ", url=" + url + ", top=" + top + ", good=" + good + ", viewCount=" + viewCount
				+ ", shareCount=" + shareCount + ", goodCount=" + goodCount + ", postGoodCount=" + postGoodCount
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", type=" + type + ", status=" + status
				+ ", remark=" + remark + "}";
	}

	@Override
	public String getPrimaryKey() {
		return topicId;
	}

}
