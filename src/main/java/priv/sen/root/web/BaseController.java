package priv.sen.root.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import priv.sen.root.dto.ResponseDataBody;
import priv.sen.root.entity.RootUser;
import priv.sen.root.service.CollectService;
import priv.sen.root.service.RootNoticeService;
import priv.sen.root.service.RootReplyService;
import priv.sen.root.service.RootSectionService;
import priv.sen.root.service.RootTopicService;
import priv.sen.root.service.RootUserService;
import priv.sen.root.util.Base64Util;
import priv.sen.root.util.CookieAndSessionUtil;
import priv.sen.root.util.WebUtil;

@Controller
public class BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RootUserService rootUserService;
	@Autowired
	private RootTopicService rootTopicService;
	@Autowired
	private RootSectionService rootSectionService;
	@Autowired
	private RootNoticeService rootNoticeService;
	@Autowired
	private RootReplyService rootReplyService;
	@Autowired
	private CollectService collectDaoService;
	
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
	
	/**
	 * 未读通知的数量
	 * @param request
	 * @return
	 */
	public int getNotReadNotice(HttpServletRequest request) {
		int notReadNotice = 0;
		notReadNotice = rootNoticeService.countNotReadNotice(getUser(request).getUserName());
		return notReadNotice;
	}
	
	/**
	 * 发布的主题的数量
	 * @param request
	 * @return
	 */
	public int getCountTopicByUserName(HttpServletRequest request) {
		int countTopicByUserName = 0;
		countTopicByUserName = rootTopicService.countByUserName(getUser(request).getUserName());
		return countTopicByUserName;
	}
	
	/**
	 * 收藏话题的数量
	 * @param request
	 * @return
	 */
	public int getCountCollect(HttpServletRequest request) {
		int countCollect = 0;
		collectDaoService.count(getUser(request).getUserId());
		return countCollect;
	}
}
