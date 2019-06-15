package cn.roothub.bbs.module.notice.controller.front;

import javax.servlet.http.HttpServletRequest;

import cn.roothub.bbs.common.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.roothub.bbs.core.base.PageDataBody;
import cn.roothub.bbs.module.notice.model.Notice;
import cn.roothub.bbs.module.user.model.User;
import cn.roothub.bbs.module.collect.service.CollectService;
import cn.roothub.bbs.module.notice.service.NoticeService;
import cn.roothub.bbs.module.topic.service.TopicService;
import cn.roothub.bbs.module.user.service.UserService;

@Controller
public class NoticeController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NoticeService rootNoticeService;
	@Autowired
	private UserService rootUserService;
	@Autowired
	private TopicService rootTopicService;
	@Autowired
	private CollectService collectDaoService;
	
	/**
	 * 通知列表
	 * @param request
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/notification/list", method = RequestMethod.GET)
	private String noticeList(HttpServletRequest request,@RequestParam(value = "p", defaultValue = "1") Integer p) {
		/*RootUser user = null;
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
		}*/
		User user = getUser(request);
		if(user == null) {
			return "error-page/500";
		}
		int countByAuthor = rootNoticeService.countByAuthor(user.getUserName());//统计所有通知的数量
		int notReadNotice = rootNoticeService.countNotReadNotice(user.getUserName());//统计未读通知的数量
		int countTopicByUserName = rootTopicService.countByUserName(user.getUserName());//用户发布的主题的数量
		int countCollect = collectDaoService.count(user.getUserId());//用户收藏话题的数量
		PageDataBody<Notice> page = rootNoticeService.pageByAuthor(p, 20, user.getUserName());//查询所有通知
		rootNoticeService.updateIsRead(user.getUserName());//将通知都置为已读
		request.setAttribute("countByAuthor", countByAuthor);
		request.setAttribute("notReadNotice", notReadNotice);
		request.setAttribute("page", page);
		request.setAttribute("user", user);
		request.setAttribute("countTopicByUserName", countTopicByUserName);
		request.setAttribute("countCollect", countCollect);
		return "/default/front/notice/list";
	}
}
