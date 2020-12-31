package wang.miansen.roothub.gateway.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * 自定义身份验证入口点接口。
 *
 * <p>拒绝访问（用户没有权限并且是匿名状态），将使用此接口。</p>
 *
 * <p>更详细点说：</p>
 *
 * <p>异常转换筛选器 {@link org.springframework.security.web.access.ExceptionTranslationFilter}
 * 在处理访问拒绝异常 {@link org.springframework.security.access.AccessDeniedException} 时使用此接口。
 * 如果引发了一个 {@link org.springframework.security.access.AccessDeniedException}，并且身份验证是匿名类型的，
 * 则过滤器将改为启动 {@link AuthenticationEntryPoint}，以便主体可以正确地进行身份验证，
 * 而不是引发 403 (禁止的)响应。这是一个必要的区别，否则主体将总是被认为是 “已认证的”，
 * 并且永远没有机会通过表单、基本、摘要或一些其他正常的认证机制登录。</p>
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
