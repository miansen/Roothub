package wang.miansen.roothub.modules.collect.vo;

import wang.miansen.roothub.common.vo.BaseVO;

/**
 * @author miansen.wang
 * @date 2020-02-07
 */
public class CollectVO implements BaseVO {

	private static final long serialVersionUID = -3971022078121882212L;

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
	private String createDate;

	/**
	 * 更新时间
	 */
	private String updateDate;


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


	@Override
	public String getPrimaryKey() {
		return collectId;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		this.collectId = primaryKey;
	}

	@Override
	public String toString() {
		return "CollectVO {collectId=" + collectId + ", userId=" + userId + ", postId=" + postId + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "}";
	}

}