package wang.miansen.roothub.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import wang.miansen.roothub.auth.exception.AuthenticationMobileNotFoundException;
import wang.miansen.roothub.auth.entity.AuthenticationUser;
import wang.miansen.roothub.auth.service.AuthenticationUserDetailsService;
import wang.miansen.roothub.common.util.StringUtils;
import wang.miansen.roothub.rbac.bo.PermissionBO;
import wang.miansen.roothub.rbac.bo.RoleBO;
import wang.miansen.roothub.rbac.service.PermissionService;
import wang.miansen.roothub.rbac.service.RoleService;
import wang.miansen.roothub.user.bo.UserBO;
import wang.miansen.roothub.user.service.UserService;

/**
 * 认证过程 Service 接口实现。这里处理真正的认证逻辑。
 *
 * @author miansen.wang
 * @date 2020-03-16
 * @since 3.0
 */
public class AuthenticationUserDetailsServiceImpl implements AuthenticationUserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new BadCredentialsException("用户名为空");
        }
        UserBO user = userService.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("根据用户名找不到用户");
        }
        return getAuthenticationUser(user);
    }

    @Override
    public UserDetails loadUserByMobile(String mobile) throws AuthenticationMobileNotFoundException {
        if (StringUtils.isEmpty(mobile)) {
            throw new BadCredentialsException("手机号码为空");
        }
        UserBO user = userService.getByMobile(mobile);
        if (user == null) {
            throw new AuthenticationMobileNotFoundException("根据手机号码找不到用户");
        }
        return getAuthenticationUser(user);
    }

    private AuthenticationUser getAuthenticationUser(UserBO user) {
        List<GrantedAuthority> authorities = getAuthorities(user);
        return new AuthenticationUser(user, authorities);
    }

    private List<GrantedAuthority> getAuthorities(UserBO user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<RoleBO> roles = roleService.listByUserId(user.getUserId());
        roles.forEach(role -> {
            // 添加角色。角色名需已 “ROLE_” 作为前缀。
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleCode()));
            List<PermissionBO> permissions = permissionService.listByRoleId(role.getRoleId());
            // 添加权限
            permissions.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getPermissionCode())));
        });
        return authorities;
    }
}
