package cn.roothub.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.roothub.dto.DMLExecution;
import cn.roothub.dto.PageDataBody;
import cn.roothub.entity.User;
import cn.roothub.entity.Visit;

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
