package cn.roothub.test.dao;

import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dao.TabDao;
import cn.roothub.entity.Tab;
import cn.roothub.test.base.BaseTest;

public class TabDaoTest extends BaseTest{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TabDao tabDao;
	
	@Test
	public void test01() throws Exception{
		List<Tab> tab = tabDao.selectAll();
		Tab remove = tab.remove(0);
		Tab remove2 = tab.remove(0);
		logger.debug(remove.toString());
		logger.debug(remove2.toString());
		logger.debug(tab.toString());
	}
}
