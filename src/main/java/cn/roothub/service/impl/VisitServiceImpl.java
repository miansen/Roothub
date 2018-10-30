package cn.roothub.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.roothub.dao.VisitDao;
import cn.roothub.dto.DMLExecution;
import cn.roothub.dto.PageDataBody;
import cn.roothub.entity.User;
import cn.roothub.entity.Visit;
import cn.roothub.enums.InsertEnum;
import cn.roothub.enums.UpdateEnum;
import cn.roothub.exception.OperationFailedException;
import cn.roothub.exception.OperationRepeaException;
import cn.roothub.exception.OperationSystemException;
import cn.roothub.service.VisitService;

/**
 * 
 * @author sen 2018年8月4日 下午3:34:37 TODO
 */
@Service
public class VisitServiceImpl implements VisitService {

	@Autowired
	private VisitDao visitDao;

	/**
	 * 分页查询访问记录
	 */
	@Override
	public PageDataBody<User> page(Integer vid, Integer pageNumber, Integer pageSize) {
		int totalRow = visitDao.count(vid);
		List<User> list = visitDao.select(vid, (pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 添加访问记录
	 */
	@Override
	@Transactional
	public DMLExecution save(Visit visit) {
		try {
			if (visit.getUid() == visit.getVid()) {
				throw new OperationRepeaException("访问者与被访问者为同一用户！");
			}
			int isVisit = visitDao.isVisit(visit.getUid(), visit.getVid());
			if (isVisit == 0) {
				int insert = visitDao.insert(visit);
				if (insert <= 0) {
					throw new OperationFailedException("添加失败!");
				} else {
					return new DMLExecution("添加访问记录", InsertEnum.SUCCESS, visit);
				}
			} else {
				int update = visitDao.update(visit);
				if (update <= 0) {
					throw new OperationFailedException("更新失败!");
				} else {
					return new DMLExecution("更新访问记录", UpdateEnum.SUCCESS, visit);
				}
			}
		} catch (OperationRepeaException e2) {
			throw e2;
		} catch (OperationFailedException e1) {
			throw e1;
		} catch (Exception e) {
			throw new OperationSystemException("insert into or update visit error " + e.getMessage());
		}
	}

	@Override
	public int count(Integer vid) {
		return visitDao.count(vid);
	}

}
