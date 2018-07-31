package priv.sen.root.dao.test;

import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dao.TabDao;
import cn.roothub.entity.Tab;

public class TabDaoTest extends BaseTest{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TabDao tabDao;
	
	@Test
	public void test01() throws Exception{
		List<Tab> tab = tabDao.selectAll();
		logger.debug(tab.toString());
	}
}
