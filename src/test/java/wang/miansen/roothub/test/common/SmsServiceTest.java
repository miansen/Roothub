package wang.miansen.roothub.test.common;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import wang.miansen.roothub.common.sms.service.SmsService;
import wang.miansen.roothub.test.BaseTest;

/**
 * 短信服务接口 单元测试
 *
 * @author miansen.wang
 * @since 2021-04-29 10:57
 */
public class SmsServiceTest extends BaseTest {

    @Autowired
    private SmsService smsService;

    @Test
    public void sendCodeTest() {
        smsService.sendCode("18390800450", "1058");
        logger.info("sendCode end.");
    }
}
