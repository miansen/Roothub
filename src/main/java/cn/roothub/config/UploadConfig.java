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
//@Component
public class UploadConfig {

	
	private SystemConfigService systemConfigService;
	
	/**
	 * 上传类型
	 * 29:默认上传， 30:本地上传， 45:阿里云OSS
	 */
	private String uploadType;
	
	// 默认的话题文件保存路径
	private String defaultUploadTopicFiledir;
	
	// 默认的节点文件保存路径
	private String defaultUploadNodeFiledir;
	
	// 默认的标签文件保存路径
	private String defaultUploadTagFiledir;
	
	// 默认的用户文件保存路径
	private String defaultUploadUserFiledir;
	
	// 静态文件访问URL
	private String defaultUploadStaticUrl;
	
	
	/**
	 * 资源位置
	 */
	private String[] resourceLocations;
	
	@Autowired
	public UploadConfig(SystemConfigService systemConfigService) {
		this.systemConfigService = systemConfigService;
		List<SystemConfig> list = this.systemConfigService.getAllList();
	}
	
	public String[] getResourceLocations() {
		return resourceLocations;
	}

	public void setResourceLocations(String[] resourceLocations) {
		this.resourceLocations = resourceLocations;
	}
	
}
