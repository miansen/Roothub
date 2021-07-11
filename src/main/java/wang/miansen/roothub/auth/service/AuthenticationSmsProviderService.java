package wang.miansen.roothub.auth.service;

import org.springframework.security.authentication.AuthenticationProvider;

import wang.miansen.roothub.auth.entity.AuthenticationSmsToken;

/**
 * 该接口继承 {@link AuthenticationProvider} ，用于处理短信身份认证。
 * <p>
 * 该接口响应 {@link AuthenticationSmsToken} 身份认证请求。 认证成功后，
 * 将创建 {@link AuthenticationSmsToken} 并 将其返回给调用者。
 * </p>
 *
 * @author miansen.wang
 * @date 2021-05-09 10:02
 */
public interface AuthenticationSmsProviderService extends AuthenticationProvider {

}
