package priv.sen.root.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.sen.root.dao.CollectDao;
import priv.sen.root.dto.PageDataBody;
import priv.sen.root.entity.Collect;
import priv.sen.root.entity.RootTopic;
import priv.sen.root.service.CollectService;

@Service
public class CollectDaoServiceImpl implements CollectService{

	@Autowired
	private CollectDao collectDao;
	
	/**
	 * 分页查询收藏的话题
	 */
	@Override
	public PageDataBody<RootTopic> page(Integer pageNumber, Integer pageSize,Integer uid) {
		int total = collectDao.count(uid);
		List<RootTopic> list = collectDao.selectAllByCollect((pageNumber - 1) * pageSize, pageSize, uid);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
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
