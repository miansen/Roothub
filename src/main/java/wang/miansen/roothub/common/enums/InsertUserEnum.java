package wang.miansen.roothub.common.enums;

/**
 * 注册用户的数据字典
 * @author sen
 * 2018年5月6日
 * 下午9:52:11
 * TODO
 */
public enum InsertUserEnum {

	SUCCESS(1, "注册成功"), NO_USER(0,"注册失败"),REPEAT_USER(-1, "重复注册"),INNER_ERROR(-2, "系统异常");
	
	private int state;// 信息代码
	private String stateInfo;// 信息说明
	
	private InsertUserEnum(int state,String stateInfo) {
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
	
	public static InsertUserEnum stateOf(int index) {
		for(InsertUserEnum state : values()) {
			if(state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
