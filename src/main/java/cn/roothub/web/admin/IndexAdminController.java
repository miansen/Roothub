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
@RequestMapping("/admin")
public class IndexAdminController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "admin/index";
	}

}
