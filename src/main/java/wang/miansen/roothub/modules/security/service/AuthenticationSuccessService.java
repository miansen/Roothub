package wang.miansen.roothub.modules.security.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 处理认证成功的接口
 *
 * @author miansen.wang
 * @date 2020-12-20 10:38
 * @since 3.0
 */
public interface AuthenticationSuccessService extends AuthenticationSuccessHandler {

    /**
     * 保存认证成功的信息
     *
     * @param request        认证成功的 HTTP 请求
     * @param response       认证成功的 HTTP 响应
     * @param authentication 认证的令牌
     */
    void saveAuthenticationSuccess(HttpServletRequest request,
                                   HttpServletResponse response, Authentication authentication);
}
