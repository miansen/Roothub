package cn.roothub.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
//@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Autowired
	private SystemConfigService systemConfigService;
	
	/**
	 * 静态资源映射
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		Map<String, Object> maps = systemConfigService.getUploadConfig();
		String staticUrl = (String)maps.get("static_url");
		ResourceHandlerRegistration addResourceLocations = registry.addResourceHandler(staticUrl);
		for(Object obj : maps.values()) {
			if(!obj.equals(staticUrl)) {
				addResourceLocations.addResourceLocations((String)obj);
			}
		}
		
		// registry.addResourceHandler("/static/img2/**").addResourceLocations("file:F:/upload/roothub/topic/");
		//registry.addResourceHandler("/static/img/**").addResourceLocations("file:F:/upload/roothub/node/");
		//registry.addResourceHandler("/static/img/**").addResourceLocations("file:F:/upload/roothub/user/");
		//registry.addResourceHandler("/static/img/**").addResourceLocations("file:F:/upload/roothub/tag/");
	}
	
	
}
