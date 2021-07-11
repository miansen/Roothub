package wang.miansen.roothub.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.security.bo.SystemBO;
import wang.miansen.roothub.security.dao.SystemDAO;
import wang.miansen.roothub.security.entity.SystemDO;
import wang.miansen.roothub.security.mapper.RbacDo2BoMapper;
import wang.miansen.roothub.security.service.SystemService;

/**
 * 系统 Service 接口实现
 *
 * @author miansen.wang
 * @version 2021-06-20 21:29
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemDAO systemDao;

    @Autowired
    private RbacDo2BoMapper do2BoMapper;

    @Override
    public List<SystemBO> list() {
        QueryWrapper<SystemDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
            .eq(SystemDO::getIsDisabled, Boolean.FALSE)
            .eq(SystemDO::getIsDeleted, Boolean.FALSE)
            .orderByAsc(SystemDO::getSystemSort);
        return do2BoMapper.systemDoList2BoList(systemDao.selectList(wrapper));
    }
}
