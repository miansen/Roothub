package wang.miansen.roothub.modules.follow.service.impl;

import java.util.List;
import java.util.function.Function;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.common.service.impl.AbstractBaseServiceImpl;
import wang.miansen.roothub.modules.collect.model.Collect;
import wang.miansen.roothub.modules.follow.dao.FollowDao;
import wang.miansen.roothub.modules.follow.dto.FollowDTO;
import wang.miansen.roothub.modules.follow.model.Follow;
import wang.miansen.roothub.modules.follow.service.FollowService;
import wang.miansen.roothub.modules.post.model.Post;
import wang.miansen.roothub.modules.user.model.User;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl extends AbstractBaseServiceImpl<Follow, FollowDTO> implements FollowService {

	@Autowired
	private FollowDao followDao;
	
	/**
	 * 我关注的人
	 */
	@Override
	public Page<User> page(Integer pageNumber, Integer pageSize, String uid) {
		int total = followDao.countByUid(uid);
		List<User> list = followDao.select((pageNumber - 1) * pageSize, pageSize, uid);
		return new Page<>(list, pageNumber, pageSize, total);
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
	public int delete(String uid, String fid) {
		return followDao.delete(uid, fid);
	}

	/**
	 * 我关注的数量
	 */
	@Override
	public int countByUid(String uid) {
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
	public int isFollow(String uid, String fid) {
		return followDao.isFollow(uid, fid);
	}

	/**
	 * 关注我的人
	 */
	@Override
	public Page<User> followMe(Integer pageNumber, Integer pageSize, Integer fid) {
		int total = followDao.countByFid(fid);
		List<User> list = followDao.selectByFid((pageNumber - 1) * pageSize, pageSize, fid);
		return new Page<>(list, pageNumber, pageSize, total);
	}

	/**
	 * 关注的人的主题
	 */
	@Override
	public Page<Post> pageTopic(Integer pageNumber, Integer pageSize, String uid) {
		int total = followDao.countTopic(uid);
		List<Post> list = followDao.selectTopic((pageNumber - 1) * pageSize, pageSize, uid);
		return new Page<>(list, pageNumber, pageSize, total);
	}

	@Override
	public Function<? super FollowDTO, ? extends Follow> getDTO2DOMapper() {
		return followDTO -> {
			Follow follow = new Follow();
			if (followDTO != null) {
				BeanUtils.copyProperties(followDTO, follow);
			}
			return follow;
		};
	}

	@Override
	public Function<? super Follow, ? extends FollowDTO> getDO2DTOMapper() {
		return follow -> {
			FollowDTO followDTO = new FollowDTO();
			if (follow != null) {
				BeanUtils.copyProperties(follow, followDTO);
			}
			return followDTO;
		};
	}

	@Override
	public BaseDao<Follow> getDao() {
		return followDao;
	}

}
