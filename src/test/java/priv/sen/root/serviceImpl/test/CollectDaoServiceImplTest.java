package priv.sen.root.serviceImpl.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import priv.sen.root.dao.test.BaseTest;
import priv.sen.root.dto.PageDataBody;
import priv.sen.root.entity.RootTopic;
import priv.sen.root.service.CollectService;

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
