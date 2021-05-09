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
import wang.miansen.roothub.common.exception.BaseException;
import wang.miansen.roothub.gateway.service.SecurityMetadataProviderService;
import wang.miansen.roothub.rbac.bo.PermissionBO;
import wang.miansen.roothub.rbac.bo.RoleBO;
import wang.miansen.roothub.user.bo.UserBO;

/**
 * {@link AnonymousAuthenticationFilter} bean 初始化后，在这里连接数据库，初始化匿名用户的主体信息和角色权限信息。
 * <p>这么做的意义是；把匿名用户也看做一个用户，这个用户也是有用户名、密码、头像等一些基本的用户信息，也有角色和权限。
 * 以便后续在 {@link DynamicAccessDecisionManager} 做权限决策时，校验匿名用户的权限。</p>
 *
 * @author miansen.wang
 * @date 2021-01-02 12:02
 * @since 3.0
 */
public class AnonymousAuthenticationFilterProcessor implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(AnonymousAuthenticationFilterProcessor.class);

    @Autowired
    private SecurityMetadataProviderService securityMetadataProviderService;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AnonymousAuthenticationFilter) {
            Field authoritiesField = ReflectionUtils.findField(bean.getClass(), "authorities");
            Field principalField = ReflectionUtils.findField(bean.getClass(), "principal");
            authoritiesField.setAccessible(true);
            principalField.setAccessible(true);
            try {
                List<GrantedAuthority> authorities = (List<GrantedAuthority>) authoritiesField.get(bean);
                // 角色
                List<RoleBO> roles = securityMetadataProviderService.listAnonymousRoles();
                roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleCode())));
                // 权限
                List<PermissionBO> permissions = securityMetadataProviderService.listAnonymousPermissions();
                permissions.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getPermissionCode())));
                logger.info("初始化匿名用户的 authorities 成功。{}", authorities);

                UserBO user = securityMetadataProviderService.getAnonymousUser();
                AuthenticationUser authenticationUser = new AuthenticationUser(user, authorities);
                principalField.set(bean, authenticationUser);
                logger.info("初始化匿名用户的 principal 成功。{}", authenticationUser);
            } catch (Exception e) {
                logger.error("初始化匿名用户的权限与主体信息异常。", e);
                throw new BaseException("初始化匿名用户的权限与主体信息异常。", e);
            }
        }
        return bean;
    }
}
