package cn.roothub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
		
		registry.addResourceHandler("/static/img/**")
		.addResourceLocations("file:F:/upload/roothub/topic/")
		.addResourceLocations("file:F:/upload/roothub/node/")
		.addResourceLocations("file:F:/upload/roothub/user/")
		.addResourceLocations("file:F:/upload/roothub/tag/")
		.addResourceLocations("classpath:/upload/")
		.addResourceLocations("/resources/upload/");
		
		registry.addResourceHandler("/static/img2/**").addResourceLocations("file:F:/upload/roothub/topic/");
		//registry.addResourceHandler("/static/img/**").addResourceLocations("file:F:/upload/roothub/node/");
		//registry.addResourceHandler("/static/img/**").addResourceLocations("file:F:/upload/roothub/user/");
		//registry.addResourceHandler("/static/img/**").addResourceLocations("file:F:/upload/roothub/tag/");
	}
	
	
}
