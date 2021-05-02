package wang.miansen.roothub.common.captcha.entity;

import java.util.Date;

import lombok.Data;

import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.dao.mapper.enums.IdType;

/**
 * 验证码实体类
 *
 * @author miansen.wang
 * @date 2021-05-02 14:48
 */
@Data
@Table(value = "captcha")
public class CaptchaDO {

    /**
     * 主键ID
     */
    @Id(value = "captcha_id", type = IdType.AUTO)
    private Long captchaId;

    /**
     * 验证码关联的对象，可以是用户ID、手机号码或者邮箱
     */
    private String captchaRel;

    /**
     * 验证码关联的对象的类型，1：用户ID，2：手机号码，3：邮箱
     */
    private Byte captchaRelType;

    /**
     * 验证码
     */
    private String captchaCode;

    /**
     * 验证码的类型，1：注册登录验证码，2：找回密码验证码，3：密码重置验证码，4：发送短信验证码，5：邮箱验证码，6：秒杀验证码，
     * 7：领券验证码，8：抢红包验证码，9：发帖验证码，10：评论验证码，11：投票验证码
     */
    private Byte captchaCodeType;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 是否使用过。0：否，1：是
     */
    private Boolean isUsed;

    /**
     * 是否删除（0：有效，1：删除）
     */
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
