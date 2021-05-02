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
     * @param captchaRelType 验证码关联的对象的类型
     * @param captchaCodeType 验证码的类型
     * @param length 要生成多少位的数字验证码
     * @return 数字验证码
     */
    String generateNumberCode(String captchaRel, CaptchaRelTypeEnum captchaRelType, CaptchaCodeTypeEnum captchaCodeType,
        int length);

    /**
     * 验证验证码
     *
     * @param captchaRel 验证码关联的对象，可以是用户ID、手机号码或者邮箱
     * @param captchaRelType 验证码关联的对象的类型
     * @param captchaCode 需要验证的验证码
     * @param captchaCodeType 验证码的类型
     * @return true: 验证成功，false: 验证失败
     */
    boolean verifyCode(String captchaRel, CaptchaRelTypeEnum captchaRelType, String captchaCode,
        CaptchaCodeTypeEnum captchaCodeType);

    /**
     * 验证腾讯的图形验证码，参考文档：https://007.qq.com/java-access.html?ADTAG=acces.start
     *
     * @param ticket 验证码客户端验证回调的票据
     * @param rand 验证码客户端验证回调的随机串
     * @param userIp 提交验证的用户的IP地址
     * @return true: 验证成功，false: 验证失败
     */
    boolean verifyTicket(String ticket, String rand, String userIp);
}
