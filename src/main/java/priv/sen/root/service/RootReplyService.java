package priv.sen.root.service;

import java.util.List;

import priv.sen.root.dto.PageDataBody;
import priv.sen.root.dto.RootReplyExecution;
import priv.sen.root.entity.ReplyAndTopicByName;
import priv.sen.root.entity.RootReply;

/**
 * 评论接口
 * @author sen
 * 2018年5月25日
 * 下午8:25:39
 * TODO
 */
public interface RootReplyService {

	/**
	 * 查询全部评论
	 * @return
	 */
	List<RootReply> findAll();
	
	/**
	 * 分页查询全部评论
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	PageDataBody<RootReply> findAll(Integer pageNumber, Integer pageSize);
	
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
	List<RootReply> findByTopicId(Integer topicId);
	
	/**
	 * 分页查询话题的评论列表
	 * @param pageNumber
	 * @param pageSize
	 * @param topicId
	 * @return
	 */
	PageDataBody<RootReply> page(Integer pageNumber, Integer pageSize, Integer topicId);
	
	/**
	 * 添加评论
	 * @param reply
	 * @return
	 */
	RootReplyExecution save(RootReply reply);
	
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
	 * 更新评论
	 * @param reply
	 */
	void update(RootReply reply);
	
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
}
