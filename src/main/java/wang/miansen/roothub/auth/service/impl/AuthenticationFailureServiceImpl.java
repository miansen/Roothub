package wang.miansen.roothub.auth.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.AuthenticationException;

import wang.miansen.roothub.auth.service.AuthenticationFailureService;

/**
 * 处理认证失败的实现类
 * 
 * @author miansen.wang
 * @date 2020-03-16
 * @since 3.0
 */
public class AuthenticationFailureServiceImpl implements AuthenticationFailureService {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationFailureServiceImpl.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		saveAuthenticationFailure(request, response, exception);
		request.setAttribute("error", exception.getMessage());
		// 认证失败采用跳转的方式跳转到登录页（默认是重定向），携带提示信息，给用户友好的提示。
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	@Override
	public void saveAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
		logger.error("认证失败", exception);
	}

}
