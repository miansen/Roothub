package cn.roothub.test.dao;

import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dao.TopicDao;
import cn.roothub.entity.Topic;
import cn.roothub.test.base.BaseTest;

public class TopicDaoTest extends BaseTest {

	@Autowired
	private TopicDao rootTopicDao;
	
	@Test
	public void selecrAllTest() throws Exception{
		List<Topic> selectAll = rootTopicDao.selectAll();
		System.out.println(selectAll);
	}
	
	@Test
	public void selectByLikeTest() throws Exception{
		List<Topic> selectByLike = rootTopicDao.selectByLike("测试", 1, 20);
		System.out.println(selectByLike);
	}
	
	@Test
	public void countLikeTest() throws Exception{
		int countLike = rootTopicDao.countLike("测试");
		System.out.println(countLike);
	}
	
	@Test
	public void selectIndexHotTest() throws Exception{
		List<Topic> selectIndexHot = rootTopicDao.selectIndexHot(0, 50,"good");
		System.out.println(selectIndexHot);
	}
	
	@Test
	public void countIndexHot() throws Exception{
		int countIndexHot = rootTopicDao.countIndexHot("good");
		System.out.println(countIndexHot);
	}
	
	@Test
	public void selectTodayNoReplyTest() throws Exception{
		List<Topic> selectTodayNoReply = rootTopicDao.selectTodayNoReply(0, 10);
		System.out.println(selectTodayNoReply);
	}
	
	/**
	 * 作者的其他话题
	 * @throws Exception
	 */
	@Test
	public void selectOther() throws Exception{
		List<Topic> selectOther = rootTopicDao.selectOther("083013", 80);
		System.out.println(selectOther);
	}
	
	@Test
	public void selectAllForAdminTest() {
		List<Topic> selectAllForAdmin = rootTopicDao.selectAllForAdmin(null, "2018-08-01", "2019-08-19", 0, 3);
		selectAllForAdmin.forEach(System.out::println);
	}
	
	@Test
	public void countAllForAdminTest() {
		int i = rootTopicDao.countAllForAdmin(null, "2018-08-01", "2019-08-19");
		System.out.println(i);
	}
}
