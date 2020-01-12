package wang.miansen.roothub.modules.follow.service;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.modules.follow.model.Follow;
import wang.miansen.roothub.modules.topic.model.Topic;
import wang.miansen.roothub.modules.user.model.User;

public interface FollowService {

	/**
	 * 我关注的人
	 * @param pageNumber
	 * @param pageSize
	 * @param tab
	 * @return
	 */
	Page<User> page(Integer pageNumber, Integer pageSize, Integer uid);
	
	/**
	 * 关注人的主题
	 * @param pageNumber
	 * @param pageSize
	 * @param uid
	 * @return
	 */
	Page<Topic> pageTopic(Integer pageNumber,Integer pageSize,Integer uid);
	
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
	int delete(Integer uid,Integer fid);
	
	/**
	 * 统计用户关注的数量
	 * @param uid
	 * @return
	 */
	int countByUid(Integer uid);
	
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
	int isFollow(Integer uid,Integer fid);
}
