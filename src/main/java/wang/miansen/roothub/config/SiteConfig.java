package wang.miansen.roothub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	@Value("${intro}")
	private String intro;
	
	public CookieConfig getCookieConfig() {
		return cookieConfig;
	}

	public void setCookieConfig(CookieConfig cookieConfig) {
		this.cookieConfig = cookieConfig;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
}
