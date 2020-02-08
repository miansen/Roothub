package wang.miansen.roothub.modules.comment.model;

import java.util.Date;

import wang.miansen.roothub.common.entity.BaseDO;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.dao.mapper.annotation.Id;

/**
 * 评论实体类
 * @author miansen.wang
 * @date 2018-05-05 21:30:23
 * @since 1.0
 * @version 3.0
 */
@Table("comment")
public class Comment implements BaseDO {

	private static final long serialVersionUID = -6899805232180464418L;

	/**
	 * 评论ID
	 */
	@Id("comment_id")
	private String commentId;

	/**
	 * 帖子ID
	 */
	private String postId;

	/**
	 * 用户ID
	 */
	private String userId;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
		return "Comment {commentId=" + commentId + ", postId=" + postId + ", userId=" + userId + ", content="
				+ content + ", viewCount=" + viewCount + ", shareCount=" + shareCount + ", goodCount=" + goodCount
				+ ", badCount=" + badCount + ", createDate=" + createDate + ", updateDate=" + updateDate + ", type="
				+ type + ", status=" + status + ", remark=" + remark + "}";
	}

}