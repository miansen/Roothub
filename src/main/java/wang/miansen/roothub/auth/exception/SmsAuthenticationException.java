package wang.miansen.roothub.auth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 如果短信认证失败，则抛出此异常。
 *
 * @author 龙德
 * @date 2021-05-23 15:14
 */
public class SmsAuthenticationException extends AuthenticationException {

    private static final long serialVersionUID = 6753374510046534504L;

    /**
     * 构造一个具有详细信息的 {@code SmsAuthenticationException}
     *
     * @param msg 详细信息
     */
    public SmsAuthenticationException(String msg) {
        super(msg);
    }

    /**
     * 构造一个具有详细信息和根本原因的 {@code SmsAuthenticationException}
     *
     * @param msg 详细信息
     * @param t 根本原因
     */
    public SmsAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }
}
