package wang.miansen.roothub.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.apache.xpath.internal.operations.Bool;

import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.rbac.bo.SystemBO;
import wang.miansen.roothub.rbac.dao.SystemDAO;
import wang.miansen.roothub.rbac.entity.SystemDO;
import wang.miansen.roothub.rbac.mapper.RbacDo2BoMapper;
import wang.miansen.roothub.rbac.service.SystemService;

/**
 * 系统 Service 接口实现
 *
 * @author miansen.wang
 * @date 2021-06-20 21:29
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
