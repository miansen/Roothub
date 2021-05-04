package wang.miansen.roothub.test.common;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import wang.miansen.roothub.common.captcha.enums.CaptchaCodeTypeEnum;
import wang.miansen.roothub.common.captcha.enums.CaptchaRelTypeEnum;
import wang.miansen.roothub.common.captcha.service.CaptchaService;
import wang.miansen.roothub.test.BaseTest;

/**
 * 验证码 Service 接口单元测试
 *
 * @author miansen.wang
 * @date 2021-05-02 16:36
 */
public class CaptchaServiceTest extends BaseTest {

    @Autowired
    private CaptchaService captchaService;

    @Test
    public void generateNumberCode() {
        String code = captchaService.generateNumberCode("18390800450", CaptchaRelTypeEnum.MOBILE, CaptchaCodeTypeEnum.SIGN_UP, 4);
        logger.info("code: {}", code);
    }

    @Test
    public void verifyCodeTest() {
        boolean b = captchaService.verifyCode("18390800450", CaptchaRelTypeEnum.MOBILE, "9620", CaptchaCodeTypeEnum.SIGN_UP);
        logger.info("{}", b);
    }
}
