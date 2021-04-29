package wang.miansen.roothub.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
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
}
