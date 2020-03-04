package wang.miansen.roothub.modules.visit.vo;

import wang.miansen.roothub.common.vo.BaseVO;

/**
 * @author miansen.wang
 * @date 2020-02-07
 */
public class VisitVO implements BaseVO {

	private static final long serialVersionUID = -5653061817547475903L;

	/**
	 * 访问ID
	 */
	private String visitId;

	/**
	 * 访问者ID
	 */
	private String sourceId;

	/**
	 * 被访问者ID
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


	public String getVisitId() {
		return visitId;
	}


	public void setVisitId(String visitId) {
		this.visitId = visitId;
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
		return visitId;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		this.visitId = primaryKey;
	}

	@Override
	public String toString() {
		return "VisitVO {visitId=" + visitId + ", sourceId=" + sourceId + ", targetId=" + targetId + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "}";
	}

}