package wang.miansen.roothub.common.captcha.enums;

import java.util.Objects;

/**
 * 验证码关联的对象的类型枚举
 *
 * @author miansen.wang
 * @date 2021-05-02 15:14
 */
public enum CaptchaRelTypeEnum {

    /**
     * 用户ID
     */
    USER_ID(1, "用户ID"),

    /**
     * 手机号码
     */
    MOBILE(2, "手机号码"),

    /**
     * 电子邮箱
     */
    EMAIL(3, "电子邮箱");

    /**
     * 枚举编号
     */
    private final Integer captchaRelTypeId;

    /**
     * 枚举详情
     */
    private final String captchaRelTypeName;

    /**
     * 构造方法
     *
     * @param captchaRelTypeId 枚举编号
     * @param captchaRelTypeName 枚举详情
     */
    CaptchaRelTypeEnum(Integer captchaRelTypeId, String captchaRelTypeName) {
        this.captchaRelTypeId = captchaRelTypeId;
        this.captchaRelTypeName = captchaRelTypeName;
    }

    /**
     * 根据枚举编号获取枚举
     *
     * @param captchaRelTypeId 枚举编号
     * @return 枚举
     */
    public static CaptchaRelTypeEnum valueOf(Integer captchaRelTypeId) {
        for (CaptchaRelTypeEnum captchaRelTypeEnum : CaptchaRelTypeEnum.values()) {
            if (Objects.equals(captchaRelTypeEnum.getCaptchaRelTypeId(), captchaRelTypeId)) {
                return captchaRelTypeEnum;
            }
        }
        return null;
    }

    /**
     * 获取枚举编号
     *
     * @return 枚举编号
     */
    public Integer getCaptchaRelTypeId() {
        return captchaRelTypeId;
    }

    /**
     * 获取枚举详情
     *
     * @return 枚举详情
     */
    public String getCaptchaRelTypeName() {
        return captchaRelTypeName;
    }
}
