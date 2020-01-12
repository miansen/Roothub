package wang.miansen.roothub.modules.security.service;

import java.util.List;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.modules.security.model.Role;

/**
 * @author miansen.wang
 * @date 2019年2月27日 下午3:40:52
 */
public interface RoleService {
	
	// 根据ID查询角色
	Role getById(Integer id);
	
	// 根据名称查询角色
	Role getByName(String name);
	
	//分页查询所有角色
	Page<Role> page(Integer pageNumber, Integer pageSize);
	
	// 根据后台用户ID查询所关联的所有角色
	List<Role> getByAdminUserId(Integer adminUserId,Integer pageNumber, Integer pageSize);
	
	// 新增角色
	void save(String roleName,Integer[] permissionIds);
	
	/**
	 * 更新角色
	 * @param roleId
	 * @param roleName
	 * @param permissionIds
	 */
	void update(Integer roleId,String roleName,Integer[] permissionIds);
	
	// 删除角色
	void removeById(Integer id);
	
	// 统计所有角色
	int countAll();
}
