package cn.roothub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author sen
 * 2018年8月29日
 * 下午3:17:20
 * TODO
 */
@Component
public class SiteConfig {

	@Autowired
	private CookieConfig cookieConfig;

	public CookieConfig getCookieConfig() {
		return cookieConfig;
	}

	public void setCookieConfig(CookieConfig cookieConfig) {
		this.cookieConfig = cookieConfig;
	}
	
}
