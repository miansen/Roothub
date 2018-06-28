package priv.sen.root.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import priv.sen.root.dto.ResponseDataBody;
import priv.sen.root.entity.RootUser;
import priv.sen.root.service.RootUserService;
import priv.sen.root.util.Base64Util;
import priv.sen.root.util.CookieAndSessionUtil;
import priv.sen.root.util.WebUtil;

@Controller
public class BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RootUserService rootUserService;
	
	public RootUser getUser(HttpServletRequest request) {
		RootUser user = new RootUser();
		user = CookieAndSessionUtil.getSession(request, "user");
		if(user == null) {
			String cookie = CookieAndSessionUtil.getCookie(request, "user");
			if(cookie != null) {
				user = rootUserService.findByName(Base64Util.decode(cookie));
				return user;
			}
		}
		return user;
	}
	
	public String isLogin(HttpServletRequest request,String errorPage,String suesscePage) {
		RootUser user = getUser(request);
		if(user == null ) {
			return errorPage;
		}
		return suesscePage;
	}
}
