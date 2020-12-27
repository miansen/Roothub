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
}
