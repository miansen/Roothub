package cn.roothub.web.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cn.roothub.dto.PageDataBody;
import cn.roothub.entity.Role;
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
	
	@RequiresPermissions("role:list")
	@RequestMapping(value = "/list")
	public String list(@RequestParam(value = "p",defaultValue = "1") Integer p,Model model) {
		PageDataBody<Role> page = roleService.page(p, 25);
		model.addAttribute("page", page);
		model.addAttribute("p", p);
		return "admin/role/list";
	}
}
