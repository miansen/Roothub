package cn.roothub.web.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import cn.roothub.entity.Permission;
import cn.roothub.entity.Role;
import cn.roothub.exception.ApiAssert;
import cn.roothub.service.PermissionService;
import cn.roothub.service.RoleService;

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
		PageDataBody<Role> page = roleService.page(p, 25);
		model.addAttribute("page", page);
		model.addAttribute("p", p);
		return "admin/role/list";
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
		return "admin/role/edit";
	}
	
	@RequiresPermissions("role:edit")
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> edit(Integer roleId,String roleName,Integer[] permissionIds){
		ApiAssert.notNull(roleId, "角色ID不能为空");
		ApiAssert.notNull(roleName, "角色名字不能为空");
		roleService.update(roleId, roleName, permissionIds);
		return new Result<>(true, "编辑成功");
	}
}
