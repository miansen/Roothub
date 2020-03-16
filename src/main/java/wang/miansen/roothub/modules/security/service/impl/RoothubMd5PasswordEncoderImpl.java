package wang.miansen.roothub.modules.security.service.impl;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.modules.security.service.RoothubMd5PasswordEncoder;

/**
 * @author miansen.wang
 * @date 2020-03-16
 */
@Service
public class RoothubMd5PasswordEncoderImpl extends Md5PasswordEncoder implements RoothubMd5PasswordEncoder {

	@Override
	public String encodePassword(String password) {
		return encodePassword(password, null);
	}

}
