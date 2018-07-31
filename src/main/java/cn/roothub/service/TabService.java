package cn.roothub.service;

import java.util.List;

import cn.roothub.entity.Tab;

public interface TabService {

	/**
	 * 查询所有板块
	 * @return
	 */
	List<Tab> selectAll();
}
