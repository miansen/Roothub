package wang.miansen.roothub.modules.follow.model;

import java.util.Date;

import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.entity.BaseDO;

/**
 * 关注实体
 * @author sen
 * 2018年7月1日
 * 下午8:28:39
 * TODO
 */
@Table("follow")
public class Follow implements BaseDO {

	private static final long serialVersionUID = -7347985930759632454L;

	/**
	 * 关注ID
	 */
	@Id("follow_id")
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
		return "Follow {followId=" + followId + ", sourceId=" + sourceId + ", targetId=" + targetId + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "}";
	}

}