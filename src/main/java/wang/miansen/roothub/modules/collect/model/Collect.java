package wang.miansen.roothub.modules.collect.model;

import java.util.Date;

import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.entity.BaseDO;

/**
 * 收藏实体
 * @author sen
 * 2018年6月29日
 * 下午5:15:14
 * TODO
 */
@Table("collect")
public class Collect implements BaseDO {

	private static final long serialVersionUID = 8711880740944927277L;
	
	/**
	 * 收藏ID
	 */
	@Id("collect_id")
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
		return "Collect {collectId=" + collectId + ", userId=" + userId + ", postId=" + postId + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "}";
	}
	
}