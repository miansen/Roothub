package wang.miansen.roothub.modules.reply.model;

import java.util.Date;

/**
 * 根据评论人昵称关联话题表查询全部评论
 * @author sen
 * 2018年6月3日
 * 下午5:55:06
 * TODO
 */
public class ReplyAndTopicByName {

	private Date createDate;//评论时间
	private String author;//话题作者的昵称
	private Integer topicId;//话题ID
	private String title;//话题的标题
	private String replyContent;//评论的内容
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	@Override
	public String toString() {
		return "ReplyAndTopicByName [createDate=" + createDate + ", author=" + author + ", topicId=" + topicId
				+ ", title=" + title + ", replyContent=" + replyContent + "]";
	}
	
}
