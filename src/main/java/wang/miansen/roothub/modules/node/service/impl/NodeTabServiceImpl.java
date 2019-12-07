package wang.miansen.roothub.modules.node.service.impl;

import java.util.List;

import wang.miansen.roothub.modules.node.model.NodeTab;
import wang.miansen.roothub.modules.node.service.NodeTabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.miansen.roothub.modules.node.dao.NodeTabDao;

@Service
public class NodeTabServiceImpl implements NodeTabService {

	@Autowired
	private NodeTabDao nodeTabDao;

	//查询所有节点板块
	@Override
	public List<NodeTab> findAll() {
		return nodeTabDao.selectAll();
	}

	//根据ID查询节点板块
	@Override
	public NodeTab findById(Integer nodeTabId) {
		return nodeTabDao.selectById(nodeTabId);
	}

	//根据编码查询节点板块
	@Override
	public NodeTab findByCode(String nodeTabCode) {
		return nodeTabDao.selectByCode(nodeTabCode);
	}

	//添加节点板块
	@Override
	public void save(NodeTab nodeTab) {
		nodeTabDao.insert(nodeTab);
	}

	//根据ID更新节点板块
	@Override
	public void update(NodeTab nodeTab) {
		nodeTabDao.updateById(nodeTab);
	}

	//根据ID删除节点板块
	@Override
	public void deleteById(Integer nodeTabId) {
		nodeTabDao.deleteById(nodeTabId);
	}
	
}
