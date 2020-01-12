package wang.miansen.roothub.modules.security.controller.admin;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.util.ApiAssert;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import wang.miansen.roothub.modules.security.model.Role;
import wang.miansen.roothub.modules.security.service.PermissionService;
import wang.miansen.roothub.modules.security.service.RoleService;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-04
 */
@Controller
@RequestMapping("/admin/role")
public class RoleAdminController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	@RequiresPermissions("role:list")
	@RequestMapping(value = "/list")
	public String list(@RequestParam(value = "p",defaultValue = "1") Integer p,Model model) {
		Page<Role> page = roleService.page(p, 25);
		model.addAttribute("page", page);
		model.addAttribute("p", p);
		return "/default/admin/role/list";
	}
	
	@RequiresPermissions("role:add")
	@RequestMapping(value = "/add",method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("permissionMap", permissionService.permissionMap());
		return "/default/admin/role/add";
	}
	
	@RequiresPermissions("role:add")
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> add(String roleName, Integer[] permissionIds) {
		ApiAssert.notNull(roleName, "角色名字不能为空");
		roleService.save(roleName, permissionIds);
		return new Result(true, "添加成功");
	}
	
	/**
	 * 
	 * @param id: 角色ID
	 * @param model
	 * @return
	 */
	@RequiresPermissions("role:edit")
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id",defaultValue = "1") Integer id,Model model) {
		model.addAttribute("role", roleService.getById(id));
		model.addAttribute("roleHaspermissions", JSON.toJSONString(permissionService.getAllByRoleId(id)));
		model.addAttribute("permissionMap", permissionService.permissionMap());
		return "/default/admin/role/edit";
	}
	
	@RequiresPermissions("role:edit")
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> edit(Integer roleId,String roleName,Integer[] permissionIds){
		ApiAssert.notNull(roleId, "角色ID不能为空");
		ApiAssert.notNull(roleName, "角色名字不能为空");
		roleService.update(roleId, roleName, permissionIds);
		return new Result(true, "编辑成功");
	}
	
	@RequiresPermissions("role:delete")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	public String delete(Integer id) {
		ApiAssert.notNull(id, "角色ID不能为空");
		roleService.removeById(id);
		return "redirect: /admin/role/list";
	}
}
