package cn.roothub.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.roothub.dao.AdminUserDao;
import cn.roothub.dto.PageDataBody;
import cn.roothub.entity.AdminUser;
import cn.roothub.service.AdminUserService;
import cn.roothub.service.RoleService;


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
	public void save(AdminUser adminUser) {
		adminUserDao.insert(adminUser);
	}

	@Override
	public void update(AdminUser adminUser) {
		adminUserDao.update(adminUser);
	}

	@Override
	public void removeById(Integer id) {
		adminUserDao.deleteById(id);
	}
}
