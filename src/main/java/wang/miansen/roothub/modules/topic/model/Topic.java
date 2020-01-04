package wang.miansen.roothub.modules.topic.model;

import java.util.Date;

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
	 * 话题标识
	 */
	private Integer topicId;
	
	/**
	 * 父版块标识
	 */
	private String ptab;
	
	/**
	 * 版块标识
	 */
	private String tab;
	
	/**
	 * 话题标题
	 */
	private String title;
	
	/**
	 * 话题内容标签
	 */
	private String tag;
	
	/**
	 * 话题内容
	 */
	private String content;
	
	/**
	 * 摘录
	 */
	private String excerpt;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 更新时间
	 */
	private Date updateDate;
	
	/**
	 * 最后回复话题时间，用于排序
	 */
	private Date lastReplyTime;
	
	/**
	 * 最后回复话题的用户id
	 */
	private String lastReplyAuthor;
	
	/**
	 * 浏览量
	 */
	private Integer viewCount;
	
	/**
	 * 话题作者id
	 */
	private String author;
	
	/**
	 * 1置顶 0默认
	 */
	private Boolean top;
	
	/**
	 * 1精华 0默认
	 */
	private Boolean good;
	
	/**
	 * 1显示 0不显示
	 */
	private Boolean showStatus;
	
	/**
	 * 回复数量
	 */
	private Integer replyCount;
	
	/**
	 * 1删除 0默认
	 */
	private Boolean isDelete;
	
	/**
	 * 话题内容标签是否被统计过 1是 0否默认
	 */
	private Boolean tagIsCount;
	
	/**
	 * 点赞
	 */
	private Integer postGoodCount;
	
	/**
	 * 踩数
	 */
	private Integer postBadCount;
	
	/**
	 * 话题状态 1000:有效 1100:无效 1200:未生效
	 */
	private String statusCd;
	
	/**
	 * 所属节点
	 */
	private String nodeSlug;
	
	/**
	 * 节点名称
	 */
	private String nodeTitle;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 话题作者头像
	 */
	private String avatar;
	
	private String url;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	public Date getLastReplyTime() {
		return lastReplyTime;
	}

	public void setLastReplyTime(Date lastReplyTime) {
		this.lastReplyTime = lastReplyTime;
	}

	public String getLastReplyAuthor() {
		return lastReplyAuthor;
	}

	public void setLastReplyAuthor(String lastReplyAuthor) {
		this.lastReplyAuthor = lastReplyAuthor;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public Boolean getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(Boolean showStatus) {
		this.showStatus = showStatus;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Boolean getTagIsCount() {
		return tagIsCount;
	}

	public void setTagIsCount(Boolean tagIsCount) {
		this.tagIsCount = tagIsCount;
	}

	public Integer getPostGoodCount() {
		return postGoodCount;
	}

	public void setPostGoodCount(Integer postGoodCount) {
		this.postGoodCount = postGoodCount;
	}

	public Integer getPostBadCount() {
		return postBadCount;
	}

	public void setPostBadCount(Integer postBadCount) {
		this.postBadCount = postBadCount;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public String getNodeSlug() {
		return nodeSlug;
	}

	public void setNodeSlug(String nodeSlug) {
		this.nodeSlug = nodeSlug;
	}

	public String getNodeTitle() {
		return nodeTitle;
	}

	public void setNodeTitle(String nodeTitle) {
		this.nodeTitle = nodeTitle;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPtab() {
		return ptab;
	}

	public void setPtab(String ptab) {
		this.ptab = ptab;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", ptab=" + ptab + ", tab=" + tab + ", title=" + title + ", tag=" + tag
				+ ", content=" + content + ", excerpt=" + excerpt + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", lastReplyTime=" + lastReplyTime + ", lastReplyAuthor=" + lastReplyAuthor
				+ ", viewCount=" + viewCount + ", author=" + author + ", top=" + top + ", good=" + good
				+ ", showStatus=" + showStatus + ", replyCount=" + replyCount + ", isDelete=" + isDelete
				+ ", tagIsCount=" + tagIsCount + ", postGoodCount=" + postGoodCount + ", postBadCount=" + postBadCount
				+ ", statusCd=" + statusCd + ", nodeSlug=" + nodeSlug + ", nodeTitle=" + nodeTitle + ", remark="
				+ remark + ", avatar=" + avatar + ", url=" + url + "]";
	}
}
