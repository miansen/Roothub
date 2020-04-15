package cn.roothub.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author: miansen.wang
 * @date: 2019-04-01
 */
public class ApplicationContextUtil implements ApplicationContextAware {

	private static Logger log = LoggerFactory.getLogger(ApplicationContextUtil.class);
	
	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

	public static ApplicationContext getContext() {
		return context;
	}

	public static <T> T getBean(Class<T> clazz) {
		try {
			return context.getBean(clazz);
		} catch (BeansException e) {
			log.debug("Spring getBaen: " + clazz,e);
		}
		return null;
	}
}
