package wang.miansen.roothub.gateway.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;

import wang.miansen.roothub.gateway.service.AccessDeniedHandlerService;

/**
 * 自定义拒绝访问处理接口实现类。（用户没有权限并且是已认证状态）
 *
 * @author miansen.wang
 * @date 2020-12-27 17:12
 */
public class AccessDeniedHandlerServiceImpl implements AccessDeniedHandlerService {

    private static final Logger logger = LoggerFactory.getLogger(AccessDeniedHandlerServiceImpl.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        saveAccessDenied(request, response, accessDeniedException);
        // 设置 403 状态码
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        // 采用跳转的方式跳转到登录页，携带提示信息，给用户友好的提示。
        request.setAttribute("errorCode", HttpServletResponse.SC_FORBIDDEN);
        request.setAttribute("errorMsg", "抱歉，您没有权限访问。");
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }

    @Override
    public void saveAccessDenied(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        logger.error("拒绝访问", accessDeniedException);
    }

}
