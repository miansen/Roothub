package wang.miansen.roothub.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import wang.miansen.roothub.auth.exception.MobileNotFoundException;

/**
 * 加载用户数据的 Service 接口。
 * <p>
 * 它在整个框架中用作用户 DAO，是 {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider} 使用的策略。
 * </p>
 * 该接口继承 {@link UserDetailsService} 接口，并扩展出多个只读方法。
 *
 * @author miansen.wang
 * @date 2020-03-16
 * @since 3.0
 */
public interface AuthenticationUserDetailsService extends UserDetailsService {

    /**
     * 根据手机号码查找用户
     *
     * @param mobile 手机号码（非空）
     * @return 完整的用户信息（非空）
     * @throws MobileNotFoundException 如果找不到用户或者用户没有授予的权限，则抛出此异常。
     */
    UserDetails loadUserByMobile(String mobile) throws MobileNotFoundException;
}
