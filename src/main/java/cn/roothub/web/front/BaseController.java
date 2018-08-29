package cn.roothub.web.front;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;

import cn.roothub.config.SiteConfig;
import cn.roothub.entity.RootUser;
import cn.roothub.service.CollectService;
import cn.roothub.service.RootNoticeService;
import cn.roothub.service.RootReplyService;
import cn.roothub.service.RootSectionService;
import cn.roothub.service.RootTopicService;
import cn.roothub.service.RootUserService;
import cn.roothub.util.Base64Util;
import cn.roothub.util.CookieAndSessionUtil;
import cn.roothub.util.JsonUtil;

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
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private SiteConfig siteConfig;

	public RootUser getUser(HttpServletRequest request) {

		String thirdId = CookieAndSessionUtil.getCookie(request, siteConfig.getCookieConfig().getName());

		try {
			if (thirdId != null) {
				thirdId = Base64Util.decode(thirdId);
				ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
				String redisUser = opsForValue.get(thirdId);
				if (redisUser != null)
					return JsonUtil.jsonToObject(redisUser, RootUser.class);
			} else {
				RootUser sessionUser = CookieAndSessionUtil.getSession(request, "user");
				return sessionUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

		/*
		 * RootUser user = null; if(str != null) { user = JsonUtil.jsonToObject(str,
		 * RootUser.class); logger.debug("redis生效了==="+str); return user; } user =
		 * CookieAndSessionUtil.getSession(request, "user"); if(user == null) { String
		 * cookie = CookieAndSessionUtil.getCookie(request, "user"); if(cookie != null)
		 * { user = rootUserService.findByName(Base64Util.decode(cookie)); return user;
		 * } } return user;
		 */
	}

	public String isLogin(HttpServletRequest request, String errorPage, String suesscePage) {
		RootUser user = getUser(request);
		if (user == null) {
			return errorPage;
		}
		return suesscePage;
	}

	/**
	 * 未读通知的数量
	 * 
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
	 * 
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
	 * 
	 * @param request
	 * @return
	 */
	public int getCountCollect(HttpServletRequest request) {
		int countCollect = 0;
		collectDaoService.count(getUser(request).getUserId());
		return countCollect;
	}
}
