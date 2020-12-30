package wang.miansen.roothub.gateway.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * 自定义身份验证入口点接口。
 *
 * <p>当用户认证失败或者拒绝访问（用户没有权限并且是匿名状态），将进入此接口。</p>
 *
 * <p>更详细点说：</p>
 *
 * <p>当用户认证失败并且抛出 {@link org.springframework.security.core.AuthenticationException} 异常，
 * 或者拒绝访问（用户没有权限并且是匿名状态）并且抛出 {@link org.springframework.security.access.AccessDeniedException} 异常，
 * 这个 {@link org.springframework.security.web.access.ExceptionTranslationFilter} 拦截器
 * 将处理这两个异常，实际上是交给 {@link AuthenticationEntryPoint} 处理。</p>
 *
 * <p>所以我们可以自定义此接口，根据请求头判断是否为 AJAX 请求，返回 JSON 数据或者重定向（跳转）到登录页，提示用户
 * 去登录。spring security 默认的处理方式是重定向到登录页。</p>
 *
 * <p>自定义身份验证入口点放在 gateway 模块，主要是为了处理拒绝访问（没有权限）并且用户是匿名状态的场景。</p>
 *
 * @author miansen.wang
 * @date 2020-12-30 15:51
 */
public interface AuthenticationEntryPointService extends AuthenticationEntryPoint {

    /**
     * 判断是否为 AJAX 请求
     *
     * @param request HTTP 请求
     * @return 如果是 AJAX 请求，返回 true。
     */
    boolean isAjaxRequest(HttpServletRequest request);

    /**
     * 处理 AJAX 请求
     *
     * @param request HTTP 请求
     * @param response HTTP 响应
     * @param authException 认证异常
     */
    void ajaxRequestHandler(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException);

}
