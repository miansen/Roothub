package wang.miansen.roothub.gateway.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;

import wang.miansen.roothub.gateway.service.AccessDeniedService;

/**
 * 没有权限访问返回结果处理类
 *
 * @author miansen.wang
 * @date 2020-12-27 17:12
 */
public class AccessDeniedServiceImpl implements AccessDeniedService {

    private static final Logger logger = LoggerFactory.getLogger(AccessDeniedServiceImpl.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        request.setAttribute("error", "抱歉，您没有权限访问。");
        // 认证失败采用跳转的方式跳转到登录页（默认是重定向），携带提示信息，给用户友好的提示。
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    public void saveAccessDenied(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        logger.error("拒绝访问", accessDeniedException);
    }

}
