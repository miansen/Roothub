package wang.miansen.roothub.gateway.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import wang.miansen.roothub.common.util.CollectionUtils;
import wang.miansen.roothub.gateway.service.SecurityMetadataProviderService;
import wang.miansen.roothub.rbac.bo.PermissionBO;
import wang.miansen.roothub.rbac.bo.ResourceBO;
import wang.miansen.roothub.rbac.bo.RoleBO;
import wang.miansen.roothub.rbac.service.PermissionService;
import wang.miansen.roothub.rbac.service.ResourceService;
import wang.miansen.roothub.rbac.service.RoleService;
import wang.miansen.roothub.user.bo.UserBO;
import wang.miansen.roothub.user.service.UserService;

/**
 * 权限数据提供者实现类
 *
 * @author miansen.wang
 * @date 2020-12-27 15:06
 */
public class SecurityMetadataProviderServiceImpl implements SecurityMetadataProviderService {

    /**
     * 匿名用户默认的 ID
     */
    private static final Long ANONYMOUS_USER_ID = 1L;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public Map<String, List<ConfigAttribute>> mapSecurityMetadata() {
        Map<String, List<ConfigAttribute>> map = new ConcurrentHashMap<>(16);
        List<ResourceBO> resources = resourceService.list();
        for (ResourceBO resource : resources) {
            List<ConfigAttribute> configAttributes = new ArrayList<>();
            List<PermissionBO> permissions = permissionService.listByResourceId(resource.getResourceId());
            List<String> permissionCodes = permissions.stream().map(PermissionBO::getPermissionCode).collect(Collectors.toList());
            for (String permissionCode : permissionCodes) {
                SecurityConfig securityConfig = new SecurityConfig(permissionCode);
                configAttributes.add(securityConfig);
            }
            map.put(resource.getResourceCode(), configAttributes);
        }
        return map;
    }

    @Override
    public List<RoleBO> listAnonymousRoles() {
        return roleService.listByUserId(ANONYMOUS_USER_ID);
    }

    @Override
    public List<PermissionBO> listAnonymousPermissions() {
        List<PermissionBO> permissionsAll = new ArrayList<>();
        List<RoleBO> roles = listAnonymousRoles();
        roles.forEach(role -> {
            List<PermissionBO> permissions = permissionService.listByRoleId(role.getRoleId());
            if (CollectionUtils.isNotEmpty(permissions)) {
                permissionsAll.addAll(permissions);
            }
        });
        return permissionsAll;
    }

    @Override
    public UserBO getAnonymousUser() {
        return userService.getById(ANONYMOUS_USER_ID);
    }

}
