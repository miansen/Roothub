package cn.roothub.test.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dao.AdminUserRoleRelDao;
import cn.roothub.entity.AdminUserRoleRel;
import cn.roothub.test.base.BaseTest;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-02-28
 */
public class AdminUserRelRoleDaoTest extends BaseTest {

	@Autowired
	private AdminUserRoleRelDao adminUserRoleRelDao;
	
	@Test
	public void test01() {
		List<AdminUserRoleRel> selectAllByAdminUserId = adminUserRoleRelDao.selectAllByAdminUserId(3);
		System.out.println(selectAllByAdminUserId);
		selectAllByAdminUserId.forEach(System.out::println);
	}
	
	@Test
	public void test02() {
		List<AdminUserRoleRel> list = new ArrayList<>();
		AdminUserRoleRel roleAdminUserRel = new AdminUserRoleRel();
		roleAdminUserRel.setAdminUserId(3);
		roleAdminUserRel.setRoleId(1);
		roleAdminUserRel.setCreateDate(new Date());
		
		AdminUserRoleRel roleAdminUserRel2 = new AdminUserRoleRel();
		roleAdminUserRel2.setAdminUserId(3);
		roleAdminUserRel2.setRoleId(2);
		roleAdminUserRel2.setCreateDate(new Date());
		
		list.add(roleAdminUserRel);
		list.add(roleAdminUserRel2);
		// list.forEach(a -> System.out.println(a));
		
		int insertByRoleAdminUserRels = adminUserRoleRelDao.insertBatch(list);
		System.out.println(insertByRoleAdminUserRels);
	}
	
	@Test
	public void test03() {
		int i = adminUserRoleRelDao.deleteByAdminUserId(3);
		System.out.println(i);
	}
}
