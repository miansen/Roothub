package wang.miansen.roothub.auth.service;

/**
 * 认证密码处理器接口。对用户提交的密码进行加密操作。
 *
 * @author miansen.wang
 * @version 3.0, 2020-03-16
 * @since 3.0
 */
public interface AuthenticationMd5PasswordEncoderService {

    /**
     * 将密码 MD5 加密
     *
     * @param password 密码
     * @param salt 盐值
     * @return String
     */
    String encodePassword(String password, Object salt);
}
