package wang.miansen.roothub.gateway;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import wang.miansen.roothub.common.util.CollectionUtils;

/**
 * 动态权限决策管理器，用于判断用户是否有访问权限。
 *
 * @author miansen.wang
 * @date 2020-12-27 14:34
 */
public class DynamicAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // 当接口未被配置资源时直接放行
        if (CollectionUtils.isEmpty(configAttributes)) {
            return;
        }
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            // 必须要有这个权限
            String needAuthority = configAttribute.getAttribute();
            // 当前用户所拥有的权限
            Collection<? extends GrantedAuthority> userAuthorities = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : userAuthorities) {
                // 判断用户有没有权限
                if (needAuthority.trim().equals(grantedAuthority.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("抱歉，您没有访问权限。");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
