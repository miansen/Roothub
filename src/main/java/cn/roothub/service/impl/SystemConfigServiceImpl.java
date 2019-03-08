package cn.roothub.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import cn.roothub.dao.SystemConfigDao;
import cn.roothub.entity.SystemConfig;
import cn.roothub.service.SystemConfigService;
import cn.roothub.util.Constants;
import cn.roothub.util.JsonUtil;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-07
 */
@Service
public class SystemConfigServiceImpl implements SystemConfigService {

	private Logger log = LoggerFactory.getLogger(SystemConfigService.class);
	
	@Autowired
	private SystemConfigDao systemConfigDao;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public Map<String, Object> getAllMap() {
		// 先从redis里面取
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		String str = opsForValue.get(Constants.REDIS_SYSTEM_CONFIG_KEY);
		Map<String,Object> map = JsonUtil.jsonToObject(str, Map.class);
		if(map != null) {
			log.debug("从redis里面取出了系统设置的信息");
			return map;
		}else {
			Map<String,Object> map2 = new LinkedHashMap<>();
			systemConfigDao.selectByPid(0).forEach(systemConfig -> {
				map2.put(systemConfig.getDescription(), systemConfigDao.selectByPid(systemConfig.getSystemConfigId()));
			});
			opsForValue.set(Constants.REDIS_SYSTEM_CONFIG_KEY, JsonUtil.objectToJson(map2));
			log.debug("从数据可里面取出了系统设置的信息");
			return map2;
		}
	}
}
