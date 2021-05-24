package wang.miansen.roothub.common.captcha.service;

import wang.miansen.roothub.common.captcha.enums.CaptchaCodeTypeEnum;
import wang.miansen.roothub.common.captcha.enums.CaptchaRelTypeEnum;

/**
 * 验证码 Service 接口
 *
 * @author miansen.wang
 * @date 2021-05-02 14:58
 */
public interface CaptchaService {

    /**
     * 生成数字验证码
     *
     * @param captchaRel 验证码关联的对象，可以是用户ID、手机号码或者邮箱
     * @param captchaRelType 验证码关联的对象的类型，{@link CaptchaRelTypeEnum}
     * @param captchaCodeType 验证码的类型，{@link CaptchaCodeTypeEnum}
     * @param length 要生成多少位的数字验证码
     * @return 数字验证码
     */
    String generateNumberCode(String captchaRel, CaptchaRelTypeEnum captchaRelType, CaptchaCodeTypeEnum captchaCodeType,
        int length);

    /**
     * 此接口可以给指定的手机号码发送数字验证码。
     * <p>
     *     此接口最终是调用这个接口 {@link wang.miansen.roothub.common.sms.service.SmsService}
     *     的 {@code send()} 方法将验证码发送给指定的手机号码上的。
     * </p>
     * <p>
     *     为了防止恶意发送，造成短信资源的浪费。在此基础上增加腾讯防水墙验证码功能，
     *     只有通过了腾讯验证码的校验，才能发送短信。
     * </p>
     *
     * @param numberCode 数字验证码
     * @param mobile 手机号码
     * @param ticket 腾讯防水墙票据
     * @param randStr 腾讯防水墙随机字符串
     * @param ip 提交请求的 IP 地址
     */
    void sendNumberCode(String numberCode, String mobile, String ticket, String randStr, String ip);

    /**
     * 验证验证码
     *
     * @param captchaRel 验证码关联的对象，可以是用户ID、手机号码或者邮箱
     * @param captchaRelType 验证码关联的对象的类型，{@link CaptchaRelTypeEnum}
     * @param captchaCode 需要验证的验证码
     * @param captchaCodeType 验证码的类型，{@link CaptchaCodeTypeEnum}
     * @return true: 验证成功，false: 验证失败
     */
    boolean verifyCode(String captchaRel, CaptchaRelTypeEnum captchaRelType, String captchaCode,
        CaptchaCodeTypeEnum captchaCodeType);

    /**
     * 验证腾讯的图形验证码，参考文档：https://007.qq.com/java-access.html?ADTAG=acces.start
     *
     * @param ticket 验证码客户端验证回调的票据
     * @param rand 验证码客户端验证回调的随机串
     * @param userIp 提交验证的用户的 IP 地址
     * @return true: 验证成功，false: 验证失败
     */
    boolean verifyTicket(String ticket, String rand, String userIp);
}
