package priv.sen.root.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import priv.sen.root.dao.RootReplyDao;
import priv.sen.root.entity.ReplyAndTopicByName;
import priv.sen.root.entity.RootReply;

public class RootReplyDaoTest extends BaseTest{

	@Autowired
	private RootReplyDao rootReplyDao;
	
	/**
	 * 测试-根据评论人昵称统计评论数量
	 * @throws Exception
	 */
	@Test
	public void countByNameTest() throws Exception{
		int countByName = rootReplyDao.countByName("void");
		System.out.println(countByName);
	}
	
	/**
	 * 测试-根据评论人昵称关联话题表查询全部评论
	 * @throws Exception
	 */
	@Test
	public void selectAllByNameAndTopicTest() throws Exception{
		List<ReplyAndTopicByName> list = rootReplyDao.selectAllByNameAndTopic("void", 0, 20);
		System.out.println(list);
	}
	
}
