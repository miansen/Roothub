package wang.miansen.roothub.auth.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import wang.miansen.roothub.auth.entity.AuthenticationUser;
import wang.miansen.roothub.auth.service.AuthenticationSuccessService;

/**
 * 处理认证成功的接口实现类。
 *
 * @author miansen.wang
 * @date 2020-12-20 10:48
 * @since 3.0
 */
public class AuthenticationSuccessServiceImpl extends SavedRequestAwareAuthenticationSuccessHandler
    implements AuthenticationSuccessService {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessServiceImpl.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
        saveAuthenticationSuccess(request, response, authentication);
        super.onAuthenticationSuccess(request, response, authentication);
    }

    @Override
    public void saveAuthenticationSuccess(HttpServletRequest request,
        HttpServletResponse response, Authentication authentication) {
        logger.info("用户[" + authentication.getPrincipal() + "]认证成功");
    }
}