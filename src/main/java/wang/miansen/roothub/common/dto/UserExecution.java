package wang.miansen.roothub.common.dto;

import wang.miansen.roothub.modules.user.model.User;
import wang.miansen.roothub.common.enums.InsertUserEnum;
import wang.miansen.roothub.common.enums.UpdateUserEnum;

/**
 * 存储操作用户的结果
 * @author sen
 * 2018年5月6日
 * 下午9:34:13
 * TODO
 */
public class UserExecution {

	private String userName;//昵称
	private String password;//密码
	private String email;//邮箱
	private int state;// 结果状态
	private String stateInfo;// 状态标识
	private User user;//成功对象
	
	public UserExecution() {
		super();
	}
	
	/**
	 * 注册失败时的构造器
	 * @param userName
	 * @param stateEnum
	 */
	public UserExecution(String userName,InsertUserEnum stateEnum) {
		this.userName = userName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	} 
	
	/**
	 * 更新失败时的构造器
	 * @param userName
	 * @param stateEnum
	 */
	public UserExecution(String userName,UpdateUserEnum stateEnum) {
		this.userName = userName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	} 
	
	/**
	 * 注册成功时的构造器
	 * @param userName
	 * @param stateEnum
	 * @param user
	 */
	public UserExecution(String userName,InsertUserEnum stateEnum,User user) {
		this.userName = userName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.user = user;
	}
	
	/**
	 * 更新成功时的构造器
	 * @param userName
	 * @param stateEnum
	 * @param user
	 */
	public UserExecution(String userName,UpdateUserEnum stateEnum,User user) {
		this.userName = userName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "RootUserExecution [userName=" + userName + ", password=" + password + ", email=" + email + ", state="
				+ state + ", stateInfo=" + stateInfo + ", user=" + user + "]";
	}
	
}
