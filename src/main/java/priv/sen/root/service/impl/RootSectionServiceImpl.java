package priv.sen.root.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.sen.root.dao.RootSectionDao;
import priv.sen.root.dto.RootSectionExecution;
import priv.sen.root.entity.RootSection;
import priv.sen.root.enums.InsertRootSectionEnum;
import priv.sen.root.enums.UpdateRootSectionEnum;
import priv.sen.root.exception.OperationFailedException;
import priv.sen.root.exception.OperationRepeaException;
import priv.sen.root.exception.OperationSystemException;
import priv.sen.root.service.RootSectionService;

@Service
public class RootSectionServiceImpl implements RootSectionService{

	@Autowired
	private RootSectionDao rootSectionDao;
	/**
	 * 查询所有板块
	 */
	@Override
	public List<RootSection> findAll() {
		return rootSectionDao.selectAll();
	}

	/**
	 * 根据是否显示状态查询板块
	 */
	@Override
	public List<RootSection> findByShowStatus(Boolean showStatus) {
		return rootSectionDao.selectByShowStatus(showStatus);
	}

	/**
	 * 根据ID查询板块
	 */
	@Override
	public RootSection findBySectionId(Integer sectionId) {
		return rootSectionDao.selectBySectionId(sectionId);
	}

	/**
	 * 根据名称查询板块
	 */
	@Override
	public RootSection findBySectionName(String sectionName) {
		return rootSectionDao.selectBySectionName(sectionName);
	}

	/**
	 * 根据标签查询板块
	 */
	@Override
	public RootSection findBySectionTab(String sectionTab) {
		return rootSectionDao.selectByShowTab(sectionTab);
	}

	/**
	 * 添加板块
	 */
	@Transactional
	@Override
	public RootSectionExecution save(RootSection rootSection) {
		try {
			RootSection section = rootSectionDao.selectBySectionName(rootSection.getSectionName());
			if(section != null) {
				 throw new OperationRepeaException("板块已存在"); 
			}else {
				int insert = rootSectionDao.insert(rootSection);
				if(insert <= 0) {
					throw new OperationFailedException("添加失败！");
				}else {
					RootSection selectBySectionName = rootSectionDao.selectBySectionName(rootSection.getSectionName());
					return new RootSectionExecution(selectBySectionName.getSectionName(),InsertRootSectionEnum.SUCCESS,selectBySectionName); 
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
	public RootSectionExecution update(RootSection rootSection) {
		try {
			RootSection section = rootSectionDao.selectBySectionId(rootSection.getSectionId());
			if(section == null) {
				throw new OperationFailedException("板块不存在");
			}else {
				int update = rootSectionDao.updateBySectionId(rootSection);
				if(update <= 0) {
					throw new OperationFailedException("更新失败！");
				}else {
					RootSection selectBySectionId = rootSectionDao.selectBySectionId(rootSection.getSectionId());
					return new RootSectionExecution(selectBySectionId.getSectionName(), UpdateRootSectionEnum.SUCCESS,selectBySectionId);
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
