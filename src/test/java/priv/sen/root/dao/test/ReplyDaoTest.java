package priv.sen.root.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dao.ReplyDao;
import cn.roothub.entity.ReplyAndTopicByName;
import cn.roothub.entity.Reply;

public class ReplyDaoTest extends BaseTest{

	@Autowired
	private ReplyDao rootReplyDao;
	
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
