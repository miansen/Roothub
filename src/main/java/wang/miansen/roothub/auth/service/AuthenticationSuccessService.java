package wang.miansen.roothub.auth.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 处理认证成功的接口。
 * <p>
 *     实现 AuthenticationSuccessService 接口即可自定义认证成功后处理逻辑。例如前后端分离，
 *     认证成功后需要返回 JSON 格式的数据，而不是跳转到模板页面，实现这个接口后就可以做出相应的处理。
 * </p>
 * <p>
 *     同时我们这里也继承了 SavedRequestAwareAuthenticationSuccessHandler 类，这个类会记住用户上一次请求的资源路径，
 *     认证成功后会跳转会用户原本受限制的页面，这也是 spring security 默认的处理方式。
 * </P>
 *
 * @author miansen.wang
 * @date 2020-12-20 10:38
 * @since 3.0
 */
public interface AuthenticationSuccessService extends AuthenticationSuccessHandler {

    /**
     * 保存认证成功的信息
     *
     * @param request 认证成功的 HTTP 请求
     * @param response 认证成功的 HTTP 响应
     * @param authentication 认证的令牌
     */
    void saveAuthenticationSuccess(HttpServletRequest request,
        HttpServletResponse response, Authentication authentication);
}
