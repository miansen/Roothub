package wang.miansen.roothub.auth.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import wang.miansen.roothub.auth.entity.AuthenticationSmsToken;
import wang.miansen.roothub.auth.exception.AuthenticationMobileNotFoundException;
import wang.miansen.roothub.auth.exception.AuthenticationSmsException;
import wang.miansen.roothub.auth.service.AuthenticationUserDetailsService;
import wang.miansen.roothub.auth.service.AuthenticationSmsProviderService;
import wang.miansen.roothub.common.captcha.enums.CaptchaCodeTypeEnum;
import wang.miansen.roothub.common.captcha.enums.CaptchaRelTypeEnum;
import wang.miansen.roothub.common.captcha.service.CaptchaService;
import wang.miansen.roothub.user.service.UserService;

/**
 * 短信身份认证 Provider Service Impl
 *
 * @author miansen.wang
 * @date 2021-05-09 10:05
 */
public class AuthenticationSmsProviderServiceImpl implements AuthenticationSmsProviderService {

    /**
     * authenticationUserDetailsService
     */
    private AuthenticationUserDetailsService authenticationUserDetailsService;

    /**
     * captchaService
     */
    private CaptchaService captchaService;

    /**
     * userService
     */
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // authRequest 代表本次认证请求的主体
        AuthenticationSmsToken authRequest = (AuthenticationSmsToken) authentication;
        String mobile = (String) authRequest.getPrincipal();
        String code = (String) authRequest.getCredentials();

        // 在这里处理短信认证的逻辑

        // 先校验验证码是否合法的
        boolean verifyCode = captchaService.verifyCode(mobile, CaptchaRelTypeEnum.MOBILE, code, CaptchaCodeTypeEnum.SIGN_UP);
        if (!verifyCode) {
            throw new AuthenticationSmsException("验证码错误或已过期");
        }

        UserDetails userDetails;
        try {
            userDetails = authenticationUserDetailsService.loadUserByMobile(mobile);
        } catch (AuthenticationMobileNotFoundException e) {
            // 如果根据手机号码找不到用户，说明是第一次登录，所以先注册账号
            userService.registerByMobile(mobile);
            // 注册好之后再查一遍
            userDetails = authenticationUserDetailsService.loadUserByMobile(mobile);
        }

        // 构建一个通过认证的 token
        AuthenticationSmsToken smsAuthenticationToken = new AuthenticationSmsToken(userDetails, null, userDetails.getAuthorities());
        smsAuthenticationToken.setDetails(authRequest.getDetails());
        return smsAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 SmsAuthenticationToken 的子类或子接口
        return AuthenticationSmsToken.class.isAssignableFrom(authentication);
    }

    public AuthenticationUserDetailsService getAuthenticationUserDetailsService() {
        return authenticationUserDetailsService;
    }

    public void setAuthenticationUserDetailsService(
        AuthenticationUserDetailsService authenticationUserDetailsService) {
        this.authenticationUserDetailsService = authenticationUserDetailsService;
    }

    public CaptchaService getCaptchaService() {
        return captchaService;
    }

    public void setCaptchaService(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
