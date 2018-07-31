package priv.sen.root.serviceImpl.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dto.PageDataBody;
import cn.roothub.entity.RootTopic;
import cn.roothub.service.CollectService;
import priv.sen.root.dao.test.BaseTest;

public class CollectDaoServiceImplTest extends BaseTest{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CollectService collectDaoService;
	
	@Test
	public void pageTest() throws Exception{
		PageDataBody<RootTopic> page = collectDaoService.page(1, 20, 1);
		logger.info(page.toString());
	}
}
