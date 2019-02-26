package cn.roothub.test.dao;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import cn.roothub.dao.UpDownDao;
import cn.roothub.entity.UpDown;
import cn.roothub.test.base.BaseTest;

/**
 * 点赞测试
 * @author sen
 * 2018年8月11日
 * 上午10:52:47
 * TODO
 */
public class UpDownDaoTest extends BaseTest{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UpDownDao upDownDao;
	
	/**
	 * 添加赞同或者反对的记录
	 * @throws Exception
	 */
	@Test
	public void insertTest() throws Exception{
		UpDown upDown = new UpDown();
		upDown.setUid(1);
		upDown.setTid(58);
		upDown.setUpDown(true);
		upDown.setCreateDate(new Date());
		upDown.setDelete(false);
		int insert = upDownDao.insert(upDown);
		System.out.println(insert);
	}
	
	/**
	 * 统计赞同或者反对的数量
	 * @throws Exception
	 */
	@Test
	public void countUpOrDownTest() throws Exception{
		int countUpOrDown = upDownDao.countUpOrDown(58, 0);
		System.out.println(countUpOrDown);
	}
	
	/**
	 * 更新状态
	 * @throws Exception
	 */
	@Test
	public void updateTest() throws Exception{
		UpDown upDown = new UpDown();
		upDown.setUid(1);
		upDown.setTid(58);
		upDown.setUpDown(false);
		upDown.setCreateDate(new Date());
		upDown.setDelete(false);
		int update = upDownDao.update(upDown);
		System.out.println(update);
		System.out.println(upDown.isUpDown()?1:0);
	}
	
	/**
	 * 是否已点赞或者点踩
	 * @throws Exception
	 */
	@Test
	public void isUpOrDownTest() throws Exception{
		int upOrDown = upDownDao.isUpOrDown(1, 58);
		System.out.println(upOrDown);
	}
}
