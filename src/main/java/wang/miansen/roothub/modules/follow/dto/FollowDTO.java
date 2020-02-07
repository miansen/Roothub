package wang.miansen.roothub.modules.follow.dto;

import java.util.Date;

import wang.miansen.roothub.common.dto.BaseDTO;

/**
 * @author miansen.wang
 * @date 2020-02-07
 */
public class FollowDTO implements BaseDTO {

	private static final long serialVersionUID = 5573310575974424746L;

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
	private Date createDate;

	/**
	 * 更新时间
	 */
	private Date updateDate;

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
		return "FollowDTO {followId=" + followId + ", sourceId=" + sourceId + ", targetId=" + targetId + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "}";
	}

}