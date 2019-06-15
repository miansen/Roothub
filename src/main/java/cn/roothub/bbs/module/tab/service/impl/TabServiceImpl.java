package cn.roothub.bbs.module.tab.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import cn.roothub.bbs.module.tab.dao.TabDao;
import cn.roothub.bbs.module.tab.model.Tab;
import cn.roothub.bbs.module.topic.service.TabService;

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
