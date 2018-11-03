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
	public List<Node> findAll(String tabName, Integer pageNumber, Integer pageSize) {
		return nodeDao.selectAll(tabName, pageNumber, pageSize);
	}
	
}
