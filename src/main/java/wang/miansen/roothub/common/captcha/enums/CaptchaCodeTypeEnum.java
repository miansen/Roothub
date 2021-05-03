package wang.miansen.roothub.common.captcha.enums;

import java.util.Objects;

/**
 * 验证码类型枚举
 *
 * @author miansen.wang
 * @date 2021-05-02 15:20
 */
public enum CaptchaCodeTypeEnum {

    /**
     * 注册登录验证码
     */
    SIGN_UP(1, "注册登录验证码"),

    /**
     * 找回密码验证码
     */
    FIND_PASSWORD(2, "找回密码验证码"),

    /**
     * 重置密码验证码
     */
    RESET_PASSWORD(3, "重置密码验证码"),

    /**
     * 发送短信验证码
     */
    SMS(4, "发送短信验证码"),

    /**
     * 邮箱验证码
     */
    MAIL(5, "邮箱验证码"),

    /**
     * 秒杀验证码
     */
    SPIKE(6, "秒杀验证码"),

    /**
     * 领券验证码
     */
    COUPON(7, "领券验证码"),

    /**
     * 抢红包验证码
     */
    RED_ENVELOPE(8, "抢红包验证码"),

    /**
     * 发帖验证码
     */
    POST(9, "发帖验证码"),

    /**
     * 评论验证码
     */
    COMMENT(10, "评论验证码"),

    /**
     * 投票验证码
     */
    VOTE(11, "投票验证码");

    /**
     * 枚举编号
     */
    private final Integer captchaCodeTypeId;

    /**
     * 枚举详情
     */
    private final String captchaCodeTypeName;

    /**
     * 构造方法
     *
     * @param captchaCodeTypeId 枚举编号
     * @param captchaCodeTypeName 枚举详情
     */
    CaptchaCodeTypeEnum(Integer captchaCodeTypeId, String captchaCodeTypeName) {
        this.captchaCodeTypeId = captchaCodeTypeId;
        this.captchaCodeTypeName = captchaCodeTypeName;
    }

    /**
     * 根据枚举编号获取枚举
     *
     * @param captchaCodeTypeId 枚举编号
     * @return 枚举
     */
    public static CaptchaCodeTypeEnum valueOf(Integer captchaCodeTypeId) {
        for (CaptchaCodeTypeEnum captchaCodeType : CaptchaCodeTypeEnum.values()) {
            if (Objects.equals(captchaCodeType.getCaptchaCodeTypeId(), captchaCodeTypeId)) {
                return captchaCodeType;
            }
        }
        return null;
    }

    /**
     * 获取枚举编号
     *
     * @return 枚举编号
     */
    public Integer getCaptchaCodeTypeId() {
        return captchaCodeTypeId;
    }

    /**
     * 获取枚举详情
     *
     * @return 枚举详情
     */
    public String getCaptchaCodeTypeName() {
        return captchaCodeTypeName;
    }

}
