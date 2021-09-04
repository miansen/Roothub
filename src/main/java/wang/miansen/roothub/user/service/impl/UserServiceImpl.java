package wang.miansen.roothub.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.miansen.roothub.auth.service.AuthenticationMd5PasswordEncoderService;
import wang.miansen.roothub.common.constant.BaseConstants;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.util.NicknameUtils;
import wang.miansen.roothub.security.service.RoleService;
import wang.miansen.roothub.user.bo.UserBO;
import wang.miansen.roothub.user.dao.UserDAO;
import wang.miansen.roothub.user.entity.UserDO;
import wang.miansen.roothub.user.mapper.UserDo2BoMapper;
import wang.miansen.roothub.user.service.UserService;

/**
 * 用户 Service 接口实现类
 *
 * @author miansen.wang
 * @since 2021-04-17 17:06
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserDo2BoMapper do2BoMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthenticationMd5PasswordEncoderService authenticationMd5PasswordEncoderService;

    @Override
    public UserBO getById(Long userId) {
        return do2BoMapper.userDo2Bo(userDao.selectById(userId));
    }

    @Override
    public UserBO getByUsername(String username) {
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
            .eq(UserDO::getUsername, username)
            .eq(UserDO::getIsDeleted, Boolean.FALSE);
        return do2BoMapper.userDo2Bo(userDao.selectOne(wrapper));
    }

    @Override
    public UserBO getByEmail(String email) {
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
            .eq(UserDO::getEmail, email)
            .eq(UserDO::getIsDeleted, Boolean.FALSE);
        return do2BoMapper.userDo2Bo(userDao.selectOne(wrapper));
    }

    @Override
    public UserBO getByMobile(String mobile) {
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
            .eq(UserDO::getMobile, mobile)
            .eq(UserDO::getIsDeleted, Boolean.FALSE);
        return do2BoMapper.userDo2Bo(userDao.selectOne(wrapper));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void registerByMobile(String mobile) {
        UserDO user = new UserDO();
        user.setUsername(NicknameUtils.generateNickname());
        user.setPassword(authenticationMd5PasswordEncoderService.encodePassword("123456", null));
        user.setMobile(mobile);
        userDao.insertNotNull(user);
        roleService.add(BaseConstants.GENERAL_ROLE_ID, user.getUserId());
    }
}
