package wang.miansen.roothub.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.security.bo.SecSystemBO;
import wang.miansen.roothub.security.dao.SecSystemDAO;
import wang.miansen.roothub.security.entity.SecSystemDO;
import wang.miansen.roothub.security.mapper.SecDo2BoMapper;
import wang.miansen.roothub.security.service.SecSystemService;

/**
 * 系统 Service 接口实现
 *
 * @author miansen.wang
 * @version 2021-06-20 21:29
 */
@Service
public class SecSystemServiceImpl implements SecSystemService {

    @Autowired
    private SecSystemDAO systemDao;

    @Autowired
    private SecDo2BoMapper do2BoMapper;

    @Override
    public List<SecSystemBO> list() {
        QueryWrapper<SecSystemDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
            .eq(SecSystemDO::getIsDisabled, Boolean.FALSE)
            .eq(SecSystemDO::getIsDeleted, Boolean.FALSE)
            .orderByAsc(SecSystemDO::getSystemSort);
        return do2BoMapper.systemDoList2BoList(systemDao.selectList(wrapper));
    }
}
