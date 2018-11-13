package priv.sen.root.serviceImpl.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import cn.roothub.entity.Node;
import cn.roothub.service.NodeService;
import priv.sen.root.dao.test.BaseTest;

public class NodeServiceIpmlTest extends BaseTest {
	
	@Autowired
	private NodeService nodeService;
	
	@Test
	public void findAll() throws Exception {
		List<Node> list = nodeService.findAllByTab(null, null, null);
		System.out.println(list);
	}
	
	/*@Test
	public void findAtherByNodeCodeTest() throws Exception {
		List<Node> findAtherByNodeCode = nodeService.find("java", 0, 10);
		System.out.println(findAtherByNodeCode);
	}*/
	
	@Test
	public void adjacencyNodeTest() throws Exception{
		Node node = nodeService.findByNodeCode("java");
		List<Node> adjacencyNode = nodeService.adjacencyNode(node);
		System.out.println(adjacencyNode);
	}
	
	@Test
	public void findByNodeCodeTest() throws Exception{
		Node node = nodeService.findByNodeCode("lang");
		Node findByNodeCode = nodeService.findByNodeCode(node.getParentNodeCode());
		System.out.println(findByNodeCode);
	}
}
