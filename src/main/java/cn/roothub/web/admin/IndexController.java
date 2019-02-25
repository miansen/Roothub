package cn.roothub.web.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author miansen.wang
 * @date 2019年2月25日 下午8:51:25
 */
@Controller
// @RequestMapping("/admin")
public class IndexController {
	
	@RequestMapping(value = "/admin/index",method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	/**
     * 注册页面
     */
    @RequestMapping(value = "/admin/register", method = RequestMethod.GET)
    private String register(HttpServletRequest request) {
            return "register";   
    }
}
