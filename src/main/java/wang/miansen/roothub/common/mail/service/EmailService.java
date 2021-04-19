package wang.miansen.roothub.common.mail.service;

/**
 * 电子邮件服务接口
 *
 * @author miansen.wang
 * @since 2021-04-18 14:41
 */
public interface EmailService {

    /**
     * 发送电子邮件
     *
     * @param recipient 收件人
     * @param title 主题
     * @param content 内容
     */
    void send(String recipient, String title, String content);
}
