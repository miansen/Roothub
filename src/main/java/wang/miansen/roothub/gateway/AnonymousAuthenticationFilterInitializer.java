package wang.miansen.roothub.gateway;

import java.lang.reflect.Field;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.util.ReflectionUtils;

import wang.miansen.roothub.auth.entity.AuthenticationUser;
import wang.miansen.roothub.gateway.service.SecurityMetadataProviderService;
import wang.miansen.roothub.modules.user.dto.UserDTO;
import wang.miansen.roothub.modules.user.service.UserService;

/**
 * 匿名认证拦截器初始化器，初始化匿名用户的权限与主体信息。
 *
 * @author miansen.wang
 * @date 2021-01-02 12:02
 */
public class AnonymousAuthenticationFilterInitializer implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(AnonymousAuthenticationFilterInitializer.class);

    @Autowired
    private SecurityMetadataProviderService securityMetadataProviderService;

    @Autowired
    private UserService userService;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AnonymousAuthenticationFilter) {
            Field authoritiesField = ReflectionUtils.findField(bean.getClass(), "authorities");
            Field principalField = ReflectionUtils.findField(bean.getClass(), "principal");
            authoritiesField.setAccessible(Boolean.TRUE);
            principalField.setAccessible(Boolean.TRUE);
            try {
                List<GrantedAuthority> authorities = (List<GrantedAuthority>) authoritiesField.get(bean);
                List<String> permissions = securityMetadataProviderService.listAnonymousPermissions();
                permissions.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission)));
                logger.info("初始化匿名用户的 authorities 成功。{}", authorities);
                UserDTO user = userService.getById(1);
                AuthenticationUser authenticationUser = new AuthenticationUser(user, authorities);
                principalField.set(bean, authenticationUser);
                logger.info("初始化匿名用户的 principal 成功。{}", authenticationUser);
            } catch (Exception e) {
                logger.error("初始化匿名用户异常。", e);
            }
        }
        return bean;
    }
}
