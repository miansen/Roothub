package cn.roothub.service.impl;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.roothub.dao.ReplyDao;
import cn.roothub.dao.UserDao;
import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.ReplyExecution;
import cn.roothub.entity.ReplyAndTopicByName;
import cn.roothub.entity.Topic;
import cn.roothub.entity.Reply;
import cn.roothub.enums.InsertReplyEnum;
import cn.roothub.exception.OperationFailedException;
import cn.roothub.exception.OperationRepeaException;
import cn.roothub.exception.OperationSystemException;
import cn.roothub.service.ReplyService;
import cn.roothub.service.TopicService;

/**
 * 评论业务逻辑层
 * @author sen
 * 2018年5月25日
 * 下午8:54:27
 * TODO
 */
@Service
public class ReplyServiceImpl implements ReplyService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ReplyDao replyDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TopicService topicService;
	
	/**
	 * 根据ID查询评论
	 */
	@Override
	public Reply findById(Integer id) {
		return replyDao.selectByReplyId(id);
	}

	/**
	 * 查询全部评论
	 */
	@Override
	public List<Reply> findAll() {
		return replyDao.selectAll();
	}

	/**
	 * 分页查询全部话题
	 */
	@Override
	public PageDataBody<Reply> findAll(Integer pageNumber, Integer pageSize) {
		int totalRow = replyDao.countAll();
		List<Reply> list = replyDao.selectAll((pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 根据话题ID查询评论
	 */
	@Override
	public List<Reply> findByTopicId(Integer topicId) {
		return replyDao.selectByTopicId(topicId);
	}

	/**
	 * 分页查询评论列表
	 */
	@Override
	public PageDataBody<Reply> page(Integer pageNumber, Integer pageSize, Integer topicId) {
		int totalRow = replyDao.countByTopicId(topicId);
		List<Reply> list = replyDao.selectByTopicId((pageNumber - 1) * pageSize, pageSize, topicId);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}
	
	@Transactional
	@Override
	public ReplyExecution save(Reply reply) {
		try {
			if(reply.getReplyContent() == null) {
				throw new OperationRepeaException("评论内容不能为空");
			}
			int insert = replyDao.insert(reply);
			if(insert <= 0) {
				throw new OperationFailedException("评论话题失败！");
			}else {
				userDao.updateScore(10, reply.getReplyAuthorId());//回复积10分
				return new ReplyExecution(reply.getReplyAuthorName(), InsertReplyEnum.SUCCESS, reply);
						
			}
		} catch (OperationRepeaException e1) {
			throw e1;
		} catch (OperationFailedException e2) {
			throw e2;
		} catch (Exception e) {
			throw new OperationSystemException("insert into RootReply error "+e.getMessage());
		}
	}

	/**
	 * 根据评论ID删除评论
	 */
	@Override
	public void deleteByReplyId(Integer replyId) {
		Reply reply = findById(replyId);
		Topic topic = topicService.findById(reply.getTopicId());
		// 话题评论数 - 1
		topic.setReplyCount(topic.getReplyCount() - 1);
		// 更新话题
		topicService.updateTopic(topic);
		// 删除评论
		replyDao.deleteByReplyId(replyId);
	}

	/**
	 * 根据话题ID删除评论
	 */
	@Override
	public void deleteByTopicId(Integer topicId) {
		replyDao.deleteByTopicId(topicId);
	}

	/**
	 * 根据用户名称删除评论
	 */
	@Override
	public void deleteByReplyAuthorName(String replyAuthorName) {
		replyDao.deleteByReplyAuthorName(replyAuthorName);
	}

	/**
	 * 更新评论
	 */
	@Override
	public void update(Reply reply) {
		replyDao.update(reply);
	}

	/**
	 * 根据评论人昵称分页查询全部评论
	 * 关联话题表
	 */
	@Override
	public PageDataBody<ReplyAndTopicByName> findAllByNameAndTopic(String replyAuthorName, Integer pageNumber, Integer pageSize) {
		int totalRow = replyDao.countByName(replyAuthorName);
		List<ReplyAndTopicByName> list = replyDao.selectAllByNameAndTopic(replyAuthorName, (pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 统计所有评论
	 */
	@Override
	public int countAll() {
		return replyDao.countAll();
	}

	/**
	 * 根据用户昵称统计评论数
	 */
	@Override
	public int countByName(String name) {
		return replyDao.countByName(name);
	}

	@Override
	public int countToday() {
		return replyDao.countToday();
	}

	/**
	 * 后台评论分页列表
	 */
	@Override
	public PageDataBody<Map<String, Object>> pageForAdmin(String author, String topic, String startDate, String endDate,
			Integer pageNumber, Integer pageSize) {
		List<Map<String,Object>> list = replyDao.selectAllForAdmin(author, topic, startDate, endDate, (pageNumber - 1) * pageSize, pageSize);
		int totalRow = countAllForAdmin(author, topic, startDate, endDate);
		return new PageDataBody<Map<String, Object>>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 统计后台评论
	 */
	@Override
	public int countAllForAdmin(String author, String topic, String startDate, String endDate) {
		return replyDao.countAllForAdmin(author, topic, startDate, endDate);
	}
}
