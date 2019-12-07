package wang.miansen.roothub.common.enums;

/**
 * 发布话题的数据字典
 * @author sen
 * 2018年5月7日
 * 下午11:57:22
 * TODO
 */
public enum InsertTopicEnum {

	SUCCESS(1, "发布成功"), NO_USER(0,"禁用状态"),REPEAT_USER(-1, "发布失败"),INNER_ERROR(-2, "系统异常");

	private int state;// 信息代码
	private String stateInfo;// 信息说明
	
	private InsertTopicEnum(int state,String stateInfo) {
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
	
	public static InsertTopicEnum stateOf(int index) {
		for(InsertTopicEnum state : values()) {
			if(state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
