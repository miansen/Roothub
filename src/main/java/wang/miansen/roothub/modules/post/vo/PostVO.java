package wang.miansen.roothub.modules.post.vo;

import wang.miansen.roothub.common.annotation.VO2DTO;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.common.vo.BaseVO;
import wang.miansen.roothub.modules.post.enums.PostTypeEnum;

/**
 * 帖子 VO
 * @author miansen.wang
 * @date 2020-01-19
 */
public class PostVO implements BaseVO {

	private static final long serialVersionUID = 4899815331871315247L;

	/**
	 * 帖子 ID
	 */
	private String postId;

	/**
	 * 节点 ID
	 */
	@VO2DTO(targets = {"nodeDTO"}, policy = ConverPolicy.ID_CONVER_DTO, service = "nodeServiceImpl")
	private String nodeId;

	/**
	 * 节点名称
	 */
	private String nodeName;

	/**
	 * 用户 ID
	 */
	@VO2DTO(targets = {"userDTO"}, policy = ConverPolicy.ID_CONVER_DTO, service = "userServiceImpl")
	private String userId;

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
	 * 评论量
	 */
	private Integer commentCount;

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
	@VO2DTO(targets = {"createDate"}, policy = ConverPolicy.STRING_CONVER_DATE)
	private String createDate;

	/**
	 * 更新时间
	 */
	@VO2DTO(targets = {"updateDate"}, policy = ConverPolicy.STRING_CONVER_DATE)
	private String updateDate;

	/**
	 * 类型：文本、图片、视频、链接
	 */
	@VO2DTO(targets = {"postTypeEnum"}, policy = ConverPolicy.DESC_CONVER_ENUM, enumClass = PostTypeEnum.class)
	private String type;

	/**
	 * 状态：有效、无效、未生效
	 */
	private String status;

	/**
	 * 备注
	 */
	private String remark;

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
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

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
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
	public String getPrimaryKey() {
		return postId;
	}
	
	@Override
	public void setPrimaryKey(String primaryKey) {
		this.postId = primaryKey;
	}

	@Override
	public String toString() {
		return "PostVO {postId=" + postId + ", nodeId=" + nodeId + ", nodeName=" + nodeName + ", userId=" + userId
				+ ", userName=" + userName + ", title=" + title + ", content=" + content + ", excerpt=" + excerpt
				+ ", avatar=" + avatar + ", url=" + url + ", top=" + top + ", good=" + good + ", viewCount=" + viewCount
				+ ", commentCount=" + commentCount + ", shareCount=" + shareCount + ", goodCount=" + goodCount
				+ ", postGoodCount=" + postGoodCount + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", type=" + type + ", status=" + status + ", remark=" + remark + "}";
	}

}