package cn.roothub.dao;

import java.util.List;

import cn.roothub.entity.Tab;

/**
 * 父板块数据接口
 * @author sen
 * 2018年7月15日
 * 下午8:57:48
 * TODO
 */
public interface TabDao {

	/**
	 * 查询所有板块
	 * @return
	 */
	List<Tab> selectAll();
}
