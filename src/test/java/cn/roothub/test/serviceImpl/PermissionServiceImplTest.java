package cn.roothub.test.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.entity.Permission;
import cn.roothub.entity.Role;
import cn.roothub.service.PermissionService;
import cn.roothub.test.base.BaseTest;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-01
 */
public class PermissionServiceImplTest extends BaseTest {

	@Autowired
	private PermissionService permissionService;
	
	@Test
	public void test01() {
		List<Permission> list = permissionService.getAllByPid(0);
		list.forEach(System.out::println);
	}
	
	@Test
	public void test02() {
		List<Role> list = new ArrayList<>();
		Role role = new Role();
		role.setRoleId(1);
		role.setRoleName("超级管理员");
		role.setCreateDate(new Date());
		
		Role role2 = new Role();
		role2.setRoleId(3);
		role2.setRoleName("审核员");
		role2.setCreateDate(new Date());
		
		list.add(role);
		list.add(role2);
		
		List<Permission> list2 = permissionService.getBatchByRoleList(list);
		list2.forEach(System.out::println);
	}
}
