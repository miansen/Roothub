package priv.sen.root.serviceImpl.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dto.SectionExecution;
import cn.roothub.entity.NodeTab;
import cn.roothub.service.NodeTabService;
import priv.sen.root.dao.test.BaseTest;

public class NodeTabServiceImplTest extends BaseTest{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private NodeTabService nodeTabService;
	
	@Test
	public void saveTest() throws Exception{
		/*NodeTab rootSection = new NodeTab();
		rootSection.setSectionName("等待评论");
		rootSection.setSectionTab("WFC");
		rootSection.setShowStatus(true);
		rootSection.setDisplayIndex(4);
		rootSection.setDefaultShow(true);
		rootSection.setPid(0);
		rootSection.setCreateDate(new Date());
		SectionExecution save = rootSectionService.save(rootSection);
		logger.debug(save.toString());*/
	}
	
	@Test
	public void findAllTest() throws Exception{
		List<NodeTab> findAll = nodeTabService.findAll();
		logger.debug(findAll.toString());
	}
	
}
