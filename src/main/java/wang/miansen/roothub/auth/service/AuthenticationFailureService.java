package wang.miansen.roothub.auth.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * 处理认证失败的接口。
 * <p>
 *     典型的行为可能是将用户重定向到身份验证页面（在表单登录的情况下）以允许他们重试。根据异常的类型，可以实现更复杂的逻辑。
 *     例如，CredentialsExpiredException 可能会导致重定向到允许用户更改其密码的 web 控制器。
 * </p>
 * <p>
 *     实现 AuthenticationFailureService 接口即可自定义认证失败后处理逻辑。例如前后端分离，
 *     认证失败后需要返回 JSON 格式的数据，而不是跳转到模板页面，实现这个接口后就可以做出相应的处理。
 * </p>
 * <p>
 *     该接口额外扩展了 {@code saveAuthenticationFailure()} 方法，可以保存认证失败的信息。
 * </p>
 *
 * @author miansen.wang
 * @version 3.0, 2020-03-16
 * @since 3.0
 */
public interface AuthenticationFailureService extends AuthenticationFailureHandler {

    /**
     * 保存认证失败的信息
     *
     * @param request 认证失败的 HTTP 请求
     * @param response 认证失败的 HTTP 响应
     * @param exception 认证失败的异常
     */
    void saveAuthenticationFailure(HttpServletRequest request,
        HttpServletResponse response, AuthenticationException exception);
}
