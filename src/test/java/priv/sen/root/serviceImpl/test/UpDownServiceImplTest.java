package priv.sen.root.serviceImpl.test;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dto.DMLExecution;
import cn.roothub.entity.UpDown;
import cn.roothub.service.UpDownService;
import priv.sen.root.dao.test.BaseTest;

public class UpDownServiceImplTest extends BaseTest{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UpDownService upDownService;
	
	/**
	 * 统计赞同或者反对的数量
	 * @throws Exception
	 */
	@Test
	public void countUpOrDownTest() throws Exception{
		int countUpOrDown = upDownService.countUpOrDown(58, 0);
		System.out.println(countUpOrDown);
	}
	
	/**
	 * 添加赞同或者反对的记录
	 * @throws Exception
	 */
	@Test
	public void saveTest() throws Exception{
		UpDown upDown = new UpDown();
		upDown.setUid(1);
		upDown.setTid(60);
		upDown.setUpDown(false);
		upDown.setCreateDate(new Date());
		upDown.setDelete(false);
		DMLExecution save = upDownService.save(upDown);
		logger.debug(save.toString());
	}
}
