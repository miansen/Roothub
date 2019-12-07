package wang.miansen.roothub.modules.visit.model;

import java.util.Date;

/**
 * 访问记录
 * @author sen
 * 2018年8月4日
 * 上午11:28:01
 * TODO
 */
public class Visit {

	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * 访问者ID
	 */
	private Integer uid;
	
	/**
	 * 被访问者ID
	 */
	private Integer vid;
	
	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 是否删除 false 否 true 是
	 */
	private boolean isDelete;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "Visit [id=" + id + ", uid=" + uid + ", vid=" + vid + ", createDate=" + createDate + ", isDelete="
				+ isDelete + "]";
	}
	
}
