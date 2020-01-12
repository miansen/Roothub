package wang.miansen.roothub.modules.collect.service;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.modules.collect.model.Collect;
import wang.miansen.roothub.modules.topic.model.Topic;

/**
 * @author sen
 * 2018年7月3日
 * 上午10:14:57
 * TODO
 */
public interface CollectService {

	/**
	 * 分页查询收藏话题
	 * @param pageNumber
	 * @param pageSize
	 * @param tab
	 * @return
	 */
	Page<Topic> page(Integer pageNumber, Integer pageSize, Integer uid);
	
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
    int delete(Integer uid,Integer tid);
    
    /**
     * 根据用户 ID 删除收藏
     * @param uid
     * @return
     */
    void deleteByUid(Integer uid);
    
    /**
     * 统计用户收藏话题的数量
     * @param uid
     * @return
     */
    int count(Integer uid);
    
    /**
     * 判断用户是否已收藏此话题 0:否 1:是
     */
    int isCollect(Integer uid,Integer tid);
    
    /**
     * 统计话题被收藏的数量
     * @param uid
     * @return
     */
    int countByTid(Integer tid);
}
