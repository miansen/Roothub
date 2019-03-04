package cn.roothub.test.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dao.RoleDao;
import cn.roothub.entity.Role;
import cn.roothub.test.base.BaseTest;

/**
 * @author miansen.wang
 * @date 2019年2月27日 下午9:08:59
 */
public class RoleDaoTest extends BaseTest {
	
	@Autowired
	private RoleDao roleDao;
	
	@Test
	public void test01() {
		List<Role> list = roleDao.selectAllByAdminUserId(1, 0, 10);
		System.out.println(list);
		list.forEach(role -> System.out.println(role));
	}
	
	@Test
	public void test02() {
		Role role = new Role();
		role.setRoleName("testaasasa");
		role.setCreateDate(new Date());
		// 保存角色
		roleDao.insert(role);
		System.out.println(role);
	}
}
