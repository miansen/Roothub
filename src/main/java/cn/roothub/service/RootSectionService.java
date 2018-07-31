package cn.roothub.service;

import java.util.List;

import cn.roothub.dto.RootSectionExecution;
import cn.roothub.entity.RootSection;

public interface RootSectionService {

	/**
	 * 查询所有板块
	 * @return
	 */
	List<RootSection> findAll();
	
	/**
	 * 根据是否显示板块查询
	 * @param showStatus
	 * @return
	 */
	List<RootSection> findByShowStatus(Boolean showStatus);

	/**
	 * 根据ID查询板块
	 * @param sectionId
	 * @return
	 */
	RootSection findBySectionId(Integer sectionId);
	
	/**
	 * 根据名称查询板块
	 * @param sectionName
	 * @return
	 */
	RootSection findBySectionName(String sectionName);
	
	/**
	 * 根据标签查询板块
	 * @param sectionTab
	 * @return
	 */
	RootSection findBySectionTab(String sectionTab);
	
	/**
	 * 添加板块
	 * @param rootSection
	 */
	RootSectionExecution save(RootSection rootSection);
	
	/**
	 * 更新板块
	 * @param rootSection
	 */
	RootSectionExecution update(RootSection rootSection);
	
	/**
	 * 根据ID删除板块
	 * @param rootSection
	 */
	void deleteBySectionId(Integer sectionId);
}
