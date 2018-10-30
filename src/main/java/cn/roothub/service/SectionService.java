package cn.roothub.service;

import java.util.List;

import cn.roothub.dto.SectionExecution;
import cn.roothub.entity.Section;

public interface SectionService {

	/**
	 * 查询所有板块
	 * @return
	 */
	List<Section> findAll();
	
	/**
	 * 根据是否显示板块查询
	 * @param showStatus
	 * @return
	 */
	List<Section> findByShowStatus(Boolean showStatus);

	/**
	 * 根据ID查询板块
	 * @param sectionId
	 * @return
	 */
	Section findBySectionId(Integer sectionId);
	
	/**
	 * 根据名称查询板块
	 * @param sectionName
	 * @return
	 */
	Section findBySectionName(String sectionName);
	
	/**
	 * 根据标签查询板块
	 * @param sectionTab
	 * @return
	 */
	Section findBySectionTab(String sectionTab);
	
	/**
	 * 添加板块
	 * @param rootSection
	 */
	SectionExecution save(Section rootSection);
	
	/**
	 * 更新板块
	 * @param rootSection
	 */
	SectionExecution update(Section rootSection);
	
	/**
	 * 根据ID删除板块
	 * @param rootSection
	 */
	void deleteBySectionId(Integer sectionId);
}
