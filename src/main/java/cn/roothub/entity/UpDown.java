package cn.roothub.entity;

import java.util.Date;

/**
 * 点赞
 * @author sen
 * 2018年8月11日
 * 上午10:31:00
 * TODO
 */
public class UpDown {

	/**
	 * 主键ID
	 */
	private Integer id;
	
	/**
	 * 用户ID
	 */
	private Integer uid;
	
	/**
	 * 话题ID
	 */
	private Integer tid;
	
	/**
	 * true:up false:down
	 */
	private boolean upDown;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 是否删除 true:否  false:是
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

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public boolean isUpDown() {
		return upDown;
	}

	public void setUpDown(boolean upDown) {
		this.upDown = upDown;
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
		return "UpDown [id=" + id + ", uid=" + uid + ", tid=" + tid + ", upDown=" + upDown + ", createDate="
				+ createDate + ", isDelete=" + isDelete + "]";
	}
	
}
