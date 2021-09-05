package wang.miansen.roothub.security.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.util.CollectionUtils;
import wang.miansen.roothub.security.bo.SecRoleBO;
import wang.miansen.roothub.security.dao.SecRoleDAO;
import wang.miansen.roothub.security.dao.SecRoleUserRelDAO;
import wang.miansen.roothub.security.entity.SecRoleDO;
import wang.miansen.roothub.security.entity.SecRoleUserRelDO;
import wang.miansen.roothub.security.mapper.SecDo2BoMapper;
import wang.miansen.roothub.security.service.SecRoleService;

/**
 * 角色 Service 接口实现
 *
 * @author miansen.wang
 * @date 2020-12-27 16:10
 */
@Service
public class SecRoleServiceImpl implements SecRoleService {

    @Autowired
    private SecRoleDAO roleDao;

    @Autowired
    private SecRoleUserRelDAO roleUserRelDao;

    @Autowired
    private SecDo2BoMapper do2BoMapper;

    @Override
    public List<SecRoleBO> listByUserId(Long userId) {
        QueryWrapper<SecRoleUserRelDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
            .eq(SecRoleUserRelDO::getUserId, userId)
            .eq(SecRoleUserRelDO::getIsDisabled, Boolean.FALSE)
            .eq(SecRoleUserRelDO::getIsDeleted, Boolean.FALSE);
        List<SecRoleUserRelDO> roleUserRelList = roleUserRelDao.selectList(wrapper);
        List<Long> roleIds = roleUserRelList.stream().map(SecRoleUserRelDO::getRoleId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(roleIds)) {
            List<SecRoleDO> roleDOList = roleDao.selectBatchIds(roleIds);
            return do2BoMapper.roleDoList2BoList(roleDOList);
        }
        return Collections.emptyList();
    }

    @Override
    public SecRoleBO getByRoleCode(String roleCode) {
        QueryWrapper<SecRoleDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
            .eq(SecRoleDO::getRoleCode, roleCode)
            .eq(SecRoleDO::getIsDisabled, Boolean.FALSE)
            .eq(SecRoleDO::getIsDeleted, Boolean.FALSE);
        SecRoleDO roleDO = roleDao.selectOne(wrapper);
        return do2BoMapper.roleDo2Bo(roleDO);
    }

    @Override
    public void add(Long roleId, Long userId) {
        SecRoleUserRelDO roleUserRel = new SecRoleUserRelDO();
        roleUserRel.setRoleId(roleId);
        roleUserRel.setUserId(userId);
        roleUserRelDao.insertNotNull(roleUserRel);
    }
}
