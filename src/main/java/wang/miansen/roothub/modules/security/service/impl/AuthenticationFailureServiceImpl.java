package wang.miansen.roothub.modules.security.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.modules.security.service.AuthenticationFailureService;

/**
 * 处理认证失败的实现类
 * 
 * @author miansen.wang
 * @date 2020-03-16
 */
@Service
public class AuthenticationFailureServiceImpl implements AuthenticationFailureService {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationFailureServiceImpl.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.error("登录失败，原因是：" + exception);
		request.setAttribute("error", "登录名或者密码错误");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	@Override
	public void saveAuthenticationFailure(AuthenticationException exception) {
		logger.error("登录失败，原因是：" + exception);
	}

}
