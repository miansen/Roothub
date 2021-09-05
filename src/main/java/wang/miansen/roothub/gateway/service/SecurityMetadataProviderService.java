package wang.miansen.roothub.gateway.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;

import wang.miansen.roothub.security.bo.SecPermissionBO;
import wang.miansen.roothub.security.bo.SecRoleBO;
import wang.miansen.roothub.user.bo.UserBO;

/**
 * 权限数据提供者接口
 *
 * @author miansen.wang
 * @date 2020-12-27 14:59
 */
public interface SecurityMetadataProviderService {

    /**
     * 获取资源和权限的映射。
     *
     * <p>K: 资源（可以是 ANT 风格的通配符）</p>
     * <p>V: 权限列表</p>
     *
     * @return 资源和权限映射 Map
     */
    Map<String, List<ConfigAttribute>> mapSecurityMetadata();

    /**
     * 获取匿名用户的角色列表
     *
     * @return 匿名用户的角色列表
     */
    List<SecRoleBO> listAnonymousRoles();

    /**
     * 获取匿名用户的权限列表
     *
     * @return 匿名用户的权限列表
     */
    List<SecPermissionBO> listAnonymousPermissions();

    /**
     * 获取匿名用户信息
     *
     * @return 匿名用户信息
     */
    UserBO getAnonymousUser();
}
