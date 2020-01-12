package wang.miansen.roothub.modules.security.controller.admin;

import java.util.List;
import java.util.Map;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.util.ApiAssert;
import wang.miansen.roothub.modules.security.model.AdminUser;
import wang.miansen.roothub.modules.security.model.Role;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;

import wang.miansen.roothub.modules.security.service.AdminUserService;
import wang.miansen.roothub.modules.security.service.RoleService;

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
		Page<AdminUser> page = adminUserService.pageRoles(p, 25);
		modle.addAttribute("page", page);
		modle.addAttribute("p", p);
		return "/default/admin/admin_user/list";
	}

	@RequiresPermissions("admin_user:add")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		Page<Role> page = roleService.page(1, 100);
		model.addAttribute("page", page);
		return "/default/admin/admin_user/add";
	}

	/**
	 * 添加后台用户
	 * @param username:用户名
	 * @param password:密码
	 * @param avatar:头像
	 * @param roleIds:角色
	 * @return
	 */
	@RequiresPermissions("admin_user:add")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> add(String username, String password, String avatar, Integer[] roleIds) {
		ApiAssert.notNull(username, "用户名不能为空");
		ApiAssert.notNull(password, "密码不能为空");
		if(StringUtils.isEmpty(avatar)) avatar = "/resources/images/default-avatar.jpg";
		adminUserService.save(username, password, avatar, roleIds);
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
		return "/default/admin/admin_user/edit";
	}

	/**
	 * 编辑后台用户
	 * 如果 password 为空，则不更新 password
	 * 如果 avatar 为空，则设置 avatar 为默认值
	 * 如果修改的是当前登录用户，则强制重新登录
	 * @param id:后台用户ID
	 * @param password:密码
	 * @param avatar:头像
	 * @param roleIds:角色
	 * @return
	 */
	@RequiresPermissions("admin_user:edit")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Result<Map<String, Object>> edit(Integer id, String password, String avatar, Integer[] roleIds) {
		// ApiAssert.notNull(username, "用户名不能为空");
		if(StringUtils.isEmpty(avatar)) avatar = "/resources/images/default-avatar.jpg";
		// 更新用户
		Map<String, Object> map = adminUserService.update(id, password, avatar, roleIds);
		return new Result<Map<String,Object>>(true, map);
	}

	@RequiresPermissions("admin_user:delete")
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Integer id) {
		adminUserService.removeById(id);
		return "redirect: /admin/admin_user/list";
	}

}
