package cn.roothub.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.roothub.dto.PageDataBody;
import cn.roothub.entity.AdminUser;
import cn.roothub.service.AdminUserService;

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
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(Model modle,@RequestParam(defaultValue = "1") Integer p) {
		PageDataBody<AdminUser> page = adminUserService.pageRoles(p, 25);
		modle.addAttribute("page", page);
		return "admin/admin_user/list";
	}
}
