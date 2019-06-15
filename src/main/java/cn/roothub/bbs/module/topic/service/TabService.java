package cn.roothub.bbs.module.topic.service;

import java.util.List;

import cn.roothub.bbs.module.tab.model.Tab;

public interface TabService {

	/**
	 * 查询所有板块
	 * @return
	 */
	List<Tab> selectAll();
}
