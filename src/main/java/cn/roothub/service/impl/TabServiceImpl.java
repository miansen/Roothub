package cn.roothub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import cn.roothub.dao.TabDao;
import cn.roothub.entity.Tab;
import cn.roothub.service.TabService;
import cn.roothub.util.JsonUtil;

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
