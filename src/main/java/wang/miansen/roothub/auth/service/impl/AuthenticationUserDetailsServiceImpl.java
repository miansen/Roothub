package wang.miansen.roothub.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.util.CollectionUtils;
import wang.miansen.roothub.common.util.StringUtils;
import wang.miansen.roothub.modules.permission.dto.PermissionDTO;
import wang.miansen.roothub.modules.role.dto.RoleDTO;
import wang.miansen.roothub.auth.entity.AuthenticationUser;
import wang.miansen.roothub.auth.service.AuthenticationUserDetailsService;
import wang.miansen.roothub.modules.user.dto.UserDTO;
import wang.miansen.roothub.modules.user.model.User;
import wang.miansen.roothub.modules.user.service.UserService;

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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (StringUtils.isEmpty(username)) {
			throw new BadCredentialsException("登录名为空");
		}
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_name", username);
		UserDTO userDTO = userService.getOne(queryWrapper);
		if (userDTO == null) {
			throw new BadCredentialsException("用户不存在");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		List<RoleDTO> roles = userDTO.getRoleDTOs();
		if (CollectionUtils.isNotEmpty(roles)) {
			// 添加角色。角色名需已“ROLE_”作为前缀。
			roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName())));
			roles.forEach(role -> {
				List<PermissionDTO> permissions = role.getPermissionDTOs();
				if (CollectionUtils.isNotEmpty(permissions)) {
					permissions.forEach(
							// 添加权限
							permission -> authorities.add(new SimpleGrantedAuthority(permission.getPermissionName())));
				}

			});
		}
		return new AuthenticationUser(userDTO, authorities);
	}

}
