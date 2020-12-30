package wang.miansen.roothub.gateway.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 自定义拒绝访问（用户没有权限并且是登录状态）处理接口
 *
 * @author miansen.wang
 * @date 2020-12-27 17:09
 */
public interface AccessDeniedHandlerService extends AccessDeniedHandler {

    /**
     * 保存拒绝访问的信息
     *
     * @param request HTTP 请求
     * @param response HTTP 响应
     * @param accessDeniedException 拒绝访问异常
     */
    void saveAccessDenied(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException);
}
