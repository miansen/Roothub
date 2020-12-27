package wang.miansen.roothub.rbac.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.util.CollectionUtils;
import wang.miansen.roothub.rbac.bo.PermissionBO;
import wang.miansen.roothub.rbac.dao.PermissionDAO;
import wang.miansen.roothub.rbac.dao.PermissionResourceRelDAO;
import wang.miansen.roothub.rbac.dao.RolePermissionRelDAO;
import wang.miansen.roothub.rbac.entity.PermissionDO;
import wang.miansen.roothub.rbac.entity.PermissionResourceRelDO;
import wang.miansen.roothub.rbac.entity.RolePermissionRelDO;
import wang.miansen.roothub.rbac.mapper.Do2BoMapper;
import wang.miansen.roothub.rbac.service.PermissionService;

/**
 * 权限 Service 接口实现
 *
 * @author miansen.wang
 * @date 2020-12-27 15:33
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDAO permissionDAO;

    @Autowired
    private RolePermissionRelDAO rolePermissionRelDAO;

    @Autowired
    private PermissionResourceRelDAO permissionResourceRelDAO;

    @Autowired
    private Do2BoMapper do2BoMapper;

    @Override
    public List<PermissionBO> listByIds(List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            List<PermissionDO> permissionDOList = permissionDAO.selectBatchIds(ids);
            return do2BoMapper.permissionDoList2BoList(permissionDOList);
        }
        return Collections.emptyList();
    }

    @Override
    public List<PermissionBO> listByRoleId(Long roleId) {
        QueryWrapper<RolePermissionRelDO> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        wrapper.eq("is_disabled", "0");
        wrapper.eq("is_deleted", "0");
        List<RolePermissionRelDO> rolePermissionRelList = rolePermissionRelDAO.selectList(wrapper);
        List<Long> permissionIds = rolePermissionRelList.stream().map(RolePermissionRelDO::getPermissionId).collect(Collectors.toList());
        return listByIds(permissionIds);
    }

    @Override
    public List<PermissionBO> listByResourceId(Long resourceId) {
        QueryWrapper<PermissionResourceRelDO> wrapper = new QueryWrapper<>();
        wrapper.eq("resource_id", resourceId);
        wrapper.eq("is_disabled", "0");
        wrapper.eq("is_deleted", "0");
        List<PermissionResourceRelDO> permissionResourceRelList = permissionResourceRelDAO.selectList(wrapper);
        List<Long> permissionIds = permissionResourceRelList.stream().map(PermissionResourceRelDO::getPermissionId).collect(Collectors.toList());
        return listByIds(permissionIds);
    }
}
