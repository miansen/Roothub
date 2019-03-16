package cn.roothub.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.roothub.entity.SystemConfig;
import cn.roothub.service.SystemConfigService;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-15
 */
@Component
public class UploadConfig {

	
	private SystemConfigService systemConfigService;
	
	/**
	 * 上传类型
	 * 1: 默认上传 2: 本地上传 3: 阿里云OSS
	 */
	private Integer uploadType;
	
	/**
	 * 资源位置
	 */
	private String[] resourceLocations;
	
	@Autowired
	public UploadConfig(SystemConfigService systemConfigService) {
		this.systemConfigService = systemConfigService;
		List<SystemConfig> list = this.systemConfigService.getAllList();
		this.uploadType = list.get(0).getSystemConfigId();
	}

	public Integer getUploadType() {
		return uploadType;
	}

	public void setUploadType(Integer uploadType) {
		this.uploadType = uploadType;
	}

	public String[] getResourceLocations() {
		return resourceLocations;
	}

	public void setResourceLocations(String[] resourceLocations) {
		this.resourceLocations = resourceLocations;
	}
	
}
