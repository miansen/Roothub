package cn.roothub.web.admin;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.Result;
import cn.roothub.entity.AdminUser;
import cn.roothub.entity.Role;
import cn.roothub.exception.ApiAssert;
import cn.roothub.service.AdminUserService;
import cn.roothub.service.RoleService;

/**
 * <p>
 * </p>
 * 
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

	@RequiresPermissions("admin_user:list")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model modle, @RequestParam(defaultValue = "1") Integer p) {
		PageDataBody<AdminUser> page = adminUserService.pageRoles(p, 25);
		modle.addAttribute("page", page);
		modle.addAttribute("p", p);
		return "admin/admin_user/list";
	}

	@RequiresPermissions("admin_user:add")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		PageDataBody<Role> page = roleService.page(1, 100);
		model.addAttribute("page", page);
		return "admin/admin_user/add";
	}

	/**
	 * 添加后台用户
	 * 
	 * @param username
	 * @param password
	 * @param roleIds
	 * @return
	 */
	@RequiresPermissions("admin_user:add")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> add(String username, String password, Integer[] roleIds) {
		ApiAssert.notNull(username, "用户名不能为空");
		ApiAssert.notNull(password, "密码不能为空");
		adminUserService.save(username, password, roleIds);
		return new Result(true, "添加用户成功");
	}

	@RequiresPermissions("admin_user:edit")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, Integer id) {
		AdminUser adminUser = adminUserService.getById(id);
		// 所有的角色列表
		List<Role> roles = roleService.page(1, 100).getList();
		// 用户已有的角色列表
		List<Role> adminUserRoles = roleService.getByAdminUserId(id, null, null);
		model.addAttribute("adminUser", adminUser);
		model.addAttribute("roles", roles);
		model.addAttribute("adminUserRoles", JSON.toJSONString(adminUserRoles));
		return "admin/admin_user/edit";
	}

	@RequiresPermissions("admin_user:edit")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> edit(Integer id, String username, String password, Integer[] roleIds) {
		ApiAssert.notNull(username, "用户名不能为空");
		// 更新用户
		adminUserService.update(id, username, password, roleIds);
		return new Result(true, "编辑用户成功");
	}

	@RequiresPermissions("admin_user:delete")
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Integer id) {
		adminUserService.removeById(id);
		return "redirect: /admin/admin_user/list";
	}

}
