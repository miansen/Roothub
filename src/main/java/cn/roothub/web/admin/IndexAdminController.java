package cn.roothub.web.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.Result;
import cn.roothub.entity.AdminUser;
import cn.roothub.service.AdminUserService;

/**
 * @author miansen.wang
 * @date 2019年2月25日 下午8:51:25
 */
@Controller
@RequestMapping("/admin")
public class IndexAdminController {

	@Autowired
	private AdminUserService adminUserService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@ResponseBody
	public Result<PageDataBody<AdminUser>> index() {
		PageDataBody<AdminUser> pageRoles = adminUserService.pageRoles(1, 20);
		return new Result<PageDataBody<AdminUser>>(true, pageRoles);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "admin/login";
	}

	// 出错页面
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {
		return "admin/error/error";
	}
}
