package wang.miansen.roothub.modules.notice.model;

import java.util.Date;

/**
 * 回复实体
 * Table: root_notice
 * @author sen
 * 2018年5月5日
 * 下午9:39:24
 * TODO
 */
public class Notice {

	/**
	 * 消息通知标识
	 */
	private Integer noticeId;
	
	/**
	 * 通知标题
	 */
	private String noticeTitle;
	
	/**
	 * 是否已读：0:未读 1:已读
	 */
	private Boolean isRead;
	
	/**
	 * 发起通知用户id
	 */
	private Integer noticeAuthorId;
	
	/**
	 * 发起通知用户昵称
	 */
	private String noticeAuthorName;
	
	/**
	 * 要通知用户id
	 */
	private Integer targetAuthorId;
	
	/**
	 * 要通知用户的昵称
	 */
	private String targetAuthorName;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 阅读时间
	 */
	private Date updateDate;
	
	/**
	 * 通知动作
	 */
	private String noticeAction;
	
	/**
	 * 话题id
	 */
	private Integer topicId;
	
	/**
	 * 话题作者id
	 */
	private Integer topicAuthorId;
	
	/**
	 * 话题板块id
	 */
	private Integer topicSectionId;
	
	/**
	 * 通知内容
	 */
	private String noticeContent;
	
	/**
	 * 通知状态 1000:有效 1100:无效 1200:未生效
	 */
	private String statusCd;
	
	/**
	 * 要通知的主题
	 */
	private String title;
	
	/**
	 * 发起通知用户的头像
	 */
	private String avatar;	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Integer getNoticeAuthorId() {
		return noticeAuthorId;
	}

	public void setNoticeAuthorId(Integer noticeAuthorId) {
		this.noticeAuthorId = noticeAuthorId;
	}

	public String getNoticeAuthorName() {
		return noticeAuthorName;
	}

	public void setNoticeAuthorName(String noticeAuthorName) {
		this.noticeAuthorName = noticeAuthorName;
	}

	public Integer getTargetAuthorId() {
		return targetAuthorId;
	}

	public void setTargetAuthorId(Integer targetAuthorId) {
		this.targetAuthorId = targetAuthorId;
	}

	public String getTargetAuthorName() {
		return targetAuthorName;
	}

	public void setTargetAuthorName(String targetAuthorName) {
		this.targetAuthorName = targetAuthorName;
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

	public String getNoticeAction() {
		return noticeAction;
	}

	public void setNoticeAction(String noticeAction) {
		this.noticeAction = noticeAction;
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

	public Integer getTopicSectionId() {
		return topicSectionId;
	}

	public void setTopicSectionId(Integer topicSectionId) {
		this.topicSectionId = topicSectionId;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	@Override
	public String toString() {
		return "RootNotice [noticeId=" + noticeId + ", noticeTitle=" + noticeTitle + ", isRead=" + isRead
				+ ", noticeAuthorId=" + noticeAuthorId + ", noticeAuthorName=" + noticeAuthorName + ", targetAuthorId="
				+ targetAuthorId + ", targetAuthorName=" + targetAuthorName + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", noticeAction=" + noticeAction + ", topicId=" + topicId
				+ ", topicAuthorId=" + topicAuthorId + ", topicSectionId=" + topicSectionId + ", noticeContent="
				+ noticeContent + ", statusCd=" + statusCd + ", title=" + title + ", avatar=" + avatar + "]";
	}

}
