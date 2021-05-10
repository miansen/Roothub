package wang.miansen.roothub.auth.entity;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

/**
 * 该类参考了 {@link org.springframework.security.authentication.UsernamePasswordAuthenticationToken} ，
 * 用于短信身份验证实现，简单的表示手机号码和验证码。
 * <p>主体和凭据应使用通过 Object.toString() 方法提供相应属性的对象进行设置。最简单的对象是字符串。</p>
 *
 * @author miansen.wang
 * @date 2021-05-08 16:02
 */
public class SmsAuthenticationToken extends AbstractAuthenticationToken {

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
     * 创建一个没有鉴权的 MobileAuthenticationToken
     *
     * @param principal principal
     * @param credentials credentials
     */
    public SmsAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    /**
     * 创建一个拥有鉴权的 MobileAuthenticationToken
     *
     * @param principal principal
     * @param credentials credentials
     * @param authorities authorities
     */
    public SmsAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
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
