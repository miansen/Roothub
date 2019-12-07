package wang.miansen.roothub.modules.node.dao;

import java.util.List;

import wang.miansen.roothub.modules.node.model.Node;
import org.apache.ibatis.annotations.Param;

/**
 * @author miansen.wang
 * @date 2018年11月3日 上午11:41:39
 */
public interface NodeDao {

	/**
	 * 查询全部节点
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Node> selectAll(@Param("start") Integer start, @Param("limit") Integer limit);
	
	/**
	 * 根据板块查询节点
	 * @param tabName
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Node> selectAllByTab(@Param("tabName") String tabName,@Param("start") Integer start, @Param("limit") Integer limit);
	
	/**
	 * 根据节点名称查询节点
	 * @param title
	 * @return
	 */
	Node selectByTitle(@Param("title") String title);
	
	/**
	 * 根据名称查询节点
	 * @param nodeTitle:节点名称
	 * @return
	 */
	Node selectByNodeTitle(@Param("nodeTitle") String nodeTitle);
	
	/**
	 * 根据ID查询节点
	 * @param id:查询节点
	 * @return
	 */
	Node selectById(@Param("id") Integer id);
	
	/**
	 * 查找子节点
	 * @param nodeTitle:节点名称
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Node> selectChildrenNode(@Param("nodeTitle") String nodeTitle,@Param("start") Integer start, @Param("limit") Integer limit);
		
	/**
	 * 查找相邻节点
	 * @param nodeCode
	 * @param parentNodeCode
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Node> selectAtherNode(@Param("nodeCode") String nodeCode,@Param("parentNodeCode") String parentNodeCode,
									 @Param("start") Integer start, @Param("limit") Integer limit);
	
	/**
	 * 查找相邻顶级节点
	 * @param nodeCode
	 * @param tabId
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Node> selectAtherParentNode(@Param("nodeCode") String nodeCode,@Param("tabId") Integer tabId,
			 						 @Param("start") Integer start, @Param("limit") Integer limit);
	
	/**
	 * 后台节点列表
	 * @param nodeTitle:节点名称
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Node> listForAdmin(@Param("nodeTitle") String nodeTitle, @Param("start") Integer start, @Param("limit") Integer limit);
	
	/**
	 * 统计节点列表
	 * @param nodeTitle:节点名称
	 * @return
	 */
	int count(@Param("nodeTitle") String nodeTitle);
	
	/**
	 * 更新节点
	 * @param node:节点对象
	 * @return
	 */
	int update(Node node);

	/**
	 * 添加节点
	 * @param node
	 * @return
	 */
	int insert(@Param("node") Node node);

	/**
	 * 删除节点
	 * @param id:节点ID
	 * @return
	 */
	int deleteById(Integer id);
	
	/**
	 * 统计当天节点数
	 * @return
	 */
	int countToday();
}
