package wang.miansen.roothub.modules.collect.dto;

import java.util.Date;

import wang.miansen.roothub.common.dto.BaseDTO;

/**
 * @author miansen.wang
 * @date 2020-02-07
 */
public class CollectDTO implements BaseDTO {

	private static final long serialVersionUID = -3656304318261444264L;

	/**
	 * 收藏ID
	 */
	private String collectId;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 主题ID
	 */
	private String postId;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 更新时间
	 */
	private Date updateDate;

	public String getCollectId() {
		return collectId;
	}

	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
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

	@Override
	public String toString() {
		return "CollectDTO {collectId=" + collectId + ", userId=" + userId + ", postId=" + postId + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "}";
	}

}