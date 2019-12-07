package wang.miansen.roothub.modules.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wang.miansen.roothub.common.util.JsonUtil;
import wang.miansen.roothub.common.util.RedisConstants;
import wang.miansen.roothub.modules.sys.dao.SystemConfigDao;
import wang.miansen.roothub.modules.sys.model.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;

import wang.miansen.roothub.third.service.RedisService;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-04-02
 */
@Service
public class UploadConfig extends AbstractUploadConfig {

	private Logger log = LoggerFactory.getLogger(UploadConfig.class);
	
	@Autowired
	private SystemConfigDao systemConfigDao;
	
	@Autowired
	private RedisService redisService;
	
	//上传配置
	private Map<String,Object> uploadConfig;
	
	@Override
	public Map<String, Object> getUploadConfig() {
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
			SystemConfig systemConfig = systemConfigDao.selectByKey("upload_type");
			List<SystemConfig> list = systemConfigDao.selectByPid(new Integer(systemConfig.getValue()));
			uploadConfig.put(systemConfig.getKey(), systemConfig.getValue());
			list.forEach(systemConfig2 -> {
				uploadConfig.put(systemConfig2.getKey(), systemConfig2.getValue());
			});
			
			// 将数据存进redis
			redisService.setString(RedisConstants.UPLOAD_CONFIG, JsonUtil.objectToJson(uploadConfig));
		}
		return uploadConfig;
	}

	
}
