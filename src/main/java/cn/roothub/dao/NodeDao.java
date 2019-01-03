package cn.roothub.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.roothub.entity.Node;

/**
 * @author miansen.wang
 * @date 2018年11月3日 上午11:41:39
 */
public interface NodeDao {

	//查询全部节点
	List<Node> selectAll(@Param("start") Integer start, @Param("limit") Integer limit);
	
	//根据板块查询节点
	List<Node> selectAllByTab(@Param("tabName") String tabName,@Param("start") Integer start, @Param("limit") Integer limit);
	
	//根据节点编码查询节点
	Node selectByTitle(@Param("title") String title);
	
	//根据名称查询节点
	Node selectByNodeTitle(@Param("nodeTitle") String nodeTitle);
	
	//查找子节点
	List<Node> selectChildrenNode(@Param("nodeCode") String nodeCode,@Param("start") Integer start, @Param("limit") Integer limit);
		
	//查找相邻节点
	List<Node> selectAtherNode(@Param("nodeCode") String nodeCode,@Param("parentNodeCode") String parentNodeCode,
									 @Param("start") Integer start, @Param("limit") Integer limit);
	
	//查找相邻顶级节点
	List<Node> selectAtherParentNode(@Param("nodeCode") String nodeCode,@Param("tabId") Integer tabId,
			 						 @Param("start") Integer start, @Param("limit") Integer limit);
}
