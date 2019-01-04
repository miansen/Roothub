package cn.roothub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.roothub.dao.NodeDao;
import cn.roothub.entity.Node;
import cn.roothub.service.NodeService;

/**
 * @author miansen.wang
 * @date 2018年11月3日 下午2:48:24
 */
@Service
public class NodeServiceImpl implements NodeService{

	@Autowired
	private NodeDao nodeDao;
	
	//根据板块查询节点
	@Override
	public List<Node> findAllByTab(String tabName, Integer pageNumber, Integer pageSize) {
		return nodeDao.selectAllByTab(tabName, pageNumber, pageSize);
	}

	@Override
	public Node findByTitle(String title) {
		return nodeDao.selectByTitle(title);
	}

	//子节点
	@Override
	public List<Node> findChildrenNode(String nodeTitle, Integer pageNumber, Integer pageSize) {
		return nodeDao.selectChildrenNode(nodeTitle, pageNumber, pageSize);
	}

	//相邻节点
	@Override
	public List<Node> adjacencyNode(Node node) {
		if(node.getParentNodeCode() != null) {
			return nodeDao.selectAtherNode(node.getNodeCode(), node.getParentNodeCode(), null, null);
		}else {
			return nodeDao.selectAtherParentNode(node.getNodeCode(), node.getTabId(), null, null);
		}
	}

	//查询全部节点
	@Override
	public List<Node> findAll(Integer pageNumber, Integer pageSize) {
		return nodeDao.selectAll(pageNumber, pageSize);
	}
}
