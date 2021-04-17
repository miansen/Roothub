package wang.miansen.roothub.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.rbac.bo.ResourceBO;
import wang.miansen.roothub.rbac.dao.ResourceDAO;
import wang.miansen.roothub.rbac.mapper.Do2BoMapper;
import wang.miansen.roothub.rbac.service.ResourceService;

/**
 * 资源 Service 接口实现
 *
 * @author miansen.wang
 * @date 2020-12-27 15:28
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDAO resourceDao;

    @Autowired
    private Do2BoMapper do2BoMapper;

    @Override
    public List<ResourceBO> list() {
        return do2BoMapper.resourceDoList2BoList(resourceDao.selectList(null));
    }
}
