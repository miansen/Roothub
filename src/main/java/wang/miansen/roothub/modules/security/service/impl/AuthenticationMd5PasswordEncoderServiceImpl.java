package wang.miansen.roothub.modules.security.service.impl;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.modules.security.service.AuthenticationMd5PasswordEncoderService;

/**
 * 认证密码处理器实现类
 * 
 * @author miansen.wang
 * @date 2020-03-16
 */
@Service
public class AuthenticationMd5PasswordEncoderServiceImpl extends Md5PasswordEncoder
		implements AuthenticationMd5PasswordEncoderService {

}
