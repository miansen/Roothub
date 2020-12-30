package wang.miansen.roothub.gateway.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;

/**
 * 权限数据提供者接口
 *
 * @author miansen.wang
 * @date 2020-12-27 14:59
 */
public interface SecurityMetadataProviderService {

    /**
     * 获取资源和权限的映射。
     * <p>K: 资源（可以是 ANT 风格的通配符）</p>
     * <p>V: 权限列表</p>
     *
     * @return 资源和权限映射 Map
     */
    Map<String, List<ConfigAttribute>> getSecurityMetadataSource();
}
