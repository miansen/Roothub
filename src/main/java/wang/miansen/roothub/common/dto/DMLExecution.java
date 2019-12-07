package wang.miansen.roothub.common.dto;

import wang.miansen.roothub.common.enums.InsertEnum;
import wang.miansen.roothub.common.enums.UpdateEnum;
/**
 * 存储增删改操作的结果
 * @author sen
 * 2018年8月4日
 * 下午3:19:48
 * TODO
 */
public class DMLExecution {

	private String type;//操作类型
	private int state;// 结果状态
	private String stateInfo;// 状态标识
	private Object data;//成功对象
	
	private DMLExecution(){}
	
	/**
	 * 添加失败时的构造器
	 * @param type
	 * @param stateEnum
	 */
	public DMLExecution(String type,InsertEnum stateEnum) {
		this.type = type;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	/**
	 * 更新失败时的构造器
	 * @param type
	 * @param stateEnum
	 */
	public DMLExecution(String type,UpdateEnum stateEnum) {
		this.type = type;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	/**
	 * 添加成功时的构造器
	 * @param type
	 * @param stateEnum
	 * @param data
	 */
	public DMLExecution(String type,InsertEnum stateEnum,Object data) {
		this.type = type;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.data = data;
	}
	
	/**
	 * 更新成功时的构造器
	 * @param type
	 * @param stateEnum
	 * @param data
	 */
	public DMLExecution(String type,UpdateEnum stateEnum,Object data) {
		this.type = type;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.data = data;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "InsertExecution [type=" + type + ", state=" + state + ", stateInfo=" + stateInfo + ", data=" + data
				+ "]";
	}
	
}
