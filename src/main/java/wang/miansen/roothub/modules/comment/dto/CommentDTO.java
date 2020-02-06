package wang.miansen.roothub.modules.comment.dto;

import java.util.Date;

import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.modules.post.dto.PostDTO;
import wang.miansen.roothub.modules.user.dto.UserDTO;

/**
 * 评论 DTO
 * 
 * @author miansen.wang
 * @date 2020-02-06
 * @since 3.0
 */
public class CommentDTO implements BaseDTO {

	private static final long serialVersionUID = -1994196383929018721L;

	/**
	 * 评论ID
	 */
	private String commentId;

	/**
	 * 帖子
	 */
	private PostDTO postDTO;

	/**
	 * 用户
	 */
	private UserDTO userDTO;

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

	public PostDTO getPostDTO() {
		return postDTO;
	}

	public void setPostDTO(PostDTO postDTO) {
		this.postDTO = postDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
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
		return "CommentDTO {commentId=" + commentId + ", postDTO=" + postDTO + ", userDTO=" + userDTO + ", content="
				+ content + ", viewCount=" + viewCount + ", shareCount=" + shareCount + ", goodCount=" + goodCount
				+ ", badCount=" + badCount + ", createDate=" + createDate + ", updateDate=" + updateDate + ", type="
				+ type + ", status=" + status + ", remark=" + remark + "}";
	}

}