package cn.roothub.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.roothub.dao.NodeDao;
import cn.roothub.dto.PageDataBody;
import cn.roothub.entity.Node;
import cn.roothub.service.NodeService;
import cn.roothub.service.TopicService;

/**
 * @author miansen.wang
 * @date 2018年11月3日 下午2:48:24
 */
@Service
public class NodeServiceImpl implements NodeService{

	@Autowired
	private NodeDao nodeDao;
	
	@Autowired
	private TopicService topicService;
	
	//根据板块查询节点
	@Override
	public List<Node> findAllByTab(String tabName, Integer pageNumber, Integer pageSize) {
		return nodeDao.selectAllByTab(tabName, pageNumber, pageSize);
	}

	@Override
	public Node findByTitle(String title) {
		return nodeDao.selectByTitle(title);
	}
	
	@Override
	public Node findById(Integer id) {
		return nodeDao.selectById(id);
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

	// 后台节点列表
	@Override
	public PageDataBody<Node> pageForAdmin(String nodeTitle, Integer pageNumber, Integer pageSize) {
		List<Node> list = nodeDao.listForAdmin(nodeTitle, (pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<Node>(list, pageNumber, pageSize, count(nodeTitle));
	}

	// 统计节点列表
	@Override
	public int count(String nodeTitle) {
		return nodeDao.count(nodeTitle);
	}

	@Transactional
	@Override
	public void update(Integer nodeId, String nodeTitle, String avatarNormal, String avatarLarge, String nodeDesc) {
		Node node = findById(nodeId);
		// 先更新话题的节点名称
		if(!nodeTitle.equals(node.getNodeTitle())) {
			topicService.updateNodeTitile(node.getNodeTitle(), nodeTitle);
		}
		node.setNodeTitle(nodeTitle);
		node.setAvatarNormal(avatarNormal);
		node.setAvatarLarge(avatarLarge);
		node.setNodeDesc(nodeDesc);
		node.setUrl("/n/" + nodeTitle);
		node.setUpdateDate(new Date());
		// 最后在更新节点
		nodeDao.update(node);
	}

	/**
	 * 删除节点
	 */
	@Transactional
	@Override
	public void deleteById(Integer id) {
		Node node = findById(id);
		// 先将话题的节点设置为 null
		topicService.updateNodeTitile(node.getNodeTitle(), null);
		// 然后在删除节点
		nodeDao.deleteById(id);
	}

	/**
	 * 统计当天的节点数量
	 */
	@Override
	public int countToday() {
		return nodeDao.countToday();
	}
}
