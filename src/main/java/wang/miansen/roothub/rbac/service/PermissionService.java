package wang.miansen.roothub.rbac.service;

import java.util.List;

import wang.miansen.roothub.rbac.bo.PermissionBO;

/**
 * 权限 Service 接口
 *
 * @author miansen.wang
 * @date 2020-12-27 15:32
 */
public interface PermissionService {

    /**
     * 根据主键 ID 查询权限列表
     *
     * @param ids 主键 ID
     * @return 权限列表
     */
    List<PermissionBO> listByIds(List<Long> ids);

    /**
     * 根据角色 ID 查询权限列表
     *
     * @param roleId 角色 ID
     * @return 权限列表
     */
    List<PermissionBO> listByRoleId(Long roleId);

    /**
     * 根据资源 ID 查询权限列表
     *
     * @param resourceId 资源 ID
     * @return 权限列表
     */
    List<PermissionBO> listByResourceId(Long resourceId);
}
