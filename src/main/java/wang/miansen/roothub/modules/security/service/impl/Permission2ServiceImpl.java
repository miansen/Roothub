package wang.miansen.roothub.modules.security.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import wang.miansen.roothub.modules.security.dao.PermissionDao;
import wang.miansen.roothub.modules.security.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import wang.miansen.roothub.modules.security.service.PermissionService;
import wang.miansen.roothub.modules.security.service.RolePermissionRelService;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-01
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;
	
	@Autowired
	private RolePermissionRelService rolePermissionRelService;
	
	@Override
	public List<Permission> getBatchByRoleList(Collection<? extends Serializable> roleList) {
		return permissionDao.selectBatchByRoleList(roleList, null, null);
	}

	@Override
	public List<Permission> getAllByPid(Integer pid) {
		return permissionDao.selectAllByPid(pid, null, null);
	}

	@Override
	public void save(String pname,String name,String value) {
		Permission permission = new Permission();
		if(pname != null && !StringUtils.isEmpty(pname)) {
			permission.setPermissionName(name);
			permission.setPermissionValue(value);
			permission.setCreateDate(new Date());
			permission.setPid(getByName(pname).getPermissionId());
			permissionDao.insert(permission);
		}else {
			permission.setPermissionName(name);
			permission.setPermissionValue(value);
			permission.setCreateDate(new Date());
			permission.setPid(0);
			permissionDao.insert(permission);
		}
	}

	@Override
	public void update(Permission permission) {
		permissionDao.update(permission);
	}

	@Override
	public void removeById(Integer id) {
		permissionDao.deleteById(id);
	}

	@Override
	public Map<String, Object> permissionMap() {
		Map<String,Object> map = new LinkedHashMap<>();
		getAllByPid(0).forEach(role -> map.put(role.getPermissionName(), getAllByPid(role.getPermissionId())));
		return map;
	}

	@Override
	public List<Permission> getAllByRoleId(Integer roleId) {
		return permissionDao.selectAllByRoleId(roleId);
	}

	@Override
	public Permission getByName(String name) {
		return permissionDao.selectByName(name);
	}

	@Override
	public Permission getById(Integer id) {
		return permissionDao.selectById(id);
	}

	@Transactional
	@Override
	public void remove(Integer id, String name) {
		// 如果删除的是父权限
		if(name != null && !StringUtils.isEmpty(name)) {
			// 1.先查询出父权限
			Permission permission = getByName(name);
			// 2.根据父权限ID查询对应的子权限
			List<Permission> list = getAllByPid(permission.getPermissionId());
			// 3.删除子权限与角色的关联关系
			rolePermissionRelService.removeBatch(list);
			// 4.删除子权限
			removeByPid(permission.getPermissionId());
			// 5.删除父权限
			removeById(permission.getPermissionId());
		}else {
			// 删除子权限与角色的关联关系
			rolePermissionRelService.removeByPermissionId(id);
			// 删除子权限
			removeById(id);
		}
	}

	
	@Override
	public void removeByPid(Integer pid) {
		permissionDao.deleteByPid(pid);
	}

}
