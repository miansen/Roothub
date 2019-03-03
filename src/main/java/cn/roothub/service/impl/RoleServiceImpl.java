package cn.roothub.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.roothub.dao.RoleDao;
import cn.roothub.dto.PageDataBody;
import cn.roothub.entity.Role;
import cn.roothub.service.RoleService;

/**
 * @author miansen.wang
 * @date 2019年2月27日 下午9:11:21
 */
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public Role getById(Integer id) {
		return roleDao.selectById(id);
	}

	@Override
	public PageDataBody<Role> page(Integer pageNumber, Integer pageSize) {
		List<Role> list = roleDao.selectAll((pageNumber - 1) * pageSize, pageSize);
		int countAll = this.countAll();
		return new PageDataBody<>(list, pageNumber, pageSize, countAll);
	}

	@Override
	public List<Role> getByAdminUserId(Integer adminUserId, Integer pageNumber, Integer pageSize) {
		return roleDao.selectAllByAdminUserId(adminUserId, pageNumber, pageSize);
	}

	@Override
	public void save(Role role) {
		roleDao.insert(role);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public void removeById(Integer id) {
		roleDao.deleteById(id);
	}

	@Override
	public int countAll() {
		return roleDao.countAll();
	}
	
}
