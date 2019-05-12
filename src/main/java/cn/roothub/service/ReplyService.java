package cn.roothub.service;

import java.util.List;
import java.util.Map;
import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.ReplyExecution;
import cn.roothub.entity.ReplyAndTopicByName;
import cn.roothub.entity.Reply;

/**
 * 评论接口
 * @author sen
 * 2018年5月25日
 * 下午8:25:39
 * TODO
 */
public interface ReplyService {

	/**
	 * 根据ID查询评论
	 * @param id
	 * @return
	 */
	Reply findById(Integer id);
	
	/**
	 * 查询全部评论
	 * @return
	 */
	List<Reply> findAll();
	
	/**
	 * 分页查询全部评论
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	PageDataBody<Reply> findAll(Integer pageNumber, Integer pageSize);
	
	/**
	 * 根据评论人昵称分页查询全部评论
	 * 关联话题表
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	PageDataBody<ReplyAndTopicByName> findAllByNameAndTopic(String replyAuthorName,Integer pageNumber, Integer pageSize);
	
	/**
	 * 根据话题ID查询评论列表
	 * @param tid
	 * @return
	 */
	List<Reply> findByTopicId(Integer topicId);
	
	/**
	 * 分页查询话题的评论列表
	 * @param pageNumber
	 * @param pageSize
	 * @param topicId
	 * @return
	 */
	PageDataBody<Reply> page(Integer pageNumber, Integer pageSize, Integer topicId);
	
	/**
	 * 添加评论
	 * @param reply
	 * @return
	 */
	ReplyExecution save(Reply reply);
	
	/**
	 * 根据ID删除评论
	 * @param replyId
	 */
	void deleteByReplyId(Integer replyId);
	
	/**
	 * 根据话题ID删除评论
	 * @param topicId
	 */
	void deleteByTopicId(Integer topicId);
	
	/**
	 * 根据用户名称删除评论
	 * @param replyAuthorName
	 */
	void deleteByReplyAuthorName(String replyAuthorName);
	
	/**
	 * 更新评论
	 * @param reply
	 */
	void update(Reply reply);
	
	/**
	 * 统计所有评论
	 * @return
	 */
	int countAll();
	
	/**
	 * 根据用户昵称统计评论数
	 * @return
	 */
	int countByName(String name);
	
	/**
	 * 统计当天评论数
	 * @return
	 */
	int countToday();
	
	/**
	 * 后台评论分页列表
	 * @param author: 评论作者
	 * @param topic: 话题
	 * @param startDate: 开始时间
	 * @param endDate: 结束时间
	 * @param pageNumber: 页数
	 * @param pageSize: 返回数据量
	 * @return
	 */
	PageDataBody<Map<String,Object>> pageForAdmin(String author,String topic,String startDate,String endDate,Integer pageNumber, Integer pageSize);
    
	/**
	 * 统计后台评论
	 * @param author: 评论作者
	 * @param topic: 话题
	 * @param startDate: 开始时间
	 * @param endDate: 结束时间
	 * @return
	 */
    int countAllForAdmin(String author,String topic,String startDate,String endDate);
}
