package wang.miansen.roothub.auth.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import wang.miansen.roothub.auth.entity.SmsAuthenticationToken;

/**
 * 此拦截器用于处理短信身份验证。
 * <p>
 *     登录表单必须向此拦截器提供两个参数：手机号码和验证码。
 *     参数名包含在静态字段 {@link #ROOTHUB_FORM_MOBILE_KEY} 和 {@link #ROOTHUB_FORM_CODE_KEY} 中。
 *     还可以通过设置 {@code mobileParameter} 和 {@code codeParameter} 属性来更改参数名。
 * </p>
 * 默认情况下，此拦截器响应 URL {@code /sms/login}。
 *
 * @author miansen.wang
 * @date 2021-05-08 16:54
 */
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String ROOTHUB_FORM_MOBILE_KEY = "mobile";
    public static final String ROOTHUB_FORM_CODE_KEY = "code";

    private String mobileParameter = ROOTHUB_FORM_MOBILE_KEY;
    private String codeParameter = ROOTHUB_FORM_CODE_KEY;
    private boolean postOnly = true;

    /**
     * 构造函数
     */
    public SmsAuthenticationFilter() {
        super(new AntPathRequestMatcher("/sms/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                "Authentication method not supported: " + request.getMethod());
        }

        String mobile = obtainMobile(request);
        String code = obtainCode(request);

        if (mobile == null) {
            mobile = "";
        }

        if (code == null) {
            code = "";
        }

        mobile = mobile.trim();
        code = code.trim();

        SmsAuthenticationToken authRequest = new SmsAuthenticationToken(mobile, code);

        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected void setDetails(HttpServletRequest request, SmsAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }

    protected String obtainCode(HttpServletRequest request) {
        return request.getParameter(codeParameter);
    }

    public void setMobileParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "mobile parameter must not be empty or null");
        this.mobileParameter = mobileParameter;
    }

    public void setCodeParameter(String codeParameter) {
        Assert.hasText(codeParameter, "code parameter must not be empty or null");
        this.codeParameter = codeParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public String getMobileParameter() {
        return mobileParameter;
    }

    public String getCodeParameter() {
        return codeParameter;
    }
}
