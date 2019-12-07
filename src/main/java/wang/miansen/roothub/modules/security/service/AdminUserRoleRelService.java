package wang.miansen.roothub.modules.security.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import wang.miansen.roothub.modules.security.model.AdminUserRoleRel;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-02-28
 */
public interface AdminUserRoleRelService {

	/**
	 * 获取查询所有的关联关系
	 * @param adminUserId: 后台用户ID
	 * @return
	 */
	List<AdminUserRoleRel> getAllByAdminUserId(Integer adminUserId);
	
	/**
	 * 获取查询所有的关联关系
	 * @param roleId: 角色ID
	 * @return
	 */
	List<AdminUserRoleRel> getAllByRoleId(Integer roleId);
	
	/**
	 * 批量保存
	 * @param adminUserRoleRels
	 */
	void saveBatch(Collection<? extends Serializable> adminUserRoleRels);
	
	/**
	 * 删除后台用户与角色的关联关系
	 * @param adminUserId: 后台用户ID
	 */
	void removeByAdminUserId(Integer adminUserId);
	
	/**
	 * 删除后台用户与角色的关联关系
	 * @param roleId: 角色ID
	 */
	void removeByRoleId(Integer roleId);
}
