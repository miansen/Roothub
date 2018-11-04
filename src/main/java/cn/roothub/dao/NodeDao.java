package cn.roothub.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.roothub.entity.Node;

/**
 * @author miansen.wang
 * @date 2018年11月3日 上午11:41:39
 */
public interface NodeDao {

	//根据板块查询节点
	List<Node> selectAllByTab(@Param("tabName") String tabName,@Param("start") Integer start, @Param("limit") Integer limit);
	
	//根据节点编码查询节点
	Node selectByNodeCode(@Param("nodeCode") String nodeCode);
	
	//相邻节点
	List<Node> selectAtherByNodeCode(@Param("nodeCode") String nodeCode,@Param("start") Integer start, @Param("limit") Integer limit);
}
