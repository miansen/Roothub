package wang.miansen.roothub.module.topic.service;

import java.util.List;

import wang.miansen.roothub.module.tab.model.Tab;

public interface TabService {

	/**
	 * 查询所有板块
	 * @return
	 */
	List<Tab> selectAll();
}
