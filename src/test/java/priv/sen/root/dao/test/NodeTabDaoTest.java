package priv.sen.root.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dao.NodeTabDao;
import cn.roothub.entity.NodeTab;

public class NodeTabDaoTest extends BaseTest{
	
	@Autowired
	private NodeTabDao nodeTabDao;
	
	@Test
	public void selectAllTest() throws Exception {
		List<NodeTab> selectAll = nodeTabDao.selectAll();
		System.out.println(selectAll);
	}
}
