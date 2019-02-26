package cn.roothub.test.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dao.AdminUserDao;
import cn.roothub.entity.AdminUser;
import cn.roothub.test.base.BaseTest;

/**
 * @author miansen.wang
 * @date 2019年2月26日 下午7:01:34
 */
public class AdminUserDaoTest extends BaseTest{
	
	private Logger log = LoggerFactory.getLogger(AdminUserDaoTest.class);
	
	@Autowired
	private AdminUserDao adminUserDao;
	
	@Test
	public void selectAll() {
		List<AdminUser> list = adminUserDao.selectAll(0, 10);
		log.debug(list.toString());
	}
	
	@Test
	public void selectByName() {
		AdminUser adminUser = adminUserDao.selectByName("admin");
		log.debug(adminUser.toString());
	}
	
	@Test
	public void selectById() {
		AdminUser adminUser = adminUserDao.selectById(1);
		log.debug(adminUser.toString());
	}
	
	@Test
	public void selectByRoleId() {
		List<AdminUser> list = adminUserDao.selectByRoleId(1, 0, 10);
		log.debug(list.toString());
	}
	
	@Test
	public void insert() {
		AdminUser adminUser = new AdminUser();
		adminUser.setUsername("test");
		adminUser.setPassword("123");
		adminUser.setRoleId(1);
		adminUser.setCreateDate(new Date());
		int i = adminUserDao.insert(adminUser);
		System.out.println(i);
	}
	
	@Test
	public void update() {
		AdminUser adminUser = new AdminUser();
		adminUser.setAdminUserId(2);
		adminUser.setUsername("test");
		adminUser.setPassword("1234");
		adminUser.setRoleId(1);
		adminUser.setCreateDate(new Date());
		adminUser.setUpdateDate(new Date());
		int i = adminUserDao.update(adminUser);
		System.out.println(i);
	}
	
	@Test
	public void deleteById() {
		int i = adminUserDao.deleteById(2);
		System.out.println(i);
	}
	
	@Test
	public void countAll() {
		int i = adminUserDao.countAll();
		System.out.println(i);
	}
	
	@Test
	public void countByRoleId() {
		int i = adminUserDao.countByRoleId(1);
		System.out.println(i);
	}
}
