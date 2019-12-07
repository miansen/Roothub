package wang.miansen.roothub.modules.follow.model;

import java.util.Date;

/**
 * 关注实体
 * @author sen
 * 2018年7月1日
 * 下午8:28:39
 * TODO
 */
public class Follow {

	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 关注者ID
	 */
	private Integer uid;
	/**
	 * 被关注者ID
	 */
	private Integer fid;
	/**
	 * 创建时间
	 */
	private Date createDate;
	
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
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Follow [id=" + id + ", uid=" + uid + ", fid=" + fid + ", createDate=" + createDate + "]";
	}
	
}
