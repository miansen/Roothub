package cn.roothub.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import cn.roothub.entity.Permission;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-01
 */
public interface PermissionService {

	List<Permission> getBatchByRoleList(Collection<? extends Serializable> roleList);
	
	List<Permission> getAllByPid(Integer pid);
	
	void save(Permission permission);
	
	void update(Permission permission);
	
	void removeById(Integer id);
}
