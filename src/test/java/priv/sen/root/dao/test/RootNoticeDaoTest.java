package priv.sen.root.dao.test;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dao.RootNoticeDao;
import cn.roothub.entity.RootNotice;
import cn.roothub.service.CollectService;
import cn.roothub.service.RootTopicService;

/**
 * 测试通知
 * @author sen
 * 2018年6月18日
 * 下午10:15:08
 * TODO
 */
public class RootNoticeDaoTest extends BaseTest{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RootNoticeDao rootNoticeDao;
	
	/**
	 * 查询所有通知
	 * @throws Exception
	 */
	@Test
	public void selectAllByAuthorTest() throws Exception{
		List<RootNotice> selectAll = rootNoticeDao.selectAll("public", 0, 20);
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
