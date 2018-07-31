package cn.roothub.entity;

/**
 * 标签实体
 * @author sen
 * 2018年6月3日
 * 下午8:09:32
 * TODO
 */
public class Tag {

	private String tag;//标签的名字
	private Integer number;//标签的数量
	public String gettag() {
		return tag;
	}
	public void settag(String tag) {
		this.tag = tag;
	}
	public Integer getnumber() {
		return number;
	}
	public void setnumber(Integer number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "Tag [tag=" + tag + ", number=" + number + "]";
	}
	
}
