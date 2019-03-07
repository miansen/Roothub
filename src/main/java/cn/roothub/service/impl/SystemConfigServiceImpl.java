package cn.roothub.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.roothub.dao.SystemConfigDao;
import cn.roothub.entity.SystemConfig;
import cn.roothub.service.SystemConfigService;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-07
 */
@Service
public class SystemConfigServiceImpl implements SystemConfigService {

	@Autowired
	private SystemConfigDao systemConfigDao;
	
	@Override
	public Map<String, Object> getAllMap() {
		Map<String,Object> map = new LinkedHashMap<>();
		systemConfigDao.selectByPid(0).forEach(systemConfig -> {
			map.put(systemConfig.getDescription(), systemConfigDao.selectByPid(systemConfig.getSystemConfigId()));
		});
		return map;
	}

}
