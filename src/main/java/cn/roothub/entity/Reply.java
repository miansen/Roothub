package cn.roothub.entity;

import java.util.Date;

/**
 * 评论实体
 * Table: root_reply
 * @author sen
 * 2018年5月5日
 * 下午9:30:23
 * TODO
 */
public class Reply {

	/**
	 * 回复标识
	 */
	private Integer replyId;
	
	/**
	 * 话题id
	 */
	private Integer topicId;
	
	/**
	 * 话题作者id
	 */
	private Integer topicAuthorId;
	
	/**
	 * 回复内容
	 */
	private String replyContent;
	
	/**
	 * 回复时间
	 */
	private Date createDate;
	
	/**
	 * 更新时间
	 */
	private Date updateDate;
	
	/**
	 * 当前回复用户id
	 */
	private Integer replyAuthorId;
	
	/**
	 * 当前回复用户昵称
	 */
	private String replyAuthorName;
	
	/**
	 * 是否删除 0:默认 1:删除 2:其他
	 */
	private Boolean isDelete;
	
	/**
	 * 是否已读 0:已读 1:未读 2:其他
	 */
	private Boolean isRead;
	
	/**
	 * 是否可见 0:可见 1:不可见 2:其他
	 */
	private Boolean isShow;
	
	/**
	 * 点赞
	 */
	private Integer replyGoodCount;
	
	/**
	 * 踩数
	 */
	private Integer replyBadCount;
	
	/**
	 * 回复类型
	 */
	private String replyType;
	
	/**
	 * 回复阅读数量
	 */
	private Integer replyReadCount;
	
	/**
	 * 回复状态 1000:有效 1100:无效 1200:未生效
	 */
	private String statusCd;
	
	/**
	 * 回复人的头像
	 */
	private String avatar;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public Integer getTopicAuthorId() {
		return topicAuthorId;
	}

	public void setTopicAuthorId(Integer topicAuthorId) {
		this.topicAuthorId = topicAuthorId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
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

	public Integer getReplyAuthorId() {
		return replyAuthorId;
	}

	public void setReplyAuthorId(Integer replyAuthorId) {
		this.replyAuthorId = replyAuthorId;
	}

	public String getReplyAuthorName() {
		return replyAuthorName;
	}

	public void setReplyAuthorName(String replyAuthorName) {
		this.replyAuthorName = replyAuthorName;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	public Integer getReplyGoodCount() {
		return replyGoodCount;
	}

	public void setReplyGoodCount(Integer replyGoodCount) {
		this.replyGoodCount = replyGoodCount;
	}

	public Integer getReplyBadCount() {
		return replyBadCount;
	}

	public void setReplyBadCount(Integer replyBadCount) {
		this.replyBadCount = replyBadCount;
	}

	public String getReplyType() {
		return replyType;
	}

	public void setReplyType(String replyType) {
		this.replyType = replyType;
	}

	public Integer getReplyReadCount() {
		return replyReadCount;
	}

	public void setReplyReadCount(Integer replyReadCount) {
		this.replyReadCount = replyReadCount;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	@Override
	public String toString() {
		return "Reply [replyId=" + replyId + ", topicId=" + topicId + ", topicAuthorId=" + topicAuthorId
				+ ", replyContent=" + replyContent + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", replyAuthorId=" + replyAuthorId + ", replyAuthorName=" + replyAuthorName + ", isDelete=" + isDelete
				+ ", isRead=" + isRead + ", isShow=" + isShow + ", replyGoodCount=" + replyGoodCount
				+ ", replyBadCount=" + replyBadCount + ", replyType=" + replyType + ", replyReadCount=" + replyReadCount
				+ ", statusCd=" + statusCd + ", avatar=" + avatar + "]";
	}
	
}
