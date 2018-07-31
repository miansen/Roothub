package cn.roothub.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.RootTopicExecution;
import cn.roothub.entity.RootTopic;
import cn.roothub.entity.RootUser;
import cn.roothub.entity.Tag;

public interface RootTopicService {

	/**
	 * 根据tab分页查询话题列表
	 * @param pageNumber
	 * @param pageSize
	 * @param tab
	 * @param ptab
	 * @return
	 */
	PageDataBody<RootTopic> page(Integer pageNumber, Integer pageSize, String tab,String ptab);
	
	/**
	 * 模糊查询话题列表
	 * @param pageNumber
	 * @param pageSize
	 * @param tab
	 * @return
	 */
	PageDataBody<RootTopic> pageLike(Integer pageNumber, Integer pageSize, String like);
	
	/**
	 * 分页查询所有话题
	 * @param pageNumber
	 * @param pageSize
	 * @param tab
	 * @return
	 */
	PageDataBody<RootTopic> pageAll(Integer pageNumber, Integer pageSize,String ptab);
	
	/**
	 * 根据板块和昵称分页查询
	 * @param pageNumber
	 * @param pageSize
	 * @param ptab:板块
	 * @param author:作者昵称
	 * @return
	 */
	PageDataBody<RootTopic> pageAllByPtabAndAuthor(Integer pageNumber, Integer pageSize,String ptab,String author);
	
	/**
	 * 分页查询最新话题
	 * @param pageNumber
	 * @param pageSize
	 * @param tab
	 * @return
	 */
	PageDataBody<RootTopic> pageAllNewest(Integer pageNumber, Integer pageSize,String ptab);
	
	/**
	 * 分页查询精华话题
	 */
	PageDataBody<RootTopic> pageGood(Integer pageNumber, Integer pageSize,String ptab);
	
	/**
	 * 分页查询无人回复的话题
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	PageDataBody<RootTopic> pageNoReply(Integer pageNumber, Integer pageSize,String ptab);

	/**
	 * 根据ID查询话题
	 * @param id
	 * @return
	 */
	RootTopic findByTopicId(Integer topicId);
	
	/**
	 * 查询当前作者的其他话题
	 * @param currentTopicId
	 * @param author
	 * @param limit
	 * @return
	 */
	List<RootTopic> findOtherTopicByAuthor(Integer currentTopicId, String author, Integer limit);
	
	/**
	 * 查询热门话题
	 * @param currentTopicId
	 * @param author
	 * @param limit
	 * @return
	 */
	List<RootTopic> findHot(Integer start,Integer limit);
	
	/**
	 * 根据作者昵称分页查询所有话题
	 * @param pageNumber
	 * @param pageSize
	 * @param author
	 * @return
	 */
	PageDataBody<RootTopic> pageByAuthor(Integer pageNumber, Integer pageSize, String author);
	
	/**
	 * 查询所有话题
	 * @return
	 */
	List<RootTopic> findAll();

	/**
	 * 根据ID删除话题
	 * @param id
	 */
	void deleteByTopicId(Integer topicId);
	
	/**
	 * 根据ID置顶话题
	 * @param id
	 */
	void topByTopicId(Integer topicId);
	
	/**
	 * 根据ID加精话题
	 * @param id
	 */
	void goodByTopicId(Integer topicId);
	
	/**
	 * 发布话题
	 * @param topic
	 */
	RootTopicExecution saveTopic(RootTopic topic);
	
	/**
	 * 更新话题
	 * @param topic
	 */
	void updateTopic(RootTopic topic);
	
	/**
     * 更新主题的头像
     * @param user
     * @return
     */
	void updateTopicAvatar(RootUser user);
    
	/**
	 * 收藏的话题列表
	 * @param pageNumber
	 * @param pageSize
	 * @param uid
	 * @return
	 */
	PageDataBody<RootTopic> findById(Integer pageNumber, Integer pageSize, Integer uid);
	
	/**
	 * 查询用户发布主题的数量
	 * @param userId
	 * @return
	 */
	int countByUserName(String userName);

	/**
	 * 分组统计所有标签
	 * @return
	 */
	//Integer countTag();
	
	/**
	 * 分页查询所有标签
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	PageDataBody<Tag> findByTag(Integer pageNumber, Integer pageSize);
	
	/**
	 * 根据标签查询话题
	 * @param tag
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	PageDataBody<RootTopic> pageByTag(String tag,Integer pageNumber, Integer pageSize);
	
	/**
     * 统计所有话题
     * @return
     */
    int countAllTopic(String ptab);
    
}
