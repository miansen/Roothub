package wang.miansen.roothub.auth.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import wang.miansen.roothub.user.bo.UserBO;

/**
 * 此类继承 {@link User}，扩展 {@code userId} 字段， 对 {@link org.springframework.security.core.userdetails.UserDetailsService}
 * 检索到的核心用户信息进行建模。
 *
 * @author miansen.wang
 * @date 2020-03-18
 */
public class AuthenticationUser extends User {

    private static final long serialVersionUID = 8186131563710796749L;

    /**
     * 用户ID
     */
    private Long userId;

    public AuthenticationUser(UserBO user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
        this.userId = user.getUserId();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
