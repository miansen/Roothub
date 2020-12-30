package wang.miansen.roothub.auth.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;

import wang.miansen.roothub.auth.service.AuthenticationEntryPointService;

/**
 * 自定义身份验证入口点接口实现类。
 *
 * @author miansen.wang
 * @date 2020-12-30 15:54
 */
public class AuthenticationEntryPointServiceImpl implements AuthenticationEntryPointService {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        request.setAttribute("error", "请先登录");
        // 采用跳转的方式跳转到登录页（默认是重定向），携带提示信息，给用户友好的提示。
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    public boolean isAjaxRequest(HttpServletRequest request) {
        return false;
    }

    @Override
    public void ajaxRequestHandler(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {

    }
}
