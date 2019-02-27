package cn.roothub.test.serviceImpl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dto.PageDataBody;
import cn.roothub.entity.AdminUser;
import cn.roothub.service.AdminUserService;
import cn.roothub.test.base.BaseTest;

/**
 * @author miansen.wang
 * @date 2019年2月27日 下午9:36:05
 */
public class AdminUserServiceImplTest extends BaseTest {
	
	@Autowired
	private AdminUserService adminUserService;
	
	@Test
	public void test01() {
		PageDataBody<AdminUser> pageRoles = adminUserService.pageRoles(1, 20);
		System.out.println(pageRoles);
	}
}
