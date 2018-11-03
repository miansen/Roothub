package cn.roothub.service;

import java.util.List;

import cn.roothub.entity.Node;

/**
 * @author miansen.wang
 * @date 2018年11月3日 下午2:46:55
 */
public interface NodeService {

	//根据板块ID查询节点
	List<Node> findAll(String tabName,Integer pageNumber, Integer pageSize);
}
