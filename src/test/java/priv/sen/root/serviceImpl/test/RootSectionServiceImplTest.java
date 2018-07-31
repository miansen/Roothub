package priv.sen.root.serviceImpl.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dto.RootSectionExecution;
import cn.roothub.entity.RootSection;
import cn.roothub.service.RootSectionService;
import priv.sen.root.dao.test.BaseTest;

public class RootSectionServiceImplTest extends BaseTest{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private RootSectionService rootSectionService;
	
	@Test
	public void saveTest() throws Exception{
		RootSection rootSection = new RootSection();
		rootSection.setSectionName("等待评论");
		rootSection.setSectionTab("WFC");
		rootSection.setShowStatus(true);
		rootSection.setDisplayIndex(4);
		rootSection.setDefaultShow(true);
		rootSection.setPid(0);
		rootSection.setCreateDate(new Date());
		RootSectionExecution save = rootSectionService.save(rootSection);
		logger.debug(save.toString());
	}
	
	@Test
	public void findAllTest() throws Exception{
		List<RootSection> findAll = rootSectionService.findAll();
		logger.debug(findAll.toString());
	}
	
}
