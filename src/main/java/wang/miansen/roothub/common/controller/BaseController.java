package wang.miansen.roothub.common.controller;

import javax.servlet.http.HttpServletRequest;

import wang.miansen.roothub.common.util.Base64Util;
import wang.miansen.roothub.common.util.CookieAndSessionUtil;
import wang.miansen.roothub.common.util.JsonUtil;
import wang.miansen.roothub.config.SiteConfig;
import wang.miansen.roothub.modules.collect.service.CollectService;
import wang.miansen.roothub.modules.node.service.NodeTabService;
import wang.miansen.roothub.modules.notice.service.NoticeService;
import wang.miansen.roothub.modules.reply.service.ReplyService;
import wang.miansen.roothub.modules.topic.service.TopicService;
import wang.miansen.roothub.modules.user.model.User;
import wang.miansen.roothub.modules.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import wang.miansen.roothub.third.service.RedisService;

@Controller
public class BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService rootUserService;
	@Autowired
	private TopicService rootTopicService;
	@Autowired
	private NodeTabService rootSectionService;
	@Autowired
	private NoticeService rootNoticeService;
	@Autowired
	private ReplyService rootReplyService;
	@Autowired
	private CollectService collectDaoService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private SiteConfig siteConfig;
	@Autowired
	private RedisService redisService;

	/**
	 * 获取登录用户的信息，先从 Redis 里面取，如果取不到，再从 seesion 里面取
	 * 
	 * @param request
	 * @return
	 */
	public User getUser(HttpServletRequest request) {
		return CookieAndSessionUtil.getSession(request, "user");
		/*String token = CookieAndSessionUtil.getCookie(request, siteConfig.getCookieConfig().getName());
		// ValueOperations<String, String> opsForValue =
		// stringRedisTemplate.opsForValue();

		// User sessionUser = CookieAndSessionUtil.getSession(request, "user");
		try {
			if (token != null) {
				token = Base64Util.decode(token);
				// String redisUser = opsForValue.get(token);
				String redisUser = redisService.getString(token);
				if (redisUser != null) {
					return JsonUtil.jsonToObject(redisUser, User.class);
				} else {
					return CookieAndSessionUtil.getSession(request, "user");
				}
			} else {
				return CookieAndSessionUtil.getSession(request, "user");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}*/
	}

	public String isLogin(HttpServletRequest request, String errorPage, String suesscePage) {
		User user = getUser(request);
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
