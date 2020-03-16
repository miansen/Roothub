package wang.miansen.roothub.modules.security.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.modules.security.service.AuthenticationFailureService;

/**
 * 处理认证失败的 Service Impl
 * 
 * @author miansen.wang
 * @date 2020-03-16
 */
@Service
public class AuthenticationFailureServiceImpl extends SimpleUrlAuthenticationFailureHandler
		implements AuthenticationFailureService {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationFailureServiceImpl.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.error("登录失败，原因是：" + exception);
		RedirectStrategy redirectStrategy = super.getRedirectStrategy();
		request.setAttribute("error", "登录名或者密码错误");
		// redirectStrategy.sendRedirect(request, response, "/admin/login");
        request.getRequestDispatcher("/admin/login").forward(request, response);
		// response.sendRedirect("/admin/login");
		// super.onAuthenticationFailure(request, response, exception);
	}

	@Override
	public void saveAuthenticationFailure(AuthenticationException exception) {
		logger.error("登录失败，原因是：" + exception);
	}

}
