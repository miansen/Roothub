package wang.miansen.roothub.modules.follow.service;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.modules.follow.dto.FollowDTO;
import wang.miansen.roothub.modules.follow.model.Follow;
import wang.miansen.roothub.modules.post.model.Post;
import wang.miansen.roothub.modules.user.model.User;

public interface FollowService extends BaseService<Follow, FollowDTO> {

	/**
	 * 我关注的人
	 * @param pageNumber
	 * @param pageSize
	 * @param tab
	 * @return
	 */
	Page<User> page(Integer pageNumber, Integer pageSize, String uid);

	/**
	 * 关注人的主题
	 * @param pageNumber
	 * @param pageSize
	 * @param uid
	 * @return
	 */
	Page<Post> pageTopic(Integer pageNumber, Integer pageSize, String uid);

	/**
	 * 关注我的人
	 * @param pageNumber
	 * @param pageSize
	 * @param tab
	 * @return
	 */
	Page<User> followMe(Integer pageNumber, Integer pageSize, Integer fid);

	/**
	 * 添加关注
	 * @param follow
	 * @return
	 */
	int insert(Follow follow);

	/**
	 * 取消关注
	 * @param uid
	 * @param fid
	 * @return
	 */
	int delete(String uid, String fid);

	/**
	 * 统计用户关注的数量
	 * @param uid
	 * @return
	 */
	int countByUid(String uid);

	/**
	 * 统计用户被关注的数量
	 * @param fid
	 * @return
	 */
	int countByFid(Integer fid);

	/**
	 * 判断是否已关注 0：否 1：是
	 * @param uid
	 * @param fid
	 * @return
	 */
	int isFollow(String uid, String fid);
}
