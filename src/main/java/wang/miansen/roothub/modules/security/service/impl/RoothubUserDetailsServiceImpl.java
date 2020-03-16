package wang.miansen.roothub.modules.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.util.CollectionUtils;
import wang.miansen.roothub.common.util.StringUtils;
import wang.miansen.roothub.modules.permission.dto.PermissionDTO;
import wang.miansen.roothub.modules.role.dto.RoleDTO;
import wang.miansen.roothub.modules.security.service.RoothubUserDetailsService;
import wang.miansen.roothub.modules.user.dto.UserDTO;
import wang.miansen.roothub.modules.user.model.User;
import wang.miansen.roothub.modules.user.service.UserService;

/**
 * @author miansen.wang
 * @date 2020-03-16
 */
@Service
public class RoothubUserDetailsServiceImpl implements RoothubUserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (StringUtils.isEmpty(username)) {
			throw new BadCredentialsException("登录名不能为空");
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
			roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName())));
			roles.forEach(role -> {
				List<PermissionDTO> permissions = role.getPermissionDTOs();
				if (CollectionUtils.isNotEmpty(permissions)) {
					permissions.forEach(
							permission -> authorities.add(new SimpleGrantedAuthority(permission.getPermissionName())));
				}

			});
		}
		return new org.springframework.security.core.userdetails.User(username, userDTO.getPassword(), true, true, true,
				true, authorities);
	}

}
