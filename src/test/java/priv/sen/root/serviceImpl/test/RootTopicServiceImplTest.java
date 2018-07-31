package priv.sen.root.serviceImpl.test;

import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.Result;
import cn.roothub.dto.RootTopicExecution;
import cn.roothub.entity.RootTopic;
import cn.roothub.entity.Tag;
import cn.roothub.service.RootTopicService;
import priv.sen.root.dao.test.BaseTest;

public class RootTopicServiceImplTest extends BaseTest{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	RootTopicService rootTopicService;
	
	/**
	 * 测试分页查询所有话题
	 * @throws Exception
	 */
	@Test
	public void pageTest() throws Exception{
		PageDataBody<RootTopic> page = rootTopicService.page(1, 20, "all","all");
		System.out.println(page);
	}
	
	/**
	 * 测试根据昵称分页查询用户的所有话题
	 * @throws Exception
	 */
	@Test
	public void pageByAuthor() throws Exception{
		PageDataBody<RootTopic> page = rootTopicService.pageByAuthor(1, 20, "public");
		Result<PageDataBody<RootTopic>> result = new Result<>(true, page);
		System.out.println(result);
	}
	
	/**
	 * 测试查询所有话题
	 * @throws Exception
	 */
	@Test
	public void findAllTest() throws Exception{
		List<RootTopic> findAll = rootTopicService.findAll();
		System.out.println(findAll);
	}
	/**
	 * 测试通过ID查询话题
	 * @throws Exception
	 */
	@Test
	public void findById() throws Exception{
		RootTopic findByTopicId = rootTopicService.findByTopicId(30);
		System.out.println(findByTopicId);
	}
	
	/**
	 * 测试发布话题
	 * @throws Exception
	 */
	@Test
	public void saveTopicTest() throws Exception{
		RootTopic topic = new RootTopic();
		topic.setTab("all");
		topic.setTitle("测试发布话题29");
		topic.setTag("delete");
		topic.setCreateDate(new Date());
		topic.setUpdateDate(new Date());
		topic.setLastReplyTime(new Date());
		topic.setLastReplyAuthor("public");
		topic.setViewCount(10);
		topic.setAuthor("public");
		topic.setTop(true);
		topic.setGood(true);
		topic.setShowStatus(true);
		topic.setReplyCount(100);
		topic.setIsDelete(false);
		topic.setTagIsCount(true);
		topic.setContent("哈哈哈哈哈哈哈");
		RootTopicExecution saveTopic = rootTopicService.saveTopic(topic);
		System.out.println(saveTopic);
	}
	/**
	 * 测试-分页查询所有标签
	 * @throws Exception
	 */
	@Test
	public void findByTagTest() throws Exception {
		PageDataBody<Tag> tag = rootTopicService.findByTag(1, 20);
		System.out.println(tag);
	}
	
	/**
	 * 测试-根据标签查询话题
	 * @throws Exception
	 */
	@Test
	public void pageByTagTest() throws Exception{
		PageDataBody<RootTopic> pageByTag = rootTopicService.pageByTag("delete", 1, 20);
		System.out.println(pageByTag);
	}
	
	/**
	 * 分页模糊查询
	 * @throws Exception
	 */
	@Test
	public void pageLikeTest() throws Exception{
		PageDataBody<RootTopic> pageLike = rootTopicService.pageLike(1, 20, "测试");
		System.out.println(pageLike);
	}
	
	/**
	 * 根据板块和昵称分页查询话题
	 * @throws Exception
	 */
	@Test
	public void pageAllByAuthorTest() throws Exception{
		PageDataBody<RootTopic> page = rootTopicService.pageAllByPtabAndAuthor(1, 20, "qna", "Tyche");
		logger.info(page.toString());
	}
}
