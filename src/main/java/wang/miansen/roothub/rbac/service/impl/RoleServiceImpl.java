package wang.miansen.roothub.rbac.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.util.CollectionUtils;
import wang.miansen.roothub.rbac.bo.RoleBO;
import wang.miansen.roothub.rbac.dao.RoleDAO;
import wang.miansen.roothub.rbac.dao.RoleUserRelDAO;
import wang.miansen.roothub.rbac.entity.RoleDO;
import wang.miansen.roothub.rbac.entity.RoleUserRelDO;
import wang.miansen.roothub.rbac.mapper.Do2BoMapper;
import wang.miansen.roothub.rbac.service.RoleService;

/**
 * 角色 Service 接口实现
 *
 * @author miansen.wang
 * @date 2020-12-27 16:10
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private RoleUserRelDAO roleUserRelDAO;

    @Autowired
    private Do2BoMapper do2BoMapper;

    @Override
    public List<RoleBO> listByUserId(Long userId) {
        QueryWrapper<RoleUserRelDO> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("is_disabled", "0");
        wrapper.eq("is_deleted", "0");
        List<RoleUserRelDO> roleUserRelList = roleUserRelDAO.selectList(wrapper);
        List<Long> roleIds = roleUserRelList.stream().map(RoleUserRelDO::getRoleId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(roleIds)) {
            List<RoleDO> roleDOList = roleDAO.selectBatchIds(roleIds);
            return do2BoMapper.roleDoList2BoList(roleDOList);
        }
        return Collections.emptyList();
    }
}
