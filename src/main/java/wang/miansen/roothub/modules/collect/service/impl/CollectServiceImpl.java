package wang.miansen.roothub.modules.collect.service.impl;

import java.util.List;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.modules.collect.dao.CollectDao;
import wang.miansen.roothub.modules.collect.model.Collect;
import wang.miansen.roothub.modules.collect.service.CollectService;
import wang.miansen.roothub.modules.topic.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectServiceImpl implements CollectService {

	@Autowired
	private CollectDao collectDao;
	
	/**
	 * 分页查询收藏的话题
	 */
	@Override
	public Page<Topic> page(Integer pageNumber, Integer pageSize, Integer uid) {
		int total = collectDao.count(uid);
		List<Topic> list = collectDao.selectAllByCollect((pageNumber - 1) * pageSize, pageSize, uid);
		return new Page<>(list, pageNumber, pageSize, total);
	}

	/**
	 * 收藏话题
	 */
	@Override
	public int insert(Collect collect) {
		return collectDao.insert(collect);
	}

	/**
	 * 取消收藏
	 */
	@Override
	public int delete(Integer uid, Integer tid) {
		return collectDao.delete(uid, tid);
	}

	/**
	 * 根据用户 ID 删除收藏
	 */
	@Override
	public void deleteByUid(Integer uid) {
		collectDao.deleteByUid(uid);
	}

	/**
	 * 统计收藏话题的数量
	 */
	@Override
	public int count(Integer uid) {
		return collectDao.count(uid);
	}

	 /**
     * 判断用户是否已收藏此话题 0:否 1:是
     */
	@Override
	public int isCollect(Integer uid, Integer tid) {
		return collectDao.isCollect(uid, tid);
	}

	/**
     * 统计话题被收藏的数量
     * @param uid
     * @return
     */
	@Override
	public int countByTid(Integer tid) {
		return collectDao.countByTid(tid);
	}

}
