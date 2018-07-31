package cn.roothub.dto;

import cn.roothub.entity.RootSection;
import cn.roothub.enums.InsertRootSectionEnum;
import cn.roothub.enums.UpdateRootSectionEnum;

/**
 * 存储操作板块的结果
 * @author sen
 * 2018年5月9日
 * 下午6:05:31
 * TODO
 */
public class RootSectionExecution {

	private String sectionName;
	private int state;// 结果状态
	private String stateInfo;// 状态标识
	private RootSection rootSection;//成功对象
	
	public RootSectionExecution() {
		super();
	}

	/**
	 * 添加板块失败时的构造器
	 * @param sectionName
	 * @param stateEnum
	 */
	public RootSectionExecution(String sectionName,InsertRootSectionEnum stateEnum) {
		this.sectionName = sectionName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	/**
	 * 添加板块失败时的构造器
	 * @param sectionName
	 * @param stateEnum
	 */
	public RootSectionExecution(String sectionName,UpdateRootSectionEnum stateEnum) {
		this.sectionName = sectionName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	/**
	 * 添加板块成功时的构造器
	 * @param sectionName
	 * @param stateEnum
	 * @param rootSection
	 */
	public RootSectionExecution(String sectionName,InsertRootSectionEnum stateEnum,RootSection rootSection) {
		this.sectionName = sectionName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.rootSection = rootSection;
	}
	
	/**
	 * 添加板块成功时的构造器
	 * @param sectionName
	 * @param stateEnum
	 * @param rootSection
	 */
	public RootSectionExecution(String sectionName,UpdateRootSectionEnum stateEnum,RootSection rootSection) {
		this.sectionName = sectionName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.rootSection = rootSection;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
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

	public RootSection getRootSection() {
		return rootSection;
	}

	public void setRootSection(RootSection rootSection) {
		this.rootSection = rootSection;
	}

	@Override
	public String toString() {
		return "RootSectionExecution [sectionName=" + sectionName + ", state=" + state + ", stateInfo=" + stateInfo
				+ ", rootSection=" + rootSection + "]";
	}
	
}
