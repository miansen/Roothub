package wang.miansen.roothub.common.mail.service.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import wang.miansen.roothub.common.exception.BaseException;
import wang.miansen.roothub.common.mail.service.EmailService;
import wang.miansen.roothub.modules.system.service.SystemConfigService;

/**
 * 电子邮件服务接口实现类
 *
 * @author miansen.wang
 * @since 2021-04-18 14:44
 */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService, InitializingBean {

    @Autowired
    private SystemConfigService systemConfigService;

    private Session session;

    @Override
    public void send(String recipient, String title, String content) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(systemConfigService.getProperties().getProperty("mail_address")));
            message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(title);
            message.setContent(content, "text/html;charset=UTF-8");
            Transport.send(message);
        } catch (MessagingException e) {
            log.error("Exception in EmailService.send", e);
            throw new BaseException("Exception in EmailService.send", e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Properties props = new Properties();
        props.setProperty("mail.host", systemConfigService.getProperties().getProperty("mail.host"));
        props.setProperty("mail.smtp.auth", "true");
        this.session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(systemConfigService.getProperties().getProperty("mail_address"),
                    systemConfigService.getProperties().getProperty("mail_password"));
            }
        });
    }
}
