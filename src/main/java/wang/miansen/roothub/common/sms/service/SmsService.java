package wang.miansen.roothub.common.sms.service;

/**
 * 短信服务接口
 *
 * @author miansen.wang
 * @since 2021-04-27 20:31
 */
public interface SmsService {

    /**
     * 发送验证码
     *
     * @param mobile 手机号码
     * @param code 验证码
     */
    void sendCode(String mobile, String code);
}
