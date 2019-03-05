package cn.roothub.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.roothub.dao.PermissionDao;
import cn.roothub.entity.Permission;
import cn.roothub.service.PermissionService;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-01
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;
	
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

}
