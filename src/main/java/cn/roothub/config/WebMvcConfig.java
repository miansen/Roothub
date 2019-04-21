package cn.roothub.config;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import cn.roothub.service.SystemConfigService;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-12
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Autowired
	@Qualifier("systemConfigServiceImpl")
	private SystemConfigService systemConfigService;
	
	/**
	 * 静态资源映射
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		Map<String, Object> maps = systemConfigService.getUploadConfig();
		/**
		 * 上传类型
		 * 29: 默认上传
		 * 30: 自定义上传
		 * 45: 阿里云OSS上传
		 */
		String uploadType = (String)maps.get("upload_type");
		if(!uploadType.equals("45")) {
			// 静态资源访问URL
			String staticUrl = (String)maps.get("static_url");
			String uploadFiledir = (String)maps.get("default_upload_filedir") != null ? (String)maps.get("default_upload_filedir") : (String)maps.get("local_upload_filedir");
			String[] locations = {uploadFiledir + "topic/",uploadFiledir + "node/",uploadFiledir + "user/",uploadFiledir + "tag/"};
			// 静态资源访问URL
			ResourceHandlerRegistration addResourceLocations = registry.addResourceHandler(staticUrl);
			for(String location : locations) {
				// 静态资源映射目录
				addResourceLocations.addResourceLocations(location);
			}
		}
	}
}
