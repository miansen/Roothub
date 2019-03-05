package cn.roothub.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.roothub.dao.AdminUserDao;
import cn.roothub.dto.PageDataBody;
import cn.roothub.entity.AdminUser;
import cn.roothub.entity.AdminUserRoleRel;
import cn.roothub.exception.ApiAssert;
import cn.roothub.service.AdminUserRoleRelService;
import cn.roothub.service.AdminUserService;
import cn.roothub.service.RoleService;
import cn.roothub.util.SimpleHashUtil;


/**
 * @author miansen.wang
 * @date 2019年2月27日 下午2:51:52
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserDao adminUserDao;
	@Autowired
	private RoleService roleService;
	@Autowired
	private AdminUserRoleRelService adminUserRoleRelService;
	
	@Override
	public AdminUser getByName(String name) {
		return adminUserDao.selectByName(name);
	}

	@Override
	public AdminUser getById(Integer id) {
		return adminUserDao.selectById(id);
	}

	@Override
	public PageDataBody<AdminUser> page(Integer pageNumber, Integer pageSize) {
		List<AdminUser> list = adminUserDao.selectAll((pageNumber - 1) * pageSize, pageSize);
		int countAll = this.countAll();
		return new PageDataBody<>(list, pageNumber, pageSize, countAll);
	}

	@Override
	public PageDataBody<AdminUser> pageRoles(Integer pageNumber, Integer pageSize) {
		List<AdminUser> adminUsers = adminUserDao.selectAll((pageNumber - 1) * pageSize, pageSize);
		int countAll = this.countAll();
		adminUsers.forEach(adminUser -> adminUser.setRoles(roleService.getByAdminUserId(adminUser.getAdminUserId(), null, null)));
		return new PageDataBody<>(adminUsers, pageNumber, pageSize, countAll);
	}

	@Override
	public int countAll() {
		return adminUserDao.countAll();
	}

	@Override
	public void save(String username,String password,Integer[] roleIds) {
		AdminUser adminUser = getByName(username);
		ApiAssert.isNull(adminUser, "用户已存在");
		adminUser = new AdminUser();
		adminUser.setUsername(username);
		adminUser.setPassword(SimpleHashUtil.simpleHash("MD5", password, username, 1024).toString());
		adminUser.setCreateDate(new Date());
		// 保存用户
		adminUserDao.insert(adminUser);
		// 在查询保存的用户
		AdminUser adminUser2 = getById(adminUser.getAdminUserId());
		List<AdminUserRoleRel> adminUserRoleRels = new ArrayList<>();
		if(roleIds != null && roleIds.length > 0) {
			Arrays.asList(roleIds).forEach( roleId -> {
				AdminUserRoleRel adminUserRoleRel = new AdminUserRoleRel();
				adminUserRoleRel.setAdminUserId(adminUser2.getAdminUserId());
				adminUserRoleRel.setRoleId(roleId);
				adminUserRoleRel.setCreateDate(new Date());
				adminUserRoleRels.add(adminUserRoleRel);
			});
		}
		// 保存用户与角色的关联关系
		if(adminUserRoleRels != null && adminUserRoleRels.size() > 0) {
			adminUserRoleRelService.saveBatch(adminUserRoleRels);
			}
	}

	@Override
	public void update(AdminUser adminUser) {
		adminUserDao.update(adminUser);
	}

	@Override
	public void removeById(Integer id) {
		// 先删除用户与角色的关联关系
		adminUserRoleRelService.removeByAdminUserId(id);
		// 再删除用户
		adminUserDao.deleteById(id);
	}
}
