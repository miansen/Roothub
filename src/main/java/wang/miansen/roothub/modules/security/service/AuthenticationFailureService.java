package wang.miansen.roothub.modules.security.service;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * 处理认证失败的 Service
 * 
 * @author miansen.wang
 * @date 2020-03-16
 */
public interface AuthenticationFailureService extends AuthenticationFailureHandler {

	/**
	 * 保存认证失败的记录
	 * 
	 * @param exception 认证失败的异常
	 */
	void saveAuthenticationFailure(AuthenticationException exception);
}
