package priv.sen.root.serviceImpl.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import priv.sen.root.dao.test.BaseTest;
import priv.sen.root.dto.PageDataBody;
import priv.sen.root.entity.RootNotice;
import priv.sen.root.service.RootNoticeService;

/**
 * 测试通知列表
 * @author sen
 * 2018年6月18日
 * 下午10:10:29
 * TODO
 */
public class RootNoticeServiceImplTest extends BaseTest{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RootNoticeService rootNoticeService;
	
	/**
	 * 查询所有通知
	 * @throws Exception
	 */
	@Test
	public void pageByAuthorTest() throws Exception{
		PageDataBody<RootNotice> pageByAuthor = rootNoticeService.pageByAuthor(1, 20, "public");
		logger.debug(pageByAuthor.toString());
	}
	
	/**
	 * 统计未读通知的数量
	 * @throws Exception
	 */
	@Test
	public void countNotReadNoticeTest() throws Exception{
		int countNotReadNotice = rootNoticeService.countNotReadNotice("private");
		System.out.println(countNotReadNotice);
	}
	
	/**
	 * 将通知置为已读
	 * @throws Exception
	 */
	@Test
	public void updateIsReadTest() throws Exception{
		rootNoticeService.updateIsRead("private");
	}
	
	/**
	 * 统计所有通知的数量
	 * @throws Exception
	 */
	@Test
	public void countByAuthorTest() throws Exception{
		int countByAuthor = rootNoticeService.countByAuthor("public");
		System.out.println(countByAuthor);
	}
}
