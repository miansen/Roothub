package wang.miansen.roothub.modules.node.service;

import java.util.List;
import wang.miansen.roothub.modules.node.model.NodeTab;

public interface NodeTabService {

	/**
	 * 查询所有节点板块
	 * @return
	 */
	List<NodeTab> findAll();

	/**
	 * 根据ID查询节点板块
	 * @param nodeTabId
	 * @return
	 */
	NodeTab findById(Integer nodeTabId);
	
	/**
	 * 根据编码查询板块
	 * @param nodeTabCode
	 * @return
	 */
	NodeTab findByCode(String nodeTabCode);
	
	/**
	 * 添加节点板块
	 * @param nodeTab
	 */
	void save(NodeTab nodeTab);
	
	/**
	 * 更新节点板块
	 * @param nodeTab
	 */
	void update(NodeTab nodeTab);
	
	/**
	 * 根据ID删除节点板块
	 * @param nodeTabId
	 */
	void deleteById(Integer nodeTabId);
}
