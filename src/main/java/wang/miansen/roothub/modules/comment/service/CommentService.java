package wang.miansen.roothub.modules.comment.service;


import java.util.Map;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.modules.comment.dto.CommentDTO;
import wang.miansen.roothub.modules.comment.model.Comment;

/**
 * 评论 Service
 * @author miansen.wang
 * @date 2018-05-25 20:25:39
 * @since 1.0
 * @version 3.0
 */
public interface CommentService extends BaseService<Comment, CommentDTO> {
	
	/**
	 * 统计当天评论数
	 * @return
	 */
	int countToday();
	
	/**
	 * 后台评论分页列表
	 * @param author: 评论作者
	 * @param topic: 话题
	 * @param startDate: 开始时间
	 * @param endDate: 结束时间
	 * @param pageNumber: 页数
	 * @param pageSize: 返回数据量
	 * @return
	 */
	Page<Map<String,Object>> pageForAdmin(String author,String topic,String startDate,String endDate,Integer pageNumber, Integer pageSize);
    
	/**
	 * 统计后台评论
	 * @param author: 评论作者
	 * @param topic: 话题
	 * @param startDate: 开始时间
	 * @param endDate: 结束时间
	 * @return
	 */
    int countAllForAdmin(String author,String topic,String startDate,String endDate);
}