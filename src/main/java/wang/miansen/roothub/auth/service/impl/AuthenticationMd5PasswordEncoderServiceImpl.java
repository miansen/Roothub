package wang.miansen.roothub.auth.service.impl;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import wang.miansen.roothub.auth.service.AuthenticationMd5PasswordEncoderService;

/**
 * 认证密码处理器实现类。
 * <p>
 * 具体实现请参考：{@code org.springframework.security.authentication.encoding.Md5PasswordEncoder#Md5PasswordEncoder()}
 * </p>
 *
 * @author miansen.wang
 * @date 2020-03-16
 * @since 3.0
 */
public class AuthenticationMd5PasswordEncoderServiceImpl extends Md5PasswordEncoder
    implements AuthenticationMd5PasswordEncoderService {

}
