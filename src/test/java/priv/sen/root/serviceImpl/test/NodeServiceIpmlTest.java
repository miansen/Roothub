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
		List<Node> list = nodeService.findAll(null, null, null);
		System.out.println(list);
	}
}
