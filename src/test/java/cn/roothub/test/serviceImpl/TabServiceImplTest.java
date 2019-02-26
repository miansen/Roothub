package cn.roothub.test.serviceImpl;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.entity.Tab;
import cn.roothub.service.TabService;
import cn.roothub.test.base.BaseTest;

public class TabServiceImplTest extends BaseTest{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TabService tabService;
	
	@Test
	public void test01() throws Exception{
		List<Tab> tab = tabService.selectAll();
		logger.debug(tab.toString());
	}
	
}
