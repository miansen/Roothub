package cn.roothub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-12
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	/**
	 * 静态资源映射
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/img/**").addResourceLocations("file:E:/xp/test/");
	}
}
