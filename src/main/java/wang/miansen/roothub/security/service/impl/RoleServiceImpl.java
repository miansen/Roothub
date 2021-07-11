package wang.miansen.roothub.security.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.util.CollectionUtils;
import wang.miansen.roothub.security.bo.RoleBO;
import wang.miansen.roothub.security.dao.RoleDAO;
import wang.miansen.roothub.security.dao.RoleUserRelDAO;
import wang.miansen.roothub.security.entity.RoleDO;
import wang.miansen.roothub.security.entity.RoleUserRelDO;
import wang.miansen.roothub.security.mapper.RbacDo2BoMapper;
import wang.miansen.roothub.security.service.RoleService;

/**
 * 角色 Service 接口实现
 *
 * @author miansen.wang
 * @date 2020-12-27 16:10
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDao;

    @Autowired
    private RoleUserRelDAO roleUserRelDao;

    @Autowired
    private RbacDo2BoMapper do2BoMapper;

    @Override
    public List<RoleBO> listByUserId(Long userId) {
        QueryWrapper<RoleUserRelDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
            .eq(RoleUserRelDO::getUserId, userId)
            .eq(RoleUserRelDO::getIsDisabled, Boolean.FALSE)
            .eq(RoleUserRelDO::getIsDeleted, Boolean.FALSE);
        List<RoleUserRelDO> roleUserRelList = roleUserRelDao.selectList(wrapper);
        List<Long> roleIds = roleUserRelList.stream().map(RoleUserRelDO::getRoleId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(roleIds)) {
            List<RoleDO> roleDOList = roleDao.selectBatchIds(roleIds);
            return do2BoMapper.roleDoList2BoList(roleDOList);
        }
        return Collections.emptyList();
    }

    @Override
    public RoleBO getByRoleCode(String roleCode) {
        QueryWrapper<RoleDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
            .eq(RoleDO::getRoleCode, roleCode)
            .eq(RoleDO::getIsDisabled, Boolean.FALSE)
            .eq(RoleDO::getIsDeleted, Boolean.FALSE);
        RoleDO roleDO = roleDao.selectOne(wrapper);
        return do2BoMapper.roleDo2Bo(roleDO);
    }

    @Override
    public void add(Long roleId, Long userId) {
        RoleUserRelDO roleUserRel = new RoleUserRelDO();
        roleUserRel.setRoleId(roleId);
        roleUserRel.setUserId(userId);
        roleUserRelDao.insert(roleUserRel);
    }
}
