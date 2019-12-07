package wang.miansen.roothub.common.dto;

import wang.miansen.roothub.modules.node.model.NodeTab;
import wang.miansen.roothub.common.enums.InsertEnum;
import wang.miansen.roothub.common.enums.UpdateEnum;

/**
 * 存储操作板块的结果
 * @author sen
 * 2018年5月9日
 * 下午6:05:31
 * TODO
 */
public class SectionExecution {

	private String sectionName;
	private int state;// 结果状态
	private String stateInfo;// 状态标识
	private NodeTab rootSection;//成功对象
	
	public SectionExecution() {
		super();
	}

	/**
	 * 添加板块失败时的构造器
	 * @param sectionName
	 * @param stateEnum
	 */
	public SectionExecution(String sectionName,InsertEnum stateEnum) {
		this.sectionName = sectionName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	/**
	 * 更新板块失败时的构造器
	 * @param sectionName
	 * @param stateEnum
	 */
	public SectionExecution(String sectionName,UpdateEnum stateEnum) {
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
	public SectionExecution(String sectionName,InsertEnum stateEnum,NodeTab rootSection) {
		this.sectionName = sectionName;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.rootSection = rootSection;
	}
	
	/**
	 * 更新板块成功时的构造器
	 * @param sectionName
	 * @param stateEnum
	 * @param rootSection
	 */
	public SectionExecution(String sectionName,UpdateEnum stateEnum,NodeTab rootSection) {
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

	public NodeTab getRootSection() {
		return rootSection;
	}

	public void setRootSection(NodeTab rootSection) {
		this.rootSection = rootSection;
	}

	@Override
	public String toString() {
		return "RootSectionExecution [sectionName=" + sectionName + ", state=" + state + ", stateInfo=" + stateInfo
				+ ", rootSection=" + rootSection + "]";
	}
	
}
