package priv.sen.root.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dao.CollectDao;
import cn.roothub.entity.Collect;
import cn.roothub.entity.RootTopic;

public class CollectDaoTest extends BaseTest{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CollectDao collectDao;
	
	/**
	 * 查询收藏的话题
	 * @throws Exception
	 */
	@Test
	public void selectAllByCollectTest() throws Exception{
		List<RootTopic> selectAllByCollect = collectDao.selectAllByCollect(0, 20, 1);
		logger.info(selectAllByCollect.toString());
	}
	
	/**
	 * 添加收藏
	 */
	@Test
	public void insertTest() throws Exception{
		Collect collect = new Collect();
		collect.setUid(1);
		collect.setTid(2);
		collect.setCreateDate(new Date());
		int insert = collectDao.insert(collect);
		System.out.println(insert);
	}
	
	/**
	 * 取消收藏
	 * @throws Exception
	 */
	@Test
	public void deleteTest() throws Exception{
		int delete = collectDao.delete(1, 1);
		System.out.println(delete);
	}
	
	/**
	 * 统计收藏话题的数量
	 * @throws Exception
	 */
	@Test
	public void countTest() throws Exception{
		int count = collectDao.count(1);
		System.out.println(count);
	}
	
	/**
	 * 判断用户是否已收藏此话题
	 * @throws Exception
	 */
	@Test
	public void isCollectTest() throws Exception{
		int collect = collectDao.isCollect(1, 2);
		System.out.println(collect);
	}
	
	/**
	 * 统计话题被收藏的数量
	 * @throws Exception
	 */
	@Test
	public void countByTidTest() throws Exception{
		int countByTid = collectDao.countByTid(2);
		System.out.println(countByTid);
	}
}
