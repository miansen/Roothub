package cn.roothub.test.dao;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dao.NoticeDao;
import cn.roothub.entity.Notice;
import cn.roothub.service.CollectService;
import cn.roothub.service.TopicService;
import cn.roothub.test.base.BaseTest;

/**
 * 测试通知
 * @author sen
 * 2018年6月18日
 * 下午10:15:08
 * TODO
 */
public class NoticeDaoTest extends BaseTest{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NoticeDao rootNoticeDao;
	
	/**
	 * 查询所有通知
	 * @throws Exception
	 */
	@Test
	public void selectAllByAuthorTest() throws Exception{
		List<Notice> selectAll = rootNoticeDao.selectAll("public", 0, 20);
		logger.debug(selectAll.toString());
	}

	/**
	 * 将通知都置为已读
	 * @throws Exception
	 */
	@Test
	public void updateByAuthorNameTest() throws Exception{
		rootNoticeDao.updateByAuthorName("public");
	}
	
	/**
	 * 统计未读通知的数量
	 * @throws Exception
	 */
	@Test
	public void countNotReadByAuthorTest() throws Exception{
		int countNotReadByAuthor = rootNoticeDao.countNotReadByAuthor("private");
		System.out.println(countNotReadByAuthor);
	}
	
	/**
	 * 统计所有通知的数量
	 * @throws Exception
	 */
	@Test
	public void countByAuthorTest() throws Exception{
		int countByAuthor = rootNoticeDao.countByAuthor("public");
		System.out.println(countByAuthor);
	}
}
