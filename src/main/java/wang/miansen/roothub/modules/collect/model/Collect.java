package wang.miansen.roothub.modules.collect.model;

import java.util.Date;

/**
 * 收藏实体
 * @author sen
 * 2018年6月29日
 * 下午5:15:14
 * TODO
 */
public class Collect {

	/**
	 * 唯一标识
	 */
	private Integer id;
	/**
	 * 用户ID
	 */
	private Integer uid;
	/**
	 * 主题ID
	 */
	private Integer tid;
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
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Collect [id=" + id + ", uid=" + uid + ", tid=" + tid + ", createDate=" + createDate + "]";
	}
	
}
