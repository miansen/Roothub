package priv.sen.root.serviceImpl.test;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dto.PageDataBody;
import cn.roothub.entity.Follow;
import cn.roothub.entity.RootTopic;
import cn.roothub.entity.RootUser;
import cn.roothub.service.FollowService;
import priv.sen.root.dao.test.BaseTest;

public class FollowServiceImplTest extends BaseTest{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FollowService followService;
	
	/**
	 * 关注列表
	 * @throws Exception
	 * createDate=Sun May 06 20:10:37 CST 2018
	 * createDate=Sun Jul 01 20:49:23 CST 2018
	 */
	@Test
	public void pageTest() throws Exception{
		PageDataBody<RootUser> page = followService.page(1, 10, 1);
		logger.info(page.toString());
	}
	
	/**
	 * 关注
	 * @throws Exception
	 */
	@Test
	public void insertTest() throws Exception{
		Follow follow = new Follow();
		follow.setUid(1);
		follow.setFid(5);
		follow.setCreateDate(new Date());
		int insert = followService.insert(follow);
		System.out.println(insert);
	}
	
	/**
	 * 取消关注
	 * @throws Exception
	 */
	@Test
	public void deleteTest() throws Exception{
		int delete = followService.delete(1, 2);
		System.out.println(delete);
	}
	
	/**
	 * 是否已关注
	 * @throws Exception
	 */
	@Test
	public void isCollectTest() throws Exception{
		int follow = followService.isFollow(1, 4);
		System.out.println(follow);
	}
	
	/**
	 * 我关注的数量
	 * @throws Exception
	 */
	@Test
	public void countByUidTest() throws Exception{
		int countByUid = followService.countByUid(1);
		System.out.println(countByUid);
	}
	
	/**
	 * 我被关注的数量
	 * @throws Exception
	 */
	@Test
	public void countByFidTest() throws Exception{
		int countByFid = followService.countByFid(3);
		System.out.println(countByFid);
	}
	
	/**
	 * 关注我的人
	 * @throws Exception
	 */
	@Test
	public void followMeTest() throws Exception{
		PageDataBody<RootUser> followMe = followService.followMe(1, 10, 1);
		logger.info(followMe.toString());
	}
	
	/**
	 * 关注人的主题
	 * @throws Exception
	 */
	@Test
	public void pageTopicTest() throws Exception{
		PageDataBody<RootTopic> pageTopic = followService.pageTopic(1, 20, 1);
		logger.info(pageTopic.toString());
	}
}
