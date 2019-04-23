package cn.roothub.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.roothub.dao.TopicDao;
import cn.roothub.dao.UserDao;
import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.TopicExecution;
import cn.roothub.entity.Topic;
import cn.roothub.entity.User;
import cn.roothub.entity.Tag;
import cn.roothub.enums.InsertTopicEnum;
import cn.roothub.exception.OperationFailedException;
import cn.roothub.exception.OperationSystemException;
import cn.roothub.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService{

	@Autowired
	private TopicDao rootTopicDao;
	@Autowired
	private UserDao rootUserDao;
	
	/**
	 * 根据节点和节点板块查询话题
	 */
	@Override
	public PageDataBody<Topic> pageByNodeAndNodeTab(Integer pageNumber, Integer pageSize, String nodeTab,String nodeTitle) {
		if(nodeTab.equals("all")) {
			return pageAllByNode(pageNumber,pageSize,nodeTitle);
		}else if(nodeTab.equals("good")) {
			return pageGood(pageNumber,pageSize,nodeTitle);
		}else if(nodeTab.equals("noReply")) {
			return pageNoReply(pageNumber,pageSize,nodeTitle);
		}else {
			return pageAllNewest(pageNumber,pageSize,nodeTitle);
		}
	}

	/**
	 * 根据板块查询所有话题
	 */
	@Override
	public PageDataBody<Topic> pageAllByTab(Integer pageNumber, Integer pageSize,String tab) {
		List<Topic> list = rootTopicDao.selectAllByTab((pageNumber - 1) * pageSize, pageSize,tab);
		int total = rootTopicDao.countTopicByTab(tab);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}
	
	/**
	 * 根据节点查询所有话题
	 */
	@Override
	public PageDataBody<Topic> pageAllByNode(Integer pageNumber, Integer pageSize, String nodeTitle) {
		List<Topic> list = rootTopicDao.selectAllByNode((pageNumber - 1) * pageSize, pageSize,nodeTitle);
		int total = rootTopicDao.countTopicByNode(nodeTitle);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}

	/**
	 * 根据节点查询精华话题
	 */
	@Override
	public PageDataBody<Topic> pageGood(Integer pageNumber, Integer pageSize,String nodeTitle) {
		List<Topic> list = rootTopicDao.selectAllGood((pageNumber - 1) * pageSize, pageSize,nodeTitle);
		int total = rootTopicDao.countTopicGoodByNode(nodeTitle);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}

	/**
	 * 根据节点查询无人回复的话题
	 */
	@Override
	public PageDataBody<Topic> pageNoReply(Integer pageNumber, Integer pageSize,String nodeTitle) {
		List<Topic> list = rootTopicDao.selectAllNoReply((pageNumber - 1) * pageSize, pageSize,nodeTitle);
		int total = rootTopicDao.countTopicNoReplyByNode(nodeTitle);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}

	/**
	 * 根据ID查询话题
	 */
	@Override
	public Topic findByTopicId(Integer topicId) {
		return rootTopicDao.selectByTopicId(topicId);
	}

	/**
	 * 查询当前作者的其他话题
	 */
	@Override
	public List<Topic> findOtherTopicByAuthor(Integer currentTopicId, String author, Integer limit) {
		//return rootTopicDao.selectByAuthor(currentTopicId, author, 0, limit);
		return null;
	}

	/**
	 * 根据昵称分页查询用户的所有话题
	 */
	@Override
	public PageDataBody<Topic> pageByAuthor(Integer pageNumber, Integer pageSize, String author) {
		int totalRow = rootTopicDao.countAllByName(author);
		List<Topic> list = rootTopicDao.selectByAuthor(author, (pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 查询所有话题
	 */
	@Override
	public List<Topic> findAll() {
		return rootTopicDao.selectAll();
	}

	/**
	 * 根据ID删除话题
	 */
	@Override
	public void deleteByTopicId(Integer topicId) {
		rootTopicDao.deleteById(topicId);
	}

	/**
	 * 置顶话题
	 */
	@Override
	public void topByTopicId(Integer topicId) {
		Topic topic = rootTopicDao.selectByTopicId(topicId);
		if(topic != null) {
			topic.setTop(!topic.getTop());
			rootTopicDao.updateByTopicId(topic);
		}
	}

	/**
	 * 话题加精
	 */
	@Override
	public void goodByTopicId(Integer topicId) {
		Topic topic = rootTopicDao.selectByTopicId(topicId);
		if(topic != null) {
			topic.setGood(!topic.getGood());
			rootTopicDao.updateByTopicId(topic);
		}
	}

	/**
	 * 发布话题
	 */
	@Transactional
	@Override
	public TopicExecution saveTopic(Topic topic) {
		try {
			int insert = rootTopicDao.insert(topic);
			/**
			 * 根据话题名称、话题作者、话题标签、话题内容查询话题
			 * 如果上面四个参数已存在于数据库中，则此处会报错
			 * 2018.06.03 16：35
			 */
			// Topic rootTopic = rootTopicDao.selectByNameAndAuthorAndTagAndContent(topic.getTitle(), topic.getAuthor(),  topic.getTag(),topic.getContent());
			if(insert <= 0) {
				throw new OperationFailedException("发布话题失败！");
			}else {
				rootUserDao.updateScoreByName(10, topic.getAuthor());//发贴积10分
				Topic rootTopic = rootTopicDao.selectByTitleAndDate(topic.getTitle(), topic.getCreateDate());
				return new TopicExecution(rootTopic.getTitle(), InsertTopicEnum.SUCCESS, rootTopic);
			}
		}catch (OperationFailedException e1) {
			throw e1;
		}catch (Exception e) {
			throw new OperationSystemException("insert into RootTopic error "+e.getMessage());
		}
	}
	
	@Override
	public TopicExecution createTopic(String title, String content, String tab, String nodeCode,String nodeTitle, String tag,User user) {
		Topic topic = new Topic();
		topic.setPtab(null);
		topic.setTab(tab);
		topic.setTitle(title);
		topic.setTag(tag);
		topic.setContent(content);
		topic.setCreateDate(new Date());
		topic.setUpdateDate(new Date());
		topic.setLastReplyTime(null);
		topic.setLastReplyAuthor(null);
		topic.setViewCount(0);
		topic.setAuthor(user.getUserName());
		topic.setTop(false);
		topic.setGood(false);
		topic.setShowStatus(true);
		topic.setReplyCount(0);
		topic.setIsDelete(false);
		topic.setTagIsCount(true);
		topic.setPostGoodCount(null);
		topic.setPostBadCount(null);
		topic.setStatusCd("1000");
		topic.setNodeSlug(nodeCode);
		topic.setNodeTitle(nodeTitle);
		topic.setRemark(null);
		topic.setAvatar(user.getAvatar());//话题作者的头像
		topic.setUrl(null);
		TopicExecution saveTopic = saveTopic(topic);
		return saveTopic;
	}

	/**
	 * 更新话题
	 */
	@Override
	public void updateTopic(Topic topic) {
		rootTopicDao.updateByTopicId(topic);
	}

	/**
	 * 收藏话题列表
	 */
	@Override
	public PageDataBody<Topic> findCollectsById(Integer pageNumber, Integer pageSize, Integer uid) {
		return null;
	}

	/**
	 * 查询用户发布主题的数量
	 */
	@Override
	public int countByUserName(String userName) {
		return rootTopicDao.countAllByName(userName);
	}

	/**
	 * 根据节点查询最新话题
	 */
	@Override
	public PageDataBody<Topic> pageAllNewest(Integer pageNumber, Integer pageSize,String nodeTitle) {
		List<Topic> list = rootTopicDao.selectAllNewest((pageNumber - 1) * pageSize, pageSize,nodeTitle);
		int total = rootTopicDao.countTopicByNode(nodeTitle);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}

	/**
	 * 热门话题
	 */
	@Override
	public List<Topic> findHot(Integer start, Integer limit) {
		return rootTopicDao.selectHot(start, limit);
	}

	/**
	 * 分页查询所有标签
	 */
	@Override
	public PageDataBody<Tag> findByTag(Integer pageNumber, Integer pageSize) {
		int totalRow = rootTopicDao.countTag();
		List<Tag> list = rootTopicDao.selectAllTag((pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 根据标签查询话题
	 */
	@Override
	public PageDataBody<Topic> pageByTag(String tag, Integer pageNumber, Integer pageSize) {
		int totalRow = rootTopicDao.countByTag(tag);
		List<Topic> list = rootTopicDao.selectByTag(tag, (pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 更新主题作者的头像
	 */
	@Override
	public void updateTopicAvatar(User user) {
		rootTopicDao.updateTopicAvatar(user);
	}

	/**
	 * 更新节点名称
	 */
	@Override
	public void updateNodeTitile(String oldNodeTitle, String newNodeTitle) {
		rootTopicDao.updateNodeTitile(oldNodeTitle, newNodeTitle);
	}

	/**
	 * 统计所有话题
	 */
	@Override
	public int countAllTopic(String tab) {
		return rootTopicDao.countTopicByTab(tab);
	}

	/**
	 * 分页模糊查询
	 */
	@Override
	public PageDataBody<Topic> pageLike(Integer pageNumber, Integer pageSize, String like) {
		List<Topic> list = rootTopicDao.selectByLike(like, (pageNumber - 1) * pageSize, pageSize);
		int totalRow = rootTopicDao.countLike(like);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 根据板块和昵称分页查询话题
	 */
	@Override
	public PageDataBody<Topic> pageAllByPtabAndAuthor(Integer pageNumber, Integer pageSize, String ptab, String author) {
		int totalRow = rootTopicDao.countAllByNameAndPtab(author, ptab);
		List<Topic> list = rootTopicDao.selectAllByPtabAndAuthor((pageNumber - 1) * pageSize, pageSize, ptab, author);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 首页-最热话题
	 */
	@Override
	public PageDataBody<Topic> findIndexHot(Integer pageNumber, Integer pageSize, String tab) {
		int totalRow = rootTopicDao.countIndexHot(tab);
		List<Topic> list = rootTopicDao.selectIndexHot((pageNumber - 1) * pageSize, pageSize, tab);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 侧边栏-今日等待回复的话题
	 */
	@Override
	public List<Topic> findTodayNoReply(Integer start, Integer limit) {
		return rootTopicDao.selectTodayNoReply(start, limit);
	}

	/**
	 * 作者的其他话题
	 */
	@Override
	public List<Topic> findOther(String userName, Integer topicId) {
		return rootTopicDao.selectOther(userName, topicId);
	}

	/**
	 * 根据节点统计所有话题
	 */
	@Override
	public int countTopicByNode(String nodeTitle) {
		return rootTopicDao.countTopicByNode(nodeTitle);
	}

	@Override
	public int countToday() {
		return rootTopicDao.countToday();
	}

	@Override
	public PageDataBody<Topic> pageForAdmin(String author, String startDate, String endDate, Integer pageNumber,
			Integer pageSize) {
		List<Topic> list = rootTopicDao.selectAllForAdmin(author, startDate, endDate, (pageNumber - 1) * pageSize, pageSize);
		int totalRow = countAllForAdmin(author, startDate, endDate);
		return new PageDataBody<Topic>(list, pageNumber, pageSize, totalRow);
	}

	@Override
	public int countAllForAdmin(String author,String startDate,String endDate) {
		return rootTopicDao.countAllForAdmin(author, startDate, endDate);
	}

	
	@Override
	public Topic findById(Integer id) {
		return rootTopicDao.selectByTopicId(id);
	}

}
