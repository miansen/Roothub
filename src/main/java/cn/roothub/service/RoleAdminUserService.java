package cn.roothub.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import cn.roothub.entity.RoleAdminUserRel;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-02-28
 */
public interface RoleAdminUserService {

	/**
	 * 获取查询所有的关联关系
	 * @param adminUserId: 后台用户ID
	 * @return
	 */
	List<RoleAdminUserRel> getAllByAdminUserId(Integer adminUserId);
	
	/**
	 * 获取查询所有的关联关系
	 * @param roleId: 角色ID
	 * @return
	 */
	List<RoleAdminUserRel> getAllByRoleId(Integer roleId);
	
	/**
	 * 批量保存
	 * @param roleAdminUserRels
	 */
	void saveBatch(Collection<? extends Serializable> roleAdminUserRels);
	
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
