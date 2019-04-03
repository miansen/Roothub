package cn.roothub.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.roothub.dto.PageDataBody;
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
	
	//根据名称查询对应的节点
	Node findByTitle(String Title);
	
	//子节点
	List<Node> findChildrenNode(String nodeTitle,Integer pageNumber, Integer pageSize);
	
	//相邻节点
	List<Node> adjacencyNode(Node node);
	
	// 后台节点列表
	PageDataBody<Node> pageForAdmin(String nodeTitle,Integer pageNumber, Integer pageSize);
	
	// 统计后台列表
	int count();
}
