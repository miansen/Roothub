package wang.miansen.roothub.modules.node.service;

import java.util.List;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.modules.node.model.Node;

/**
 * @author miansen.wang
 * @date 2018年11月3日 下午2:46:55
 */
public interface NodeService {

	/**
	 * 查询全部节点
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<Node> findAll(Integer pageNumber, Integer pageSize);
	
	/**
	 * 根据板块ID查询节点
	 * @param tabName
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<Node> findAllByTab(String tabName,Integer pageNumber, Integer pageSize);
	
	/**
	 * 根据名称查询对应的节点
	 * @param Title
	 * @return
	 */
	Node findByTitle(String Title);
	
	/**
	 * 根据ID查询对应的节点
	 * @param id
	 * @return
	 */
	Node findById(Integer id);
	
	/**
	 * 子节点
	 * @param nodeTitle
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<Node> findChildrenNode(String nodeTitle,Integer pageNumber, Integer pageSize);
	
	/**
	 * 相邻节点
	 * @param node
	 * @return
	 */
	List<Node> adjacencyNode(Node node);
	
	/**
	 * 后台节点列表
	 * @param nodeTitle:节点名称
	 * @param pageNumber:第几页
	 * @param pageSize:数据量
	 * @return
	 */
	Page<Node> pageForAdmin(String nodeTitle, Integer pageNumber, Integer pageSize);
	
	/**
	 * 统计节点
	 * @param nodeTitle:节点名称
	 * @return
	 */
	int count(String nodeTitle);
	
	/**
	 * 更新节点
	 * @param nodeId 节点ID
	 * @Param parentNodeCode 父节点
	 * @param nodeTitle 节点名称
	 * @param avatarNormal 图标
	 * @param avatarLarge 背景图
	 * @param nodeDesc 节点描述
	 */
	void update(Integer nodeId, String parentNodeCode, String nodeTitle, String avatarNormal, String avatarLarge, String nodeDesc);

	/**
	 * 添加节点
	 * @param node
	 */
	void save(Node node);

	/**
	 * 删除节点
	 * @param id:节点ID
	 */
	void deleteById(Integer id);
	
	/**
	 * 统计当天的节点数量
	 * @return
	 */
	int countToday();
}
