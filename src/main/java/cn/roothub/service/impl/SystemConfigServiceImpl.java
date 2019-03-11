package cn.roothub.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.roothub.config.service.RedisService;
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
	private RedisService redisService;
	
	@Override
	public Map<String, Object> getAllMap() {
		// 先从redis里面取
		String str = redisService.getString(Constants.REDIS_SYSTEM_CONFIG_KEY);
		Map<String,Object> map = JsonUtil.jsonToObject(str, Map.class);
		if(map != null) {
			log.debug("从redis里面取出了系统设置的信息");
			return map;
		}else {
			Map<String,Object> map2 = new LinkedHashMap<>();
			
			/*这种方法循环查询了数据库，性能开销大！*/
			/*systemConfigDao.selectByPid(0).forEach(systemConfig -> {
				map2.put(systemConfig.getDescription(), systemConfigDao.selectByPid(systemConfig.getSystemConfigId()));
			});*/
			
			// 获取所有的系统配置数据
			List<SystemConfig> systemConfigs = getAll();
			
			// 获取父节点
			List<SystemConfig> systemConfigP = systemConfigs.stream()
						 .filter(systemConfig -> systemConfig.getPid() == 0)
						 .collect(Collectors.toList());
			
			// 遍历父节点
			systemConfigP.forEach(p -> {
				// 通过父节点ID获取对应的子节点
				List<SystemConfig> systemConfigC = systemConfigs.stream()
							 .filter(systemConfig -> systemConfig.getPid().equals(p.getSystemConfigId()))
							 .collect(Collectors.toList());
				map2.put(p.getDescription(), systemConfigC);
			});
			
			// 保存数据到redis
			redisService.setString(Constants.REDIS_SYSTEM_CONFIG_KEY, JsonUtil.objectToJson(map2));
			log.debug("从数据库里面取出了系统设置的信息");
			return map2;
		}
	}

	@Override
	public List<SystemConfig> getAll() {
		return systemConfigDao.selectAll();
	}

	@Override
	public SystemConfig getByKey(String key) {
		return systemConfigDao.selectByKey(key);
	}

	@Override
	public List<SystemConfig> getBatchKeys(Collection<? extends Serializable> keys) {
		return systemConfigDao.selectBatchKeys(keys);
	}

	@Override
	public List<SystemConfig> getByPid(Integer pid) {
		return null;
	}
}
