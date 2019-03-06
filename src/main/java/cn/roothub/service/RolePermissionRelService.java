package cn.roothub.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import cn.roothub.entity.RolePermissionRel;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-01
 */
public interface RolePermissionRelService {

	List<RolePermissionRel> getAllByRoleId(Integer roleId);
	
	List<RolePermissionRel> getAllByPermissionId(Integer permissionId);
	
	void saveBatch(Collection<? extends Serializable> rolePermissionRels);
	
	void removeByRoleId(Integer roleId);
	
	void removeByPermissionId(Integer permissionId);
	
	void removeBatch(Collection<? extends Serializable> permissionIds);
}
