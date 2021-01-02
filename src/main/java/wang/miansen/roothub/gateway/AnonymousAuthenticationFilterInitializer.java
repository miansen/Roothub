package wang.miansen.roothub.gateway;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import wang.miansen.roothub.gateway.service.SecurityMetadataProviderService;

/**
 * 匿名认证拦截器初始化器
 *
 * @author 龙德
 * @date 2021-01-02 12:02
 */
public class AnonymousAuthenticationFilterInitializer implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(AnonymousAuthenticationFilterInitializer.class);

    @Autowired
    private SecurityMetadataProviderService securityMetadataProviderService;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AnonymousAuthenticationFilter) {
//            List<String> permissions = securityMetadataProviderService.listAnonymousPermissions();
            logger.debug(bean.toString());
        }
        return bean;
    }
}
