package cn.roothub.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.roothub.dao.SectionDao;
import cn.roothub.dto.SectionExecution;
import cn.roothub.entity.Section;
import cn.roothub.enums.InsertEnum;
import cn.roothub.enums.UpdateEnum;
import cn.roothub.exception.OperationFailedException;
import cn.roothub.exception.OperationRepeaException;
import cn.roothub.exception.OperationSystemException;
import cn.roothub.service.SectionService;

@Service
public class SectionServiceImpl implements SectionService{

	@Autowired
	private SectionDao rootSectionDao;
	/**
	 * 查询所有板块
	 */
	@Override
	public List<Section> findAll() {
		return rootSectionDao.selectAll();
	}

	/**
	 * 根据是否显示状态查询板块
	 */
	@Override
	public List<Section> findByShowStatus(Boolean showStatus) {
		return rootSectionDao.selectByShowStatus(showStatus);
	}

	/**
	 * 根据ID查询板块
	 */
	@Override
	public Section findBySectionId(Integer sectionId) {
		return rootSectionDao.selectBySectionId(sectionId);
	}

	/**
	 * 根据名称查询板块
	 */
	@Override
	public Section findBySectionName(String sectionName) {
		return rootSectionDao.selectBySectionName(sectionName);
	}

	/**
	 * 根据标签查询板块
	 */
	@Override
	public Section findBySectionTab(String sectionTab) {
		return rootSectionDao.selectByShowTab(sectionTab);
	}

	/**
	 * 添加板块
	 */
	@Transactional
	@Override
	public SectionExecution save(Section rootSection) {
		try {
			Section section = rootSectionDao.selectBySectionName(rootSection.getSectionName());
			if(section != null) {
				 throw new OperationRepeaException("板块已存在"); 
			}else {
				int insert = rootSectionDao.insert(rootSection);
				if(insert <= 0) {
					throw new OperationFailedException("添加失败！");
				}else {
					Section selectBySectionName = rootSectionDao.selectBySectionName(rootSection.getSectionName());
					return new SectionExecution(selectBySectionName.getSectionName(),InsertEnum.SUCCESS,selectBySectionName); 
				}
			}
		} catch (OperationRepeaException e1) {
			throw e1;
		} catch (OperationFailedException e2) {
			throw e2;
		} catch (Exception e) {
			throw new OperationSystemException("insert into RootSection error "+e.getMessage());
		}
	}

	/**
	 * 更新板块
	 */
	@Transactional
	@Override
	public SectionExecution update(Section rootSection) {
		try {
			Section section = rootSectionDao.selectBySectionId(rootSection.getSectionId());
			if(section == null) {
				throw new OperationFailedException("板块不存在");
			}else {
				int update = rootSectionDao.updateBySectionId(rootSection);
				if(update <= 0) {
					throw new OperationFailedException("更新失败！");
				}else {
					Section selectBySectionId = rootSectionDao.selectBySectionId(rootSection.getSectionId());
					return new SectionExecution(selectBySectionId.getSectionName(), UpdateEnum.SUCCESS,selectBySectionId);
				}
			}
		} catch (OperationFailedException e1) {
			throw e1;
		} catch (Exception e) {
			throw new OperationSystemException("update RootSection error "+e.getMessage());
		}
	}

	/**
	 * 根据ID删除板块
	 */
	@Override
	public void deleteBySectionId(Integer sectionId) {
		rootSectionDao.deleteBySectionId(sectionId);		
	}

}
