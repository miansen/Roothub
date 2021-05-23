package wang.miansen.roothub.auth.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import wang.miansen.roothub.auth.entity.SmsAuthenticationToken;
import wang.miansen.roothub.auth.exception.MobileNotFoundException;
import wang.miansen.roothub.auth.exception.SmsAuthenticationException;
import wang.miansen.roothub.auth.service.AuthenticationUserDetailsService;
import wang.miansen.roothub.auth.service.SmsAuthenticationProviderService;
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
public class SmsAuthenticationProviderServiceImpl implements SmsAuthenticationProviderService {

    private AuthenticationUserDetailsService authenticationUserDetailsService;
    private CaptchaService captchaService;
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken authenticationToken = (SmsAuthenticationToken) authentication;

        String mobile = (String) authenticationToken.getPrincipal();
        String code = (String) authenticationToken.getCredentials();

        // 在这里处理短信认证的逻辑

        // 先校验验证码是否合法的
        boolean verifyCode = captchaService.verifyCode(mobile, CaptchaRelTypeEnum.MOBILE, code, CaptchaCodeTypeEnum.SIGN_UP);
        if (!verifyCode) {
            throw new SmsAuthenticationException("验证码错误或已过期");
        }

        UserDetails userDetails;
        try {
            userDetails = authenticationUserDetailsService.loadUserByMobile(mobile);
        } catch (MobileNotFoundException e) {
            // 如果根据手机号码找不到用户，说明是第一次登录，所以先注册账号
            userService.registerByMobile(mobile);
            // 注册好之后再查一遍
            userDetails = authenticationUserDetailsService.loadUserByMobile(mobile);
        }

        // 构建一个通过认证的 token
        SmsAuthenticationToken smsAuthenticationToken = new SmsAuthenticationToken(mobile, code, userDetails.getAuthorities());
        smsAuthenticationToken.setDetails(authenticationToken.getDetails());
        return smsAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 SmsAuthenticationToken 的子类或子接口
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
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
