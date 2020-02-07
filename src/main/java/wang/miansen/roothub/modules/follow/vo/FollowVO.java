package wang.miansen.roothub.modules.follow.vo;

import wang.miansen.roothub.common.vo.BaseVO;

/**
 * @author miansen.wang
 * @date 2020-02-07
 */
public class FollowVO implements BaseVO {

	private static final long serialVersionUID = -8919635128894048822L;

	/**
	 * 关注ID
	 */
	private String followId;

	/**
	 * 关注者ID
	 */
	private String sourceId;

	/**
	 * 被关注者ID
	 */
	private String targetId;

	/**
	 * 创建时间
	 */
	private String createDate;

	/**
	 * 更新时间
	 */
	private String updateDate;

	public String getFollowId() {
		return followId;
	}


	public void setFollowId(String followId) {
		this.followId = followId;
	}


	public String getSourceId() {
		return sourceId;
	}


	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}


	public String getTargetId() {
		return targetId;
	}


	public void setTargetId(String targetId) {
		this.targetId = targetId;
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
		return followId;
	}


	@Override
	public String toString() {
		return "FollowVO {followId=" + followId + ", sourceId=" + sourceId + ", targetId=" + targetId + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "}";
	}

}