package cn.roothub.web.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.roothub.dto.Result;
import cn.roothub.entity.User;
import cn.roothub.service.UserService;
import cn.roothub.util.StringUtil;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-05-01
 */
@Controller
@RequestMapping("/admin/user")
public class UserAdminController {

	@Autowired
	private UserService userService;
	
	@RequiresPermissions("user:list")
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(String username, String email, @RequestParam(value = "p",defaultValue = "1") Integer p, Model model) {
		if(StringUtils.isEmpty(username)) username = null;
		if(StringUtils.isEmpty(email)) email = null;
		model.addAttribute("username", username);
		model.addAttribute("email", email);
		model.addAttribute("p", p);
		model.addAttribute("page", userService.pageForAdmin(username, email, p, 25));
		return "/admin/user/list";
	}
	
	@RequiresPermissions("user:edit")
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public String edit(Integer id, Model model) {
		model.addAttribute("user", userService.findById(id));
		return "/admin/user/edit";
	}
	
	@RequiresPermissions("user:edit")
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> edit(User user){
		System.out.println(user);
		return new Result<>(true, "编辑成功");
	}
	
	@RequestMapping(value = "/refreshToken",method = RequestMethod.GET)
	@ResponseBody
	public Result<String> refreshToken(){
		return new Result<>(true, StringUtil.getUUID());
	} 
}
