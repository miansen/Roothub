package wang.miansen.roothub.auth.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import wang.miansen.roothub.user.bo.UserBO;

/**
 * 此类继承 {@link User}，扩展 {@code userId} 字段， 对 {@link UserDetailsService} 检索到的核心用户信息进行建模。
 *
 * @author miansen.wang
 * @date 2020-03-18
 */
public class AuthenticationUser extends User {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8186131563710796749L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 构造函数
     *
     * @param user user BO 对象
     * @param authorities 认证信息
     */
    public AuthenticationUser(UserBO user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
        this.userId = user.getUserId();
    }

    /**
     * 获取 user id
     *
     * @return user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置 user id
     *
     * @param userId user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
