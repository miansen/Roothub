package wang.miansen.roothub.modules.visit.dto;

import java.util.Date;

import wang.miansen.roothub.common.dto.BaseDTO;

/**
 * @author miansen.wang
 * @date 2020-02-07
 */
public class VisitDTO implements BaseDTO {

	private static final long serialVersionUID = -1824160488403048889L;

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
	private Date createDate;

	/**
	 * 更新时间
	 */
	private Date updateDate;

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
		return "VisitDTO {visitId=" + visitId + ", sourceId=" + sourceId + ", targetId=" + targetId + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "}";
	}

}