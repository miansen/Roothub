package wang.miansen.roothub.auth.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import wang.miansen.roothub.auth.entity.SmsAuthenticationToken;
import wang.miansen.roothub.auth.exception.MobileNotFoundException;
import wang.miansen.roothub.auth.service.AuthenticationUserDetailsService;
import wang.miansen.roothub.auth.service.SmsAuthenticationProviderService;

/**
 * 短信身份认证 Provider Service Impl
 *
 * @author miansen.wang
 * @date 2021-05-09 10:05
 */
public class SmsAuthenticationProviderServiceImpl implements SmsAuthenticationProviderService {

    private AuthenticationUserDetailsService authenticationUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken authenticationToken = (SmsAuthenticationToken) authentication;

        String mobile = (String) authenticationToken.getPrincipal();
        String code = (String) authenticationToken.getCredentials();
        UserDetails userDetails = authenticationUserDetailsService.loadUserByMobile(mobile);

        if (userDetails == null) {
            throw new MobileNotFoundException("无法根据手机号码获取用户信息");
        }

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
}
