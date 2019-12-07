package wang.miansen.roothub.modules.sys.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import wang.miansen.roothub.modules.sys.dao.SystemConfigDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.reflect.TypeToken;

import wang.miansen.roothub.third.service.RedisService;
import wang.miansen.roothub.modules.sys.model.SystemConfig;
import wang.miansen.roothub.modules.sys.service.SystemConfigService;
import wang.miansen.roothub.common.util.JsonUtil;
import wang.miansen.roothub.common.util.RedisConstants;

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
	
	//上传配置
	private Map<String,Object> uploadConfig;
	
	private Integer age;
	
	static {
		System.out.println("SystemConfigServiceImpl初始化。。。");
	}
	
	
	
	public SystemConfigServiceImpl() {
		System.out.println("SystemConfigServiceImpl构造器初始化。。。");
	}

	/**
	 * key是父节点的description,value是所有子节点对象
	 */
	@Override
	public Map<String, Object> getAllMap() {
		Map<String,Object> map = null;
		// 先从redis里面取
		String json = redisService.getString(RedisConstants.SYSTEM_CONFIG_ALL_MAP);
		if(json != null) {
			// 将json转为Map
			map = JsonUtil.jsonToObject(json, new TypeToken<Map<String,Object>>() {}.getType());
		}
		if(map != null) {
			log.debug("从redis里面取出了【系统设置】的信息");
			return map;
		}else {
			Map<String,Object> map2 = new LinkedHashMap<>();
			
			/*这种方法循环查询了数据库，性能开销大！*/
			/*systemConfigDao.selectByPid(0).forEach(systemConfig -> {
				map2.put(systemConfig.getDescription(), systemConfigDao.selectByPid(systemConfig.getSystemConfigId()));
			});*/
			
			// 获取所有的系统配置数据
			List<SystemConfig> systemConfigs = getAllList();
			
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
			redisService.setString(RedisConstants.SYSTEM_CONFIG_ALL_MAP, JsonUtil.objectToJson(map2));
			log.debug("从数据库里面取出了系统设置的信息");
			return map2;
		}
	}

	@Override
	public List<SystemConfig> getAllList() {
		List<SystemConfig> systemConfigs = null;
		// 先从redis里面取
		String json = redisService.getString(RedisConstants.SYSTEM_CONFIG_ALL_LIST);
		if (json != null) {
			// 将json转为List
			systemConfigs = JsonUtil.jsonToObject(json, new TypeToken<List<SystemConfig>>() {}.getType());
		}
		if (systemConfigs != null) {
			log.debug("从redis里面取出了【系统设置】的信息");
			return systemConfigs;
		} else {
			systemConfigs = systemConfigDao.selectAll();
			// 将数据保存进redis里
			redisService.setString(RedisConstants.SYSTEM_CONFIG_ALL_LIST, JsonUtil.objectToJson(systemConfigs));
			return systemConfigDao.selectAll();
		}

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
		return systemConfigDao.selectByPid(pid);
	}

	
	@Override
	public List<SystemConfig> edit(Integer pid) {
		
		return null;
	}

	@Override
	public SystemConfig getById(Integer id) {
		return systemConfigDao.selectById(id);
	}

	@Override
	public void update(SystemConfig systemConfig) {
		systemConfigDao.update(systemConfig);
	}
	
	@Override
	public void update(String key, String value) {
		SystemConfig systemConfig = new SystemConfig();
		systemConfig.setKey(key);
		systemConfig.setValue(value);
		systemConfigDao.update(systemConfig);
	}

	/**
	 * 更新配置
	 */
	@Override
	public void update(List<Map<String, String>> list) {
		for (Map<String, String> map : list) {
			SystemConfig systemConfig = new SystemConfig();
			systemConfig.setKey(map.get("name"));
			systemConfig.setValue(map.get("value"));
			systemConfigDao.update(systemConfig);
			// 如果更新的是上传配置
			if (systemConfig.getKey().equals("upload_type")) {
				updateUploadConfig(new Integer(systemConfig.getValue()));
			}
		}
	}

	/**
	 * 更新上传配置
	 */
	@Transactional
	@Override
	public void updateUploadConfig(Integer id) {
		// 获取当前配置
		SystemConfig systemConfig = getById(id);
		// 获取相同父节点的配置
		List<SystemConfig> list = getByPid(systemConfig.getPid());

		// 将配置的 value 设置为 0，不包含 key 为 upload_type 的配置
		list.stream()
			.filter(systemConfig2 -> !systemConfig2.getKey().equals("upload_type"))
			.collect(Collectors.toList()).forEach(systemConfig2 -> {
					systemConfig2.setValue("0");
					update(systemConfig2);
				});

		// 再将当前配置的 value 设置为 1
		systemConfig.setValue("1");
		update(systemConfig);

		// 清除redis里的上传配置数据
		redisService.delString(RedisConstants.UPLOAD_CONFIG);

		// 更新uploadType
		uploadConfig = null;
	}

	/**
	 * 获取上传数据
	 */
	@Override
	public Map<String,Object> getUploadConfig() {
		if(uploadConfig != null) {
			return uploadConfig;
		}
		// 先从redis里面取
		String json = redisService.getString(RedisConstants.UPLOAD_CONFIG);
		if (json != null) {
			uploadConfig = JsonUtil.jsonToObject(json, new TypeToken<Map<String,Object>>() {}.getType());
		}
		if (uploadConfig != null) {
			log.debug("从redis里面取出了【上传类型】的信息");
			return uploadConfig;
		} else {
			uploadConfig = new HashMap<>();
			SystemConfig systemConfig = getByKey("upload_type");
			List<SystemConfig> list = getByPid(new Integer(systemConfig.getValue()));
			uploadConfig.put(systemConfig.getKey(), systemConfig.getValue());
			list.forEach(systemConfig2 -> {
				uploadConfig.put(systemConfig2.getKey(), systemConfig2.getValue());
			});
			
			// 将数据存进redis
			redisService.setString(RedisConstants.UPLOAD_CONFIG, JsonUtil.objectToJson(uploadConfig));
		}
		return uploadConfig;
	}

	
	@Override
	public Integer getAge() {
		
		return this.age;
	}


	@Override
	public void setAge(Integer age) {
		this.age = age;
	}

}
