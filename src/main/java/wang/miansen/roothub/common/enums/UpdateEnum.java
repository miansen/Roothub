package wang.miansen.roothub.common.enums;

/**
 * 修改操作数据字典
 * @author sen
 * 2018年5月12日
 * 上午11:36:31
 * TODO
 */
public enum UpdateEnum {

	SUCCESS(1, "更新成功"), NO_USER(0,"更新失败"),REPEAT_USER(-1, "重复操作"),INNER_ERROR(-2, "系统异常");
	
	private int state;// 信息代码
	private String stateInfo;// 信息说明
	
	private UpdateEnum(int state,String stateInfo) {	
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
	
	public static UpdateEnum stateOf(int index) {
		for(UpdateEnum state : values()) {
			if(state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
