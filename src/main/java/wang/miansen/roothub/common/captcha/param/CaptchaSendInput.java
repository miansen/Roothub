package wang.miansen.roothub.common.captcha.param;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发送验证码输入参数
 *
 * @author miansen.wang
 * @date 2021-05-23 15:57
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaSendInput {

    /**
     * 手机号码
     */
    @NotNull
    private String mobile;

    /**
     * 要发送哪种类型的验证码，参考：{@link wang.miansen.roothub.common.captcha.enums.CaptchaCodeTypeEnum}
     */
    @NotNull
    private Integer type;

    /**
     * 腾讯防水墙票据
     */
    @NotNull
    private String ticket;

    /**
     * 腾讯防水墙随机字符串E
     */
    @NotNull
    private String randStr;
}
