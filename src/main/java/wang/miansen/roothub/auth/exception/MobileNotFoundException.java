package wang.miansen.roothub.auth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 如果 {@link wang.miansen.roothub.auth.service.AuthenticationUserDetailsService} 无法通过手机号码查找到用户，则抛出此异常。
 *
 * @author miansen.wang
 * @date 2021-05-09 10:14
 */
public class MobileNotFoundException extends AuthenticationException {

    /**
     * 构造一个具有详细信息的 {@code MobileNotFoundException}
     *
     * @param msg 详细信息
     */
    public MobileNotFoundException(String msg) {
        super(msg);
    }

    /**
     * 构造一个具有详细信息和根本原因的 {@code MobileNotFoundException}
     *
     * @param msg 详细信息
     * @param t 根本原因
     */
    public MobileNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
