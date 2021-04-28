package wang.miansen.roothub.user.service;

import wang.miansen.roothub.user.bo.UserBO;

/**
 * 用户 Service 接口
 *
 * @author miansen.wang
 * @since 2021-04-17 17:05
 */
public interface UserService {

    /**
     * 根据用户 ID 查询用户
     *
     * @param userId 用户 ID
     * @return UserBO
     */
    UserBO getById(Long userId);

    /**
     * 根据用户名称查询用户
     *
     * @param username 用户名称
     * @return UserBO
     */
    UserBO getByUsername(String username);

    /**
     * 根据邮箱查询用户
     *
     * @param email 邮箱
     * @return UserBO
     */
    UserBO getByEmail(String email);

    /**
     * 根据手机号码查询用户
     *
     * @param mobile 手机号码
     * @return UserBO
     */
    UserBO getByMobile(String mobile);
}
