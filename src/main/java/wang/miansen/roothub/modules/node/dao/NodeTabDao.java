package wang.miansen.roothub.modules.node.dao;

import java.util.List;

import wang.miansen.roothub.modules.node.model.NodeTab;
import org.apache.ibatis.annotations.Param;

public interface NodeTabDao {

	/**
	 * 查询所有节点板块
	 * @return
	 */
    List<NodeTab> selectAll();

    /**
     * 根据ID查询节点板块
     * @param nodeTabId
     * @return
     */
    NodeTab selectById(@Param("nodeTabId") Integer nodeTabId);
    
    /**
     * 根据编码查询节点板块
     * @param nodeTabCode
     * @return
     */
    NodeTab selectByCode(@Param("nodeTabCode") String nodeTabCode);
    
    /**
     * 添加节点板块
     * @param nodeTab
     * @return
     */
    int insert(NodeTab nodeTab);

    /**
     * 根据ID更新节点板块
     * @param nodeTab
     * @return
     */
    int updateById(NodeTab nodeTab);

    /**
     * 根据ID删除板块
     * @param nodeTabId
     * @return
     */
    int deleteById(@Param("nodeTabId") Integer nodeTabId);

}
