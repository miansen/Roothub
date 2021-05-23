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
import wang.miansen.roothub.common.util.StringUtils;

/**
 * 此拦截器用于处理短信身份验证。
 * <p>
 *     登录表单必须向此拦截器提供两个参数：手机号码和验证码。
 *     参数名包含在静态字段 {@link #ROOTHUB_FORM_MOBILE_KEY} 和 {@link #ROOTHUB_FORM_CODE_KEY} 中。
 *     还可以通过设置 {@code mobileParameter} 和 {@code codeParameter} 属性来更改参数名。
 * </p>
 * 默认情况下，此拦截器响应 URL {@code /sms/login}。
 * <p>
 *     这个拦截器需放在 {@link org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter} 后面
 * </p>
 *
 * @author miansen.wang
 * @date 2021-05-08 16:54
 */
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * 前端 FORM 表单的 key
     */
    public static final String ROOTHUB_FORM_MOBILE_KEY = "mobile";
    public static final String ROOTHUB_FORM_CODE_KEY = "code";

    /**
     * 此拦截器只响应请求地址为 {@code /sms/login} 的请求
     */
    public static final String URL = "/sms/login";

    /**
     * 请求方法必须为 POST
     */
    private static final String HTTP_METHOD = "POST";

    private String mobileParameter = ROOTHUB_FORM_MOBILE_KEY;
    private String codeParameter = ROOTHUB_FORM_CODE_KEY;

    /**
     * 只支持 POST 请求
     */
    private boolean postOnly = true;

    /**
     * 构造函数
     */
    public SmsAuthenticationFilter() {
        super(new AntPathRequestMatcher(URL, HTTP_METHOD));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {
        // 只支持 POST 请求
        if (postOnly && !HTTP_METHOD.equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String mobile = obtainMobile(request);
        String code = obtainCode(request);

        // 必要的参数校验
        if (!StringUtils.checkPhoneNumber(mobile)) {
            throw new IllegalArgumentException("The mobile phone number is empty or illegal");
        }

        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("The Verification Code is empty or null");
        }

        mobile = mobile.trim();
        code = code.trim();

        // 构建一个没有认证的 token
        SmsAuthenticationToken authRequest = new SmsAuthenticationToken(mobile, code);

        setDetails(request, authRequest);

        // 交给认证管理器认证
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
