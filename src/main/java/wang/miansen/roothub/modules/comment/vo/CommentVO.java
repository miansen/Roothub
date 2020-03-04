package wang.miansen.roothub.modules.comment.vo;

import wang.miansen.roothub.common.vo.BaseVO;

/**
 * 评论 VO
 * 
 * @author miansen.wang
 * @date 2020-02-06
 * @since 3.0
 */
public class CommentVO implements BaseVO {

	private static final long serialVersionUID = -2792330525102558357L;

	/**
	 * 评论ID
	 */
	private String commentId;

	/**
	 * 帖子 ID
	 */
	private String postId;

	/**
	 * 帖子名称
	 */
	private String postName;
	
	/**
	 * 帖子封面
	 */
	private String postAvatar;

	/**
	 * 用户 ID
	 */
	private String userId;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 用户头像
	 */
	private String userAvatar;
	
	/**
	 * 评论内容
	 */
	private String content;

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
	private String createDate;

	/**
	 * 更新时间
	 */
	private String updateDate;

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

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}
	
	public String getPostAvatar() {
		return postAvatar;
	}

	public void setPostAvatar(String postAvatar) {
		this.postAvatar = postAvatar;
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
	
	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String getPrimaryKey() {
		return commentId;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		this.commentId = primaryKey;
	}
	
	@Override
	public String toString() {
		return "CommentVO {commentId=" + commentId + ", postId=" + postId + ", postName=" + postName + ", postAvatar="
				+ postAvatar + ", userId=" + userId + ", userName=" + userName + ", userAvatar=" + userAvatar
				+ ", content=" + content + ", viewCount=" + viewCount + ", shareCount=" + shareCount + ", goodCount="
				+ goodCount + ", badCount=" + badCount + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", type=" + type + ", status=" + status + ", remark=" + remark + "}";
	}

}