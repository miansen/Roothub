package wang.miansen.roothub.auth.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * 处理认证失败的接口
 *
 * @author miansen.wang
 * @date 2020-03-16
 * @since 3.0
 */
public interface AuthenticationFailureService extends AuthenticationFailureHandler {

    /**
     * 保存认证失败的信息
     *
     * @param request   认证失败的 HTTP 请求
     * @param response  认证失败的 HTTP 响应
     * @param exception 认证失败的异常
     */
    void saveAuthenticationFailure(HttpServletRequest request,
								   HttpServletResponse response, AuthenticationException exception);
}
