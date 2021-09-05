package wang.miansen.roothub.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.security.bo.SecResourceBO;
import wang.miansen.roothub.security.dao.SecResourceDAO;
import wang.miansen.roothub.security.mapper.SecDo2BoMapper;
import wang.miansen.roothub.security.service.SecResourceService;

/**
 * 资源 Service 接口实现
 *
 * @author miansen.wang
 * @date 2020-12-27 15:28
 */
@Service
public class SecResourceServiceImpl implements SecResourceService {

    @Autowired
    private SecResourceDAO resourceDao;

    @Autowired
    private SecDo2BoMapper do2BoMapper;

    @Override
    public List<SecResourceBO> list() {
        return do2BoMapper.resourceDoList2BoList(resourceDao.selectList(null));
    }
}
