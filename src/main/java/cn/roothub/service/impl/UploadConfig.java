package cn.roothub.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.roothub.config.service.RedisService;
import cn.roothub.dao.SystemConfigDao;
import cn.roothub.service.AbstractUploadConfig;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-04-02
 */
//@Service
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
		// TODO Auto-generated method stub
		return null;
	}

	
}
