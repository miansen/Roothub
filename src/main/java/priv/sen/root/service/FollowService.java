package priv.sen.root.service;

import priv.sen.root.dto.PageDataBody;
import priv.sen.root.entity.Follow;
import priv.sen.root.entity.RootUser;

public interface FollowService {

	/**
	 * 我关注的人
	 * @param pageNumber
	 * @param pageSize
	 * @param tab
	 * @return
	 */
	PageDataBody<RootUser> page(Integer pageNumber, Integer pageSize,Integer uid);
	
	/**
	 * 关注我的人
	 * @param pageNumber
	 * @param pageSize
	 * @param tab
	 * @return
	 */
	PageDataBody<RootUser> followMe(Integer pageNumber, Integer pageSize,Integer fid);
	
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
