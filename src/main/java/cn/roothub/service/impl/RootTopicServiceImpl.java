package cn.roothub.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.roothub.dao.RootTopicDao;
import cn.roothub.dao.RootUserDao;
import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.RootTopicExecution;
import cn.roothub.entity.RootTopic;
import cn.roothub.entity.RootUser;
import cn.roothub.entity.Tag;
import cn.roothub.enums.InsertRootTopicEnum;
import cn.roothub.exception.OperationFailedException;
import cn.roothub.exception.OperationSystemException;
import cn.roothub.service.RootTopicService;

@Service
public class RootTopicServiceImpl implements RootTopicService{

	@Autowired
	private RootTopicDao rootTopicDao;
	@Autowired
	private RootUserDao rootUserDao;
	
	/**
	 * 根据tab分页查询话题列表
	 */
	@Override
	public PageDataBody<RootTopic> page(Integer pageNumber, Integer pageSize, String tab,String ptab) {
		if(tab.equals("all")) {
			return pageAll(pageNumber,pageSize,ptab);
		}else if(tab.equals("good")) {
			return pageGood(pageNumber,pageSize,ptab);
		}else if(tab.equals("noReply")) {
			return pageNoReply(pageNumber,pageSize,ptab);
		}else {
			return pageAllNewest(pageNumber,pageSize,ptab);
		}
	}

	/**
	 * 分页查询所有话题
	 */
	@Override
	public PageDataBody<RootTopic> pageAll(Integer pageNumber, Integer pageSize,String ptab) {
		List<RootTopic> list = rootTopicDao.selectAll((pageNumber - 1) * pageSize, pageSize,ptab);
		int total = rootTopicDao.countAllTopic(ptab);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}

	/**
	 * 分页查询精华话题
	 */
	@Override
	public PageDataBody<RootTopic> pageGood(Integer pageNumber, Integer pageSize,String ptab) {
		List<RootTopic> list = rootTopicDao.selectAllGood((pageNumber - 1) * pageSize, pageSize,ptab);
		int total = rootTopicDao.countAllTopicGood(ptab);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}

	/**
	 * 分页查询无人回复的话题
	 */
	@Override
	public PageDataBody<RootTopic> pageNoReply(Integer pageNumber, Integer pageSize,String ptab) {
		List<RootTopic> list = rootTopicDao.selectAllNoReply((pageNumber - 1) * pageSize, pageSize,ptab);
		int total = rootTopicDao.countAllTopicNoReply(ptab);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}

	/**
	 * 根据ID查询话题
	 */
	@Override
	public RootTopic findByTopicId(Integer topicId) {
		return rootTopicDao.selectByTopicId(topicId);
	}

	/**
	 * 查询当前作者的其他话题
	 */
	@Override
	public List<RootTopic> findOtherTopicByAuthor(Integer currentTopicId, String author, Integer limit) {
		//return rootTopicDao.selectByAuthor(currentTopicId, author, 0, limit);
		return null;
	}

	/**
	 * 根据昵称分页查询用户的所有话题
	 */
	@Override
	public PageDataBody<RootTopic> pageByAuthor(Integer pageNumber, Integer pageSize, String author) {
		int totalRow = rootTopicDao.countAllByName(author);
		List<RootTopic> list = rootTopicDao.selectByAuthor(author, (pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 查询所有话题
	 */
	@Override
	public List<RootTopic> findAll() {
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
		RootTopic topic = rootTopicDao.selectByTopicId(topicId);
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
		RootTopic topic = rootTopicDao.selectByTopicId(topicId);
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
	public RootTopicExecution saveTopic(RootTopic topic) {
		try {
			int insert = rootTopicDao.insert(topic);
			/**
			 * 根据话题名称、话题作者、话题标签、话题内容查询话题
			 * 如果上面四个参数已存在于数据库中，则此处会报错
			 * 2018.06.03 16：35
			 */
			RootTopic rootTopic = rootTopicDao.selectByNameAndAuthorAndTagAndContent(topic.getTitle(), topic.getAuthor(),  topic.getTag(),topic.getContent());
			if(insert <= 0) {
				throw new OperationFailedException("发布话题失败！");
			}else {
				rootUserDao.updateScoreByName(10, topic.getAuthor());//发贴积10分
				return new RootTopicExecution(rootTopic.getTitle(), InsertRootTopicEnum.SUCCESS, rootTopic);
			}
		}catch (OperationFailedException e1) {
			throw e1;
		}catch (Exception e) {
			throw new OperationSystemException("insert into RootTopic error"+e.getMessage());
		}
	}

	/**
	 * 更新话题
	 */
	@Override
	public void updateTopic(RootTopic topic) {
		rootTopicDao.updateByTopicId(topic);
	}

	/**
	 * 收藏话题列表
	 */
	@Override
	public PageDataBody<RootTopic> findById(Integer pageNumber, Integer pageSize, Integer uid) {
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
	 * 分页查询最新话题
	 */
	@Override
	public PageDataBody<RootTopic> pageAllNewest(Integer pageNumber, Integer pageSize,String ptab) {
		List<RootTopic> list = rootTopicDao.selectAllNewest((pageNumber - 1) * pageSize, pageSize,ptab);
		int total = rootTopicDao.countAllTopic(ptab);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}

	/**
	 * 热门话题
	 */
	@Override
	public List<RootTopic> findHot(Integer start, Integer limit) {
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
	public PageDataBody<RootTopic> pageByTag(String tag, Integer pageNumber, Integer pageSize) {
		int totalRow = rootTopicDao.countByTag(tag);
		List<RootTopic> list = rootTopicDao.selectByTag(tag, (pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 更新主题作者的头像
	 */
	@Override
	public void updateTopicAvatar(RootUser user) {
		rootTopicDao.updateTopicAvatar(user);
	}

	/**
	 * 统计所有话题
	 */
	@Override
	public int countAllTopic(String ptab) {
		return rootTopicDao.countAllTopic(ptab);
	}

	/**
	 * 分页模糊查询
	 */
	@Override
	public PageDataBody<RootTopic> pageLike(Integer pageNumber, Integer pageSize, String like) {
		List<RootTopic> list = rootTopicDao.selectByLike(like, (pageNumber - 1) * pageSize, pageSize);
		int totalRow = rootTopicDao.countLike(like);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 根据板块和昵称分页查询话题
	 */
	@Override
	public PageDataBody<RootTopic> pageAllByPtabAndAuthor(Integer pageNumber, Integer pageSize, String ptab, String author) {
		int totalRow = rootTopicDao.countAllByNameAndPtab(author, ptab);
		List<RootTopic> list = rootTopicDao.selectAllByPtabAndAuthor((pageNumber - 1) * pageSize, pageSize, ptab, author);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 首页-最热话题
	 */
	@Override
	public PageDataBody<RootTopic> findIndexHot(Integer pageNumber, Integer pageSize, String tab) {
		int totalRow = rootTopicDao.countIndexHot(tab);
		List<RootTopic> list = rootTopicDao.selectIndexHot((pageNumber - 1) * pageSize, pageSize, tab);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 侧边栏-今日等待回复的话题
	 */
	@Override
	public List<RootTopic> findTodayNoReply(Integer start, Integer limit) {
		return rootTopicDao.selectTodayNoReply(start, limit);
	}

}
