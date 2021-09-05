package wang.miansen.roothub.security.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.util.CollectionUtils;
import wang.miansen.roothub.security.bo.SecPermissionBO;
import wang.miansen.roothub.security.dao.SecPermissionDAO;
import wang.miansen.roothub.security.dao.SecPermissionResourceRelDAO;
import wang.miansen.roothub.security.dao.SecRolePermissionRelDAO;
import wang.miansen.roothub.security.entity.SecPermissionDO;
import wang.miansen.roothub.security.entity.SecPermissionResourceRelDO;
import wang.miansen.roothub.security.entity.SecRolePermissionRelDO;
import wang.miansen.roothub.security.mapper.SecDo2BoMapper;
import wang.miansen.roothub.security.service.SecPermissionService;

/**
 * 权限 Service 接口实现
 *
 * @author miansen.wang
 * @date 2020-12-27 15:33
 */
@Service
public class SecPermissionServiceImpl implements SecPermissionService {

    @Autowired
    private SecPermissionDAO permissionDao;

    @Autowired
    private SecRolePermissionRelDAO rolePermissionRelDao;

    @Autowired
    private SecPermissionResourceRelDAO permissionResourceRelDao;

    @Autowired
    private SecDo2BoMapper do2BoMapper;

    @Override
    public List<SecPermissionBO> listByIds(List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            List<SecPermissionDO> permissionDOList = permissionDao.selectBatchIds(ids);
            return do2BoMapper.permissionDoList2BoList(permissionDOList);
        }
        return Collections.emptyList();
    }

    @Override
    public List<SecPermissionBO> listByRoleId(Long roleId) {
        QueryWrapper<SecRolePermissionRelDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
            .eq(SecRolePermissionRelDO::getRoleId, roleId)
            .eq(SecRolePermissionRelDO::getIsDisabled, Boolean.FALSE)
            .eq(SecRolePermissionRelDO::getIsDeleted, Boolean.FALSE);
        List<SecRolePermissionRelDO> rolePermissionRelList = rolePermissionRelDao.selectList(wrapper);
        List<Long> permissionIds = rolePermissionRelList.stream().map(SecRolePermissionRelDO::getPermissionId).collect(Collectors.toList());
        return listByIds(permissionIds);
    }

    @Override
    public List<SecPermissionBO> listByResourceId(Long resourceId) {
        QueryWrapper<SecPermissionResourceRelDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
            .eq(SecPermissionResourceRelDO::getResourceId, resourceId)
            .eq(SecPermissionResourceRelDO::getIsDisabled, Boolean.FALSE)
            .eq(SecPermissionResourceRelDO::getIsDeleted, Boolean.FALSE);
        List<SecPermissionResourceRelDO> permissionResourceRelList = permissionResourceRelDao.selectList(wrapper);
        List<Long> permissionIds = permissionResourceRelList.stream().map(SecPermissionResourceRelDO::getPermissionId).collect(Collectors.toList());
        return listByIds(permissionIds);
    }
}
