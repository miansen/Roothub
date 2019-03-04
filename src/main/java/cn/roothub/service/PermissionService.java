package cn.roothub.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import cn.roothub.entity.Permission;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-01
 */
public interface PermissionService {

	/**
	 * 根据角色列表批量获取所关联的权限
	 * @param roleList
	 * @return
	 */
	List<Permission> getBatchByRoleList(Collection<? extends Serializable> roleList);
	
	/**
	 * 根据角色ID获取权限
	 * @param roleId
	 * @return
	 */
	List<Permission> getAllByRoleId(Integer roleId);
	
	/**
	 * 根据pid获取权限
	 * @param pid
	 * @return
	 */
	List<Permission> getAllByPid(Integer pid);
	
	/**
	 * 根据pid查询权限
	 * @return: 返回的是map,key是pid=0的权限名字，value是对应的子权限
	 */
	Map<String, Object> permissionMap();
	
	void save(Permission permission);
	
	void update(Permission permission);
	
	void removeById(Integer id);
}
