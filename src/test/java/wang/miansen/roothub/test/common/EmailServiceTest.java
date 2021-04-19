package wang.miansen.roothub.test.common;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import wang.miansen.roothub.common.mail.service.EmailService;
import wang.miansen.roothub.test.BaseTest;

/**
 * 电子邮件服务单元测试
 *
 * @author miansen.wang
 * @since 2021-04-18 15:50
 */
public class EmailServiceTest extends BaseTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void sendTest() {
        String recipient = "1918823303@qq.com";
        String title = "感谢注册 Roothub，点击下面链接激活帐号";
        String content = "如果不是你注册了 Roothub，请忽略此邮件&nbsp;&nbsp;<a href='https://www.baidu.com'>点击激活</a>";
        emailService.send(recipient, title, content);
        logger.info("emailService send.");
    }
}
