package cn.roothub.web.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.Result;
import cn.roothub.entity.AdminUser;
import cn.roothub.entity.AdminUserRoleRel;
import cn.roothub.entity.Role;
import cn.roothub.exception.ApiAssert;
import cn.roothub.service.AdminUserRoleRelService;
import cn.roothub.service.AdminUserService;
import cn.roothub.service.RoleService;
import cn.roothub.util.SimpleHashUtil;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-02
 */
@Controller
@RequestMapping("/admin/admin_user")
public class AdminUserAdminController {

	@Autowired
	private AdminUserService adminUserService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AdminUserRoleRelService adminUserRoleRelService;
	
	@RequiresPermissions("admin_user:list")
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(Model modle,@RequestParam(defaultValue = "1") Integer p) {
		PageDataBody<AdminUser> page = adminUserService.pageRoles(p, 25);
		modle.addAttribute("page", page);
		modle.addAttribute("p", p);
		return "admin/admin_user/list";
	}
	
	@RequiresPermissions("admin_user:add")
	@RequestMapping(value = "/add",method = RequestMethod.GET)
	public String add(Model model) {
		PageDataBody<Role> page = roleService.page(1, 100);
		model.addAttribute("page",page);
		return "admin/admin_user/add";
	}
	
	/**
	 * 添加后台用户
	 * @param username
	 * @param password
	 * @param roleIds
	 * @return
	 */
	@RequiresPermissions("admin_user:add")
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> add(String username,String password,Integer[] roleIds){
		ApiAssert.notNull(username, "用户名不能为空");
		ApiAssert.notNull(password, "密码不能为空");
		AdminUser adminUser = adminUserService.getByName(username);
		ApiAssert.isNull(adminUser, "用户已存在");
		adminUser = new AdminUser();
		adminUser.setUsername(username);
		adminUser.setPassword(SimpleHashUtil.simpleHash("MD5", password, username, 1024).toString());
		adminUser.setCreateDate(new Date());
		// 保存用户
		AdminUser adminUser2 = adminUserService.save(adminUser);
		List<AdminUserRoleRel> adminUserRoleRels = new ArrayList<>();
		if(roleIds != null && roleIds.length > 0) {
			Arrays.asList(roleIds).forEach( roleId -> {
				AdminUserRoleRel adminUserRoleRel = new AdminUserRoleRel();
				adminUserRoleRel.setAdminUserId(adminUser2.getAdminUserId());
				adminUserRoleRel.setRoleId(roleId);
				adminUserRoleRel.setCreateDate(new Date());
				adminUserRoleRels.add(adminUserRoleRel);
			});
		}
		// 保存用户与角色的关联关系
		if(adminUserRoleRels != null && adminUserRoleRels.size() > 0) {
			adminUserRoleRelService.saveBatch(adminUserRoleRels);
		}
		return new Result(true, "添加用户成功");
	}
}
