package wang.miansen.roothub.auth.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import wang.miansen.roothub.modules.user.dto.UserDTO;

/**
 * 已经认证过的用户
 * 
 * @author miansen.wang
 * @date 2020-03-18
 */
public class AuthenticationUser extends User {

	private static final long serialVersionUID = 8186131563710796749L;

	/**
	 * 用户ID
	 */
	private String userId;

	public AuthenticationUser(UserDTO user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getUserName(), user.getPassword(), true, true, true, true, authorities);
		this.userId = user.getUserId();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
