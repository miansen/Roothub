package wang.miansen.roothub.modules.topic.service;

import java.util.List;

import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.modules.tab.dto.TabDTO;
import wang.miansen.roothub.modules.tab.model.Tab;

public interface TabService extends BaseService<Tab, TabDTO>{

	/**
	 * 查询所有板块
	 * @return
	 */
	List<Tab> selectAll();
}
