package priv.sen.root.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.sen.root.dao.RootReplyDao;
import priv.sen.root.dto.PageDataBody;
import priv.sen.root.dto.RootReplyExecution;
import priv.sen.root.entity.ReplyAndTopicByName;
import priv.sen.root.entity.RootReply;
import priv.sen.root.enums.InsertRootReplyEnum;
import priv.sen.root.exception.OperationFailedException;
import priv.sen.root.exception.OperationRepeaException;
import priv.sen.root.exception.OperationSystemException;
import priv.sen.root.service.RootReplyService;

/**
 * 评论业务逻辑层
 * @author sen
 * 2018年5月25日
 * 下午8:54:27
 * TODO
 */
@Service
public class RootReplyServiceImpl implements RootReplyService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RootReplyDao rootReplyDao;
	
	/**
	 * 查询全部评论
	 */
	@Override
	public List<RootReply> findAll() {
		return rootReplyDao.selectAll();
	}

	/**
	 * 分页查询全部话题
	 */
	@Override
	public PageDataBody<RootReply> findAll(Integer pageNumber, Integer pageSize) {
		int totalRow = rootReplyDao.countAll();
		List<RootReply> list = rootReplyDao.selectAll((pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 根据话题ID查询评论
	 */
	@Override
	public List<RootReply> findByTopicId(Integer topicId) {
		return rootReplyDao.selectByTopicId(topicId);
	}

	/**
	 * 分页查询评论列表
	 */
	@Override
	public PageDataBody<RootReply> page(Integer pageNumber, Integer pageSize, Integer topicId) {
		int totalRow = rootReplyDao.countByTopicId(topicId);
		List<RootReply> list = rootReplyDao.selectByTopicId((pageNumber - 1) * pageSize, pageSize, topicId);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}
	
	@Transactional
	@Override
	public RootReplyExecution save(RootReply reply) {
		try {
			if(reply.getReplyContent() == null) {
				throw new OperationRepeaException("评论内容不能为空");
			}
			int insert = rootReplyDao.insert(reply);
			if(insert <= 0) {
				throw new OperationFailedException("评论话题失败！");
			}else {
				return new RootReplyExecution(reply.getReplyAuthorName(), InsertRootReplyEnum.SUCCESS, reply);
						
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
		rootReplyDao.deleteByReplyId(replyId);
	}

	/**
	 * 根据话题ID删除评论
	 */
	@Override
	public void deleteByTopicId(Integer topicId) {
		rootReplyDao.deleteByTopicId(topicId);
	}

	/**
	 * 更新评论
	 */
	@Override
	public void update(RootReply reply) {
		rootReplyDao.update(reply);
	}

	/**
	 * 根据评论人昵称分页查询全部评论
	 * 关联话题表
	 */
	@Override
	public PageDataBody<ReplyAndTopicByName> findAllByNameAndTopic(String replyAuthorName, Integer pageNumber, Integer pageSize) {
		int totalRow = rootReplyDao.countByName(replyAuthorName);
		List<ReplyAndTopicByName> list = rootReplyDao.selectAllByNameAndTopic(replyAuthorName, (pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 统计所有评论
	 */
	@Override
	public int countAll() {
		return rootReplyDao.countAll();
	}
}
