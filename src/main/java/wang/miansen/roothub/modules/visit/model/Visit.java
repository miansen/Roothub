package wang.miansen.roothub.modules.visit.model;

import java.util.Date;

import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.dao.mapper.enums.IdType;
import wang.miansen.roothub.common.entity.BaseDO;

/**
 * 访问记录
 * @author sen
 * 2018年8月4日
 * 上午11:28:01
 * TODO
 */
@Table(value = "visit")
public class Visit implements BaseDO {

	private static final long serialVersionUID = 160283767535840192L;

	/**
	 * 访问ID
	 */
	@Id(value = "visit_id", type = IdType.UUID)
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
		return "Visit {visitId=" + visitId + ", sourceId=" + sourceId + ", targetId=" + targetId + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "}";
	}

}