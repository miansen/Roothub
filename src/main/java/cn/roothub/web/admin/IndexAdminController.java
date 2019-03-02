package cn.roothub.web.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
	
	// 后台首页
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/admin/index";
	}
	
	// 后台登录页面
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()) {
			return "redirect:/admin/index";
		}
		return "/admin/login";
	}
	
	// 后台登录处理
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username,String password,@RequestParam(defaultValue = "0") Boolean rememberMe,Model model) {
		try {
			// 添加用户认证信息
			Subject subject = SecurityUtils.getSubject();
			if(!subject.isAuthenticated()) {
				UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
				//进行验证，这里可以捕获异常，然后返回对应信息
				subject.login(token);
			}
		} catch (AuthenticationException e) {
			model.addAttribute("error", "用户名或密码错误");
			model.addAttribute("username", username);
	        return "/admin/login";
		}
		return "redirect:/admin/index";
	}

	// 出错页面
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {
		return "admin/error/error";
	}
}
