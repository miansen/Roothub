package wang.miansen.roothub.modules.tab.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.modules.tab.dao.TabDao;
import wang.miansen.roothub.modules.tab.model.Tab;
import wang.miansen.roothub.modules.topic.service.TabService;

@Service
public class TabServiceImpl implements TabService{

	@Autowired
	private TabDao tabDao;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 查询所有板块
	 */
	@Override
	public List<Tab> selectAll() {
		return tabDao.selectAll();
	}

}
