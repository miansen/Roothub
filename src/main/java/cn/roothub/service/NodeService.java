package cn.roothub.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.roothub.entity.Node;

/**
 * @author miansen.wang
 * @date 2018年11月3日 下午2:46:55
 */
public interface NodeService {

	//查询全部节点
	List<Node> findAll(Integer pageNumber, Integer pageSize);
	
	//根据板块ID查询节点
	List<Node> findAllByTab(String tabName,Integer pageNumber, Integer pageSize);
	
	//根据节点编码查询对应的节点
	Node findByNodeCode(String nodeCode);
	
	//子节点
	List<Node> findChildrenNode(String nodeCode,Integer pageNumber, Integer pageSize);
	
	//相邻节点
	List<Node> adjacencyNode(Node node);
}
