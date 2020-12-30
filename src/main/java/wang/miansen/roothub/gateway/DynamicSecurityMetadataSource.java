package wang.miansen.roothub.gateway;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import cn.hutool.core.util.URLUtil;

import wang.miansen.roothub.common.util.CollectionUtils;
import wang.miansen.roothub.gateway.service.SecurityMetadataProviderService;

/**
 * 动态权限数据源，用于获取动态权限规则。
 *
 * @author miansen.wang
 * @date 2020-12-27 14:46
 */
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Map<String, List<ConfigAttribute>> configAttributeMap;

    @Autowired
    private SecurityMetadataProviderService securityMetadataProviderService;

    /**
     * 加载权限数据源
     */
    // @PostConstruct
    public void loadSecurityMetadataSource() {
        configAttributeMap = securityMetadataProviderService.getSecurityMetadataSource();
    }

    /**
     * 清除权限数据源
     */
    public void clearSecurityMetadataSource() {
        configAttributeMap.clear();
        configAttributeMap = null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (CollectionUtils.isEmpty(configAttributeMap)) {
            loadSecurityMetadataSource();
        }
        List<ConfigAttribute> configAttributes = new ArrayList<>();
        // 获取当前访问的路径
        String url = ((FilterInvocation) object).getRequestUrl();
        String path = URLUtil.getPath(url);
        PathMatcher pathMatcher = new AntPathMatcher();
        Iterator<String> iterator = configAttributeMap.keySet().iterator();
        while (iterator.hasNext()) {
            String pattern = iterator.next();
            if (pathMatcher.match(pattern, path)) {
                configAttributes.addAll(configAttributeMap.get(pattern));
            }
        }
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
