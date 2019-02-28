package cn.roothub.test.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dao.RoleAdminUserRelDao;
import cn.roothub.entity.RoleAdminUserRel;
import cn.roothub.test.base.BaseTest;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-02-28
 */
public class RoleAdminUserRelDaoTest extends BaseTest {

	@Autowired
	private RoleAdminUserRelDao roleAdminUserRelDao;
	
	@Test
	public void test01() {
		List<RoleAdminUserRel> selectAllByAdminUserId = roleAdminUserRelDao.selectAllByAdminUserId(1);
		System.out.println(selectAllByAdminUserId);
	}
	
	@Test
	public void test02() {
		List<RoleAdminUserRel> list = new ArrayList<>();
		RoleAdminUserRel roleAdminUserRel = new RoleAdminUserRel();
		roleAdminUserRel.setRoleId(1);
		roleAdminUserRel.setAdminUserId(3);
		roleAdminUserRel.setCreateDate(new Date());
		
		RoleAdminUserRel roleAdminUserRel2 = new RoleAdminUserRel();
		roleAdminUserRel2.setRoleId(2);
		roleAdminUserRel2.setAdminUserId(3);
		roleAdminUserRel2.setCreateDate(new Date());
		
		list.add(roleAdminUserRel);
		list.add(roleAdminUserRel2);
		list.forEach(a -> System.out.println(a));
		
		int insertByRoleAdminUserRels = roleAdminUserRelDao.insertBatch(list);
		System.out.println(insertByRoleAdminUserRels);
	}
	
	@Test
	public void test03() {
		int i = roleAdminUserRelDao.deleteByAdminUserId(3);
		System.out.println(i);
	}
}
