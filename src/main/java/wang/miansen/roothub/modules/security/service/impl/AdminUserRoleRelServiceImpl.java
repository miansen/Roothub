package wang.miansen.roothub.modules.security.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.miansen.roothub.modules.security.dao.AdminUserRoleRelDao;
import wang.miansen.roothub.modules.security.model.AdminUserRoleRel;
import wang.miansen.roothub.modules.security.service.AdminUserRoleRelService;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-02-28
 */
@Service
public class AdminUserRoleRelServiceImpl implements AdminUserRoleRelService {

	@Autowired
	private AdminUserRoleRelDao adminUserRoleRelDao;
	
	@Override
	public List<AdminUserRoleRel> getAllByAdminUserId(Integer adminUserId) {
		return adminUserRoleRelDao.selectAllByAdminUserId(adminUserId);
	}

	@Override
	public List<AdminUserRoleRel> getAllByRoleId(Integer roleId) {
		return adminUserRoleRelDao.selectAllByRoleId(roleId);
	}

	@Override
	public void saveBatch(Collection<? extends Serializable> adminUserRoleRels) {
		adminUserRoleRelDao.insertBatch(adminUserRoleRels);
	}

	@Override
	public void removeByAdminUserId(Integer adminUserId) {
		adminUserRoleRelDao.deleteByAdminUserId(adminUserId);
	}

	@Override
	public void removeByRoleId(Integer roleId) {
		adminUserRoleRelDao.deleteByRoleId(roleId);
	}

}
