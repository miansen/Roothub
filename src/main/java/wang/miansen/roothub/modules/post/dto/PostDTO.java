package wang.miansen.roothub.modules.post.dto;

import java.util.Date;

import wang.miansen.roothub.common.annotation.DTO2DO;
import wang.miansen.roothub.common.annotation.DTO2VO;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.modules.node.dto.NodeDTO;
import wang.miansen.roothub.modules.post.enums.PostTypeEnum;
import wang.miansen.roothub.modules.user.dto.UserDTO;

/**
 * 帖子 DTO
 * @author miansen.wang
 * @date 2020-01-19
 */
public class PostDTO implements BaseDTO {

	private static final long serialVersionUID = 2051297348577358951L;

	/**
	 * 帖子ID
	 */
	private String postId;
	
	/**
	 * 节点
	 */
	@DTO2DO(targets = {"nodeId"}, policy = ConverPolicy.COPY_PROPERTIES)
	@DTO2VO(targets = {"nodeId", "nodeName"}, policy = ConverPolicy.COPY_PROPERTIES)
	private NodeDTO nodeDTO;
	
	/**
	 * 作者
	 */
	@DTO2DO(targets = {"userId"}, policy = ConverPolicy.COPY_PROPERTIES)
	@DTO2VO(targets = {"userId", "userName"}, policy = ConverPolicy.COPY_PROPERTIES)
	private UserDTO userDTO;
	
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
	private Integer badCount;
	
	/**
	 * 创建时间
	 */
	@DTO2VO(targets = {"createDate"}, policy = ConverPolicy.DATE_CONVER_STRING)
	private Date createDate;
	
	/**
	 * 更新时间
	 */
	@DTO2VO(targets = {"updateDate"}, policy = ConverPolicy.DATE_CONVER_STRING)
	private Date updateDate;
	
	/**
	 * 类型，1000（文本）、1100（图片）、1200（视频）、1300（链接）
	 */
	private Integer type;
	
	/**
	 * 状态，1000（有效）、1100（无效）、1200（未生效）
	 */
	private Integer status;
	
	@DTO2DO(targets = {"type"}, policy = ConverPolicy.ENUM_CONVER_CODE)
	@DTO2VO(targets = {"type"}, policy = ConverPolicy.ENUM_CONVER_DESC)
	private PostTypeEnum postTypeEnum;
	
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

	public NodeDTO getNodeDTO() {
		return nodeDTO;
	}

	public void setNodeDTO(NodeDTO nodeDTO) {
		this.nodeDTO = nodeDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public PostTypeEnum getPostTypeEnum() {
		return postTypeEnum;
	}

	public void setPostTypeEnum(PostTypeEnum postTypeEnum) {
		this.postTypeEnum = postTypeEnum;
	}

	@Override
	public String toString() {
		return "PostDTO {postId=" + postId + ", nodeDTO=" + nodeDTO + ", userDTO=" + userDTO + ", title=" + title
				+ ", content=" + content + ", excerpt=" + excerpt + ", avatar=" + avatar + ", url=" + url + ", top="
				+ top + ", good=" + good + ", viewCount=" + viewCount + ", commentCount=" + commentCount
				+ ", shareCount=" + shareCount + ", goodCount=" + goodCount + ", badCount=" + badCount + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", type=" + type + ", status=" + status + ", remark="
				+ remark + "}";
	}

}