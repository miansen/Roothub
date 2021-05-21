package wang.miansen.roothub.rbac.service;

import java.util.List;

import wang.miansen.roothub.rbac.bo.RoleBO;

/**
 * 角色 Service 接口
 *
 * @author miansen.wang
 * @date 2020-12-27 16:08
 */
public interface RoleService {

    /**
     * 根据用户 ID 查询角色列表
     *
     * @param userId 用户 ID
     * @return 角色列表
     */
    List<RoleBO> listByUserId(Long userId);

    /**
     * 根据角色编码查询角色
     *
     * @param roleCode 角色编码
     * @return 角色 BO 对象
     */
    RoleBO getByRoleCode(String roleCode);

    /**
     * 给用户添加角色
     *
     * @param roleId 角色 ID
     * @param userId 用户 ID
     */
    void add(Long roleId, Long userId);
}
