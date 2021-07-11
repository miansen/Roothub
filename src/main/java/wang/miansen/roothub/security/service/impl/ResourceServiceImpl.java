package wang.miansen.roothub.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.security.bo.ResourceBO;
import wang.miansen.roothub.security.dao.ResourceDAO;
import wang.miansen.roothub.security.mapper.RbacDo2BoMapper;
import wang.miansen.roothub.security.service.ResourceService;

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
    private RbacDo2BoMapper do2BoMapper;

    @Override
    public List<ResourceBO> list() {
        return do2BoMapper.resourceDoList2BoList(resourceDao.selectList(null));
    }
}
