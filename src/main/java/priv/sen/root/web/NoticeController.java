package priv.sen.root.web;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import priv.sen.root.dto.PageDataBody;
import priv.sen.root.entity.RootNotice;
import priv.sen.root.entity.RootUser;
import priv.sen.root.service.RootNoticeService;
import priv.sen.root.service.RootUserService;
import priv.sen.root.util.Base64Util;
import priv.sen.root.util.CookieAndSessionUtil;

@Controller
public class NoticeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RootNoticeService rootNoticeService;
	@Autowired
	private RootUserService rootUserService;
	
	/**
	 * 通知列表
	 * @param request
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/notification/list", method = RequestMethod.GET)
	private String noticeList(HttpServletRequest request,@RequestParam(value = "p", defaultValue = "1") Integer p) {
		RootUser user = null;
		RootUser session = CookieAndSessionUtil.getSession(request, "user");
		if(session != null) {
			user = session;
		}
		if(user == null) {
			String cookie = CookieAndSessionUtil.getCookie(request, "user");
			if(cookie != null) {
	    		user = rootUserService.findByName(Base64Util.decode(cookie));
	    	}
		}
		if(user == null) {
			return "error-page/500";
		}
		int countByAuthor = rootNoticeService.countByAuthor(user.getUserName());//统计所有通知的数量
		PageDataBody<RootNotice> page = rootNoticeService.pageByAuthor(p, 20, user.getUserName());//查询所有通知
		rootNoticeService.updateIsRead(user.getUserName());//将通知都置为已读
		request.setAttribute("countByAuthor", countByAuthor);
		request.setAttribute("page", page);
		return "notification/list";
	}
}
