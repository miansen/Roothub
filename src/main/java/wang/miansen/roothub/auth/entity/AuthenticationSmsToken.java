package wang.miansen.roothub.auth.entity;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

/**
 * 该类参考了 {@link UsernamePasswordAuthenticationToken} ， 用于短信身份验证实现，简单的表示手机号码和验证码。
 * <p>主体和凭据应使用通过 Object.toString() 方法提供相应属性的对象进行设置。最简单的对象是字符串。</p>
 *
 * @author miansen.wang
 * @date 2021-05-08 16:02
 */
public class AuthenticationSmsToken extends AbstractAuthenticationToken {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * 手机号码
     */
    private final Object principal;

    /**
     * 验证码
     */
    private Object credentials;

    /**
     * 创建一个没有认证的 AuthenticationSmsToken。
     *
     * @param principal principal 对于短信身份认证请求，这将是手机号码。
     * @param credentials credentials 对于短信身份认证请求，这将是验证码。
     */
    public AuthenticationSmsToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    /**
     * 创建一个已经通过认证的 AuthenticationSmsToken。
     *
     * @param principal principal 由于已经通过认证，这将是 {@code UserDetails} 对象作为主体，供应用程序使用。
     * @param credentials credentials 由于已经通过认证，可以为 null。
     * @param authorities authorities 通过认证的主体的权限，可以是空集合。不能为 null。
     */
    public AuthenticationSmsToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        // 因为重写了 setAuthenticated 方法，会抛异常，所以只能调用父类的方法设置
        super.setAuthenticated(true);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
