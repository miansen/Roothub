package wang.miansen.roothub.modules.collect.dao;

import java.util.List;

import wang.miansen.roothub.modules.collect.model.Collect;
import org.apache.ibatis.annotations.Param;

import wang.miansen.roothub.modules.topic.model.Topic;

public interface CollectDao {

	/**
	 * 分页查询所有收藏话题
	 * @param start
	 * @param limit
	 * @return
	 */
    List<Topic> selectAllByCollect(@Param("start") Integer start, @Param("limit") Integer limit,@Param("uid")Integer uid);
    
    /**
     * 添加收藏
     * @param uid
     * @param tid
     * @return
     */
    int insert(Collect collect);
    
    /**
     * 取消收藏
     * @param uid
     * @param tid
     * @return
     */
    int delete(@Param("uid") Integer uid,@Param("tid") Integer tid);
    
    /**
     * 根据用户 ID 删除收藏
     * @return
     */
    int deleteByUid(@Param("uid") Integer uid);
    
    /**
     * 统计用户收藏话题的数量
     * @param uid
     * @return
     */
    int count(@Param("uid")Integer uid);
    
    /**
     * 统计话题被收藏的数量
     * @param uid
     * @return
     */
    int countByTid(@Param("tid")Integer tid);
    
    /**
     * 判断用户是否已收藏此话题 0:否 1:是
     */
    int isCollect(@Param("uid") Integer uid,@Param("tid") Integer tid);
}
