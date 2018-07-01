package priv.sen.root.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priv.sen.root.dao.FollowDao;
import priv.sen.root.dto.PageDataBody;
import priv.sen.root.entity.Follow;
import priv.sen.root.entity.RootUser;
import priv.sen.root.service.FollowService;

@Service
public class FollowServiceImpl implements FollowService{

	@Autowired
	private FollowDao followDao;
	
	/**
	 * 我关注的人
	 */
	@Override
	public PageDataBody<RootUser> page(Integer pageNumber, Integer pageSize, Integer uid) {
		int total = followDao.countByUid(uid);
		List<RootUser> list = followDao.select((pageNumber - 1) * pageSize, pageSize, uid);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}

	/**
	 * 关注
	 */
	@Override
	public int insert(Follow follow) {
		return followDao.insert(follow);
	}

	/**
	 * 取消关注
	 */
	@Override
	public int delete(Integer uid, Integer fid) {
		return followDao.delete(uid, fid);
	}

	/**
	 * 我关注的数量
	 */
	@Override
	public int countByUid(Integer uid) {
		return followDao.countByUid(uid);
	}

	/**
	 * 我被关注的数量
	 */
	@Override
	public int countByFid(Integer fid) {
		return followDao.countByFid(fid);
	}

	/**
	 * 我是否已关注了
	 */
	@Override
	public int isFollow(Integer uid, Integer fid) {
		return followDao.isFollow(uid, fid);
	}

	/**
	 * 关注我的人
	 */
	@Override
	public PageDataBody<RootUser> followMe(Integer pageNumber, Integer pageSize, Integer fid) {
		int total = followDao.countByFid(fid);
		List<RootUser> list = followDao.selectByFid((pageNumber - 1) * pageSize, pageSize, fid);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}

}
