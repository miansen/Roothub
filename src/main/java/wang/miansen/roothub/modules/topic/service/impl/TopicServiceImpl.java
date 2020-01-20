package wang.miansen.roothub.modules.topic.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.dao.mapper.wrapper.update.UpdateWrapper;
import wang.miansen.roothub.common.dto.TopicExecution;
import wang.miansen.roothub.common.enums.InsertTopicEnum;
import wang.miansen.roothub.common.exception.OperationFailedException;
import wang.miansen.roothub.common.service.impl.AbstractBaseServiceImpl;
import wang.miansen.roothub.common.util.StringUtils;
import wang.miansen.roothub.modules.node.dto.NodeDTO;
import wang.miansen.roothub.modules.node.service.NodeService;
import wang.miansen.roothub.modules.sys.service.SystemConfigService;
import wang.miansen.roothub.modules.tab.dto.TabDTO;
import wang.miansen.roothub.modules.tag.model.Tag;
import wang.miansen.roothub.modules.topic.dao.TopicDao;
import wang.miansen.roothub.modules.topic.dto.TopicDTO;
import wang.miansen.roothub.modules.topic.enums.TopicErrorCodeEnum;
import wang.miansen.roothub.modules.topic.exception.TopicException;
import wang.miansen.roothub.modules.topic.model.Topic;
import wang.miansen.roothub.modules.topic.service.TabService;
import wang.miansen.roothub.modules.topic.service.TopicService;
import wang.miansen.roothub.modules.user.dao.UserDao;
import wang.miansen.roothub.modules.user.dto.UserDTO;
import wang.miansen.roothub.modules.user.model.User;
import wang.miansen.roothub.modules.user.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicServiceImpl extends AbstractBaseServiceImpl<Topic, TopicDTO> implements TopicService {

	private Logger log = LoggerFactory.getLogger(TopicServiceImpl.class);
	
	@Autowired
	private TopicDao rootTopicDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NodeService nodeService;
	
	@Autowired
	private TabService tabService;
	
	@Autowired
	private UserDao rootUserDao;
	
	@Autowired
	@Qualifier("systemConfigServiceImpl")
	private SystemConfigService systemConfigService;
	
	/**
	 * 根据节点和节点板块查询话题
	 */
	@Override
	public Page<Topic> pageByNodeAndNodeTab(Integer pageNumber, Integer pageSize, String nodeTab, String nodeTitle) {
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
	public Page<Topic> pageAllByTab(Integer pageNumber, Integer pageSize, String tab) {
		List<Topic> list = rootTopicDao.selectAllByTab((pageNumber - 1) * pageSize, pageSize,tab);
		int total = rootTopicDao.countTopicByTab(tab);
		return new Page<>(list, pageNumber, pageSize, total);
	}
	
	/**
	 * 根据节点查询所有话题
	 */
	@Override
	public Page<Topic> pageAllByNode(Integer pageNumber, Integer pageSize, String nodeTitle) {
		List<Topic> list = rootTopicDao.selectAllByNode((pageNumber - 1) * pageSize, pageSize,nodeTitle);
		int total = rootTopicDao.countTopicByNode(nodeTitle);
		return new Page<>(list, pageNumber, pageSize, total);
	}

	/**
	 * 根据节点查询精华话题
	 */
	@Override
	public Page<Topic> pageGood(Integer pageNumber, Integer pageSize,String nodeTitle) {
		List<Topic> list = rootTopicDao.selectAllGood((pageNumber - 1) * pageSize, pageSize,nodeTitle);
		int total = rootTopicDao.countTopicGoodByNode(nodeTitle);
		return new Page<>(list, pageNumber, pageSize, total);
	}

	/**
	 * 根据节点查询无人回复的话题
	 */
	@Override
	public Page<Topic> pageNoReply(Integer pageNumber, Integer pageSize,String nodeTitle) {
		List<Topic> list = rootTopicDao.selectAllNoReply((pageNumber - 1) * pageSize, pageSize,nodeTitle);
		int total = rootTopicDao.countTopicNoReplyByNode(nodeTitle);
		return new Page<>(list, pageNumber, pageSize, total);
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
	public Page<Topic> pageByAuthor(Integer pageNumber, Integer pageSize, String author) {
		int totalRow = rootTopicDao.countAllByName(author);
		List<Topic> list = rootTopicDao.selectByAuthor(author, (pageNumber - 1) * pageSize, pageSize);
		return new Page<>(list, pageNumber, pageSize, totalRow);
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
	 * 根据作者删除话题
	 */
	@Override
	public void deleteByAuthor(String author) {
		rootTopicDao.deleteByAuthor(author);
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
	/*@Transactional
	@Override
	public TopicExecution saveTopic(Topic topic) {
		rootTopicDao.insert(topic);
		// 发贴加积分
		rootUserDao.updateScoreByName(Integer.valueOf(systemConfigService.getByKey("create_topic_score").getValue()), topic.getAuthor());
		return new TopicExecution(topic.getTitle(), InsertTopicEnum.SUCCESS, topic);
	}*/
	
	/*@Override
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
	}*/

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
	public Page<Topic> findCollectsById(Integer pageNumber, Integer pageSize, Integer uid) {
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
	public Page<Topic> pageAllNewest(Integer pageNumber, Integer pageSize,String nodeTitle) {
		List<Topic> list = rootTopicDao.selectAllNewest((pageNumber - 1) * pageSize, pageSize,nodeTitle);
		int total = rootTopicDao.countTopicByNode(nodeTitle);
		return new Page<>(list, pageNumber, pageSize, total);
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
	public Page<Tag> findByTag(Integer pageNumber, Integer pageSize) {
		int totalRow = rootTopicDao.countTag();
		List<Tag> list = rootTopicDao.selectAllTag((pageNumber - 1) * pageSize, pageSize);
		return new Page<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 根据标签查询话题
	 */
	@Override
	public Page<Topic> pageByTag(String tag, Integer pageNumber, Integer pageSize) {
		int totalRow = rootTopicDao.countByTag(tag);
		List<Topic> list = rootTopicDao.selectByTag(tag, (pageNumber - 1) * pageSize, pageSize);
		return new Page<>(list, pageNumber, pageSize, totalRow);
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
	public Page<Topic> pageLike(Integer pageNumber, Integer pageSize, String like) {
		List<Topic> list = rootTopicDao.selectByLike(like, (pageNumber - 1) * pageSize, pageSize);
		int totalRow = rootTopicDao.countLike(like);
		return new Page<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 根据板块和昵称分页查询话题
	 */
	@Override
	public Page<Topic> pageAllByPtabAndAuthor(Integer pageNumber, Integer pageSize, String ptab, String author) {
		int totalRow = rootTopicDao.countAllByNameAndPtab(author, ptab);
		List<Topic> list = rootTopicDao.selectAllByPtabAndAuthor((pageNumber - 1) * pageSize, pageSize, ptab, author);
		return new Page<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 首页-最热话题
	 */
	@Override
	public Page<Topic> findIndexHot(Integer pageNumber, Integer pageSize, String tab) {
		int totalRow = rootTopicDao.countIndexHot(tab);
		List<Topic> list = rootTopicDao.selectIndexHot((pageNumber - 1) * pageSize, pageSize, tab);
		return new Page<>(list, pageNumber, pageSize, totalRow);
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
	public Page<Topic> pageForAdmin(String author, String startDate, String endDate, Integer pageNumber,
			Integer pageSize) {
		List<Topic> list = rootTopicDao.selectAllForAdmin(author, startDate, endDate, (pageNumber - 1) * pageSize, pageSize);
		int totalRow = countAllForAdmin(author, startDate, endDate);
		return new Page<Topic>(list, pageNumber, pageSize, totalRow);
	}

	@Override
	public int countAllForAdmin(String author,String startDate,String endDate) {
		return rootTopicDao.countAllForAdmin(author, startDate, endDate);
	}

	
	@Override
	public Topic findById(Integer id) {
		return rootTopicDao.selectByTopicId(id);
	}
	
	@Override
	public boolean save(TopicDTO topicDTO) {
		String title = topicDTO.getTitle();
		if (StringUtils.isEmail(title)) {
			throw new TopicException(TopicErrorCodeEnum.TITLE_MISSING);
		}
		Integer nodeId = topicDTO.getNodeDTO().getNodeId();
		if (nodeId == null) {
			throw new TopicException(TopicErrorCodeEnum.NODE_ID_MISSING);
		}
		NodeDTO nodeDTO = nodeService.getById(nodeId);
		if (nodeDTO == null) {
			throw new TopicException(TopicErrorCodeEnum.INVALIDATE_NODE);
		}
		Integer userId = topicDTO.getUserDTO().getUserId();
		if (userId == null) {
			throw new TopicException(TopicErrorCodeEnum.USER_ID_MISSING);
		}
		UserDTO userDTO = userService.getById(userId);
		if (userDTO == null) {
			throw new TopicException(TopicErrorCodeEnum.INVALIDATE_USER);
		}
		return super.save(topicDTO);
	}

	@Override
	public Function<? super TopicDTO, ? extends Topic> getDTO2DOMapper() {
		return topicDTO -> {
			Topic topic = new Topic();
			BeanUtils.copyProperties(topicDTO, topic);
			topic.setTabId(topicDTO.getTabDTO().getId());
			topic.setNodeId(topicDTO.getNodeDTO().getNodeId());
			topic.setUserId(topicDTO.getUserDTO().getUserId());
			return topic;
		};
	}

	@Override
	public Function<? super Topic, ? extends TopicDTO> getDO2DTOMapper() {
		return topic -> {
			TopicDTO topicDTO = new TopicDTO();
			BeanUtils.copyProperties(topic, topicDTO);
			TabDTO tabDTO = tabService.getById(topic.getTabId());
			UserDTO userDTO = userService.getById(topic.getUserId());
			NodeDTO nodeDTO = nodeService.getById(topic.getNodeId());
			topicDTO.setTabDTO(tabDTO);
			topicDTO.setNodeDTO(nodeDTO);
			topicDTO.setUserDTO(userDTO);
			return topicDTO;
		};
	}

	@Override
	public BaseDao<Topic> getDao() {
		return rootTopicDao;
	}

}
