package cn.roothub.test.dao;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dao.NodeDao;
import cn.roothub.entity.Node;
import cn.roothub.test.base.BaseTest;

/**
 * @author miansen.wang
 * @date 2018年11月3日 上午11:51:43
 */
public class NodeDaoTest extends BaseTest{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NodeDao nodeDao;
	
	@Test
	public void selectAll() throws Exception {
		List<Node> list = nodeDao.selectAllByTab("lang", 0, 10);
		logger.debug(list.toString());
	}
	
	//查找子节点
	@Test
	public void selectChildrenNodeTest() throws Exception{
		List<Node> selectChildrenNode = nodeDao.selectChildrenNode("lang", 0, 10);
		logger.debug(selectChildrenNode.toString());
	}
	
	//查找相邻节点
	@Test
	public void selectAtherNodeTest() throws Exception{
		List<Node> selectAtherNode = nodeDao.selectAtherNode("java", "lang", 0, 10);
		logger.debug(selectAtherNode.toString());
	}
	
	//查找相邻顶级节点
	@Test
	public void selectAtherParentNodeTest() throws Exception{
		List<Node> selectAtherParentNode = nodeDao.selectAtherParentNode("lang", 14, 0, 10);
		logger.debug(selectAtherParentNode.toString());
	}
}
