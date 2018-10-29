/*package cn.roothub.base;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class UTF8StringBeanPostProcessor implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof StringHttpMessageConverter) {
			MediaType mediaType = new MediaType("text","plain",Charset.forName("UTF-8"));
			List<MediaType> list = new ArrayList<>();
			list.add(mediaType);
			((StringHttpMessageConverter) bean).setSupportedMediaTypes(list);
		}
		return bean;
	}

}
*/