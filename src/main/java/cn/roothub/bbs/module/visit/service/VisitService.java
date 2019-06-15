package cn.roothub.bbs.module.visit.service;

import cn.roothub.bbs.common.dto.DMLExecution;
import cn.roothub.bbs.core.base.PageDataBody;
import cn.roothub.bbs.module.user.model.User;
import cn.roothub.bbs.module.visit.model.Visit;

/**
 * 
 * @author sen
 * 2018年8月4日
 * 下午3:11:54
 * TODO
 */
public interface VisitService {

	/**
	 * 分页查询访问记录
	 * @param vid 被访问者ID
	 * @param pageNumber 当前页
	 * @param pageSize 每页显示的数据量
	 * @return
	 */
	PageDataBody<User> page(Integer vid,Integer pageNumber,Integer pageSize);
	
	/**
	 * 添加访问记录
	 * @param visit
	 * @return
	 */
	DMLExecution save(Visit visit);
	
	/**
	 * 被访问的次数
	 * @param vid
	 * @return
	 */
	int count(Integer vid);
	
}
