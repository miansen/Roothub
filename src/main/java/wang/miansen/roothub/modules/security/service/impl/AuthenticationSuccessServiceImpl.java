package wang.miansen.roothub.modules.security.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.modules.security.model.AuthenticationUser;
import wang.miansen.roothub.modules.security.service.AuthenticationSuccessService;

/**
 * 处理认证成功的接口实现类。
 * <p>实现 AuthenticationSuccessService 接口即可自定义认证成功后处理逻辑。
 * 例如前后端分离，认证成功后需要返回 JSON 格式的数据，而不是跳转到模板页面，
 * 实现这个接口后就可以做出相应的处理。
 * <p>同时我们这里也继承了 SavedRequestAwareAuthenticationSuccessHandler 类，
 * 这个类会记住用户上一次请求的资源路径，认证成功后会跳转会用户原本受限制的页面，这
 * 也是 spring security 默认的处理方式。
 *
 * @author miansen.wang
 * @date 2020-12-20 10:48
 * @since 3.0
 */
@Service
public class AuthenticationSuccessServiceImpl extends SavedRequestAwareAuthenticationSuccessHandler
        implements AuthenticationSuccessService {

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
        AuthenticationUser user = (AuthenticationUser) authentication.getPrincipal();
        logger.info("用户[" + user.getUsername() + "]认证成功");
    }
}