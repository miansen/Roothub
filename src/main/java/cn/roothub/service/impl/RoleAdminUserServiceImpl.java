package cn.roothub.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.roothub.dao.RoleAdminUserRelDao;
import cn.roothub.entity.RoleAdminUserRel;
import cn.roothub.service.RoleAdminUserService;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-02-28
 */
@Service
public class RoleAdminUserServiceImpl implements RoleAdminUserService {

	private RoleAdminUserRelDao roleAdminUserRelDao;
	
	@Override
	public List<RoleAdminUserRel> getAllByAdminUserId(Integer adminUserId) {
		return roleAdminUserRelDao.selectAllByAdminUserId(adminUserId);
	}

	@Override
	public List<RoleAdminUserRel> getAllByRoleId(Integer roleId) {
		return roleAdminUserRelDao.selectAllByRoleId(roleId);
	}

	@Override
	public void saveBatch(Collection<? extends Serializable> roleAdminUserRels) {
		roleAdminUserRelDao.insertBatch(roleAdminUserRels);
	}

	@Override
	public void removeByAdminUserId(Integer adminUserId) {
		roleAdminUserRelDao.deleteByAdminUserId(adminUserId);
	}

	@Override
	public void removeByRoleId(Integer roleId) {
		roleAdminUserRelDao.deleteByRoleId(roleId);
	}

}
