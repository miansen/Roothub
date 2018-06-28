package priv.sen.root.enums;

/**
 * 修改用户的数据字典
 * @author sen
 * 2018年5月7日
 * 下午9:28:07
 * TODO
 */
public enum UpdateRootUserEnum {

	SUCCESS(1, "修改成功"), NO_USER(0,"修改失败"),REPEAT_USER(-1, "重复操作"),INNER_ERROR(-2, "系统异常");
	
	private int state;// 信息代码
	private String stateInfo;// 信息说明
	
	private UpdateRootUserEnum(int state,String stateInfo) {	
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	
	public static UpdateRootUserEnum stateOf(int index) {
		for(UpdateRootUserEnum state : values()) {
			if(state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
