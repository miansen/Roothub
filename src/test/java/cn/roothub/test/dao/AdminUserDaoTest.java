package cn.roothub.test.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dao.AdminUserDao;
import cn.roothub.entity.AdminUser;
import cn.roothub.entity.AdminUserRoleRel;
import cn.roothub.entity.Role;
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
		// log.debug(list.toString());
		// list.stream().map(AdminUser::getAdminUsername).conllect(Collectors.toList());
		/*for(AdminUser a : list) {
			System.out.println(a);
		}*/
		// list.forEach(a -> System.out.println(a));
		// list.forEach(System.out::println);
		List<String> collect = list.stream().map(AdminUser::getUsername).collect(Collectors.toList());
		System.out.println(collect);
		collect.forEach(System.out::println);
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
	public void insert() {
		AdminUser adminUser = new AdminUser();
		adminUser.setUsername("test");
		adminUser.setPassword("123");
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
	public void streamTest() {
		List<String> arrs  = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		System.out.println(arrs);
		
		String[] strings = {"abc", "", "bc", "efg", "abcd","", "jkl"};
		System.out.println(strings[0]);
		
		List<String> asList = Arrays.asList(strings);
		System.out.println(asList);
		
		int[] ints = new int[3];
		ints[0] = 1;
		ints[1] = 2;
		ints[2] = 3;
		// ints[3] = 4;
		System.out.println(ints[0]);
		List<int[]> asList2 = Arrays.asList(ints);
		System.out.println(asList2.toString());
		asList2.forEach(a -> System.out.println(a));
		
		int[] ints2 = {1,2,3};
		List<int[]> asList3 = Arrays.asList(ints2);
		System.out.println(asList3.get(0));
		
	}
	
	@Test
	public void streamTest02() {
		List<String> asList = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		List<String> list = asList.stream().filter(str -> !str.isEmpty()).collect(Collectors.toList());
		// System.out.println(list);
		asList.forEach(System.out::println);
	}
	
	@Test
	public void test() {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		System.out.println(list);
		for(int i = 0;i < list.size();i++) {
			System.out.println(list.get(i));
		}
		String[] str = new String[3];
		str[0] = "a";
		str[1] = "b";
		str[2] = "c";
		System.out.println(str);
		String string = str.toString();
		System.out.println(string);
		for(int i = 0;i < str.length;i++) {
			System.out.println(str[i]);
		}
	}
	
	public void Test02() {
		AdminUserRoleRel adminUserRoleRel = new AdminUserRoleRel();
		
		Collection<? extends Serializable> collection = new ArrayList<>();
		//collection.add(adminUserRoleRel);
	}
	
}
