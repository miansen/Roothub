package wang.miansen.roothub.modules.sys.controller.front;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wang.miansen.roothub.common.beans.BaseEntity;
import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.controller.BaseController;
import wang.miansen.roothub.common.controller.SessionController;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.util.ApiAssert;
import wang.miansen.roothub.common.util.Base64Util;
import wang.miansen.roothub.common.util.StringUtils;
import wang.miansen.roothub.config.SiteConfig;
import wang.miansen.roothub.modules.node.model.Node;
import wang.miansen.roothub.modules.node.model.NodeTab;
import wang.miansen.roothub.modules.node.service.NodeService;
import wang.miansen.roothub.modules.node.service.NodeTabService;
import wang.miansen.roothub.modules.reply.service.ReplyService;
import wang.miansen.roothub.modules.tag.model.Tag;
import wang.miansen.roothub.modules.user.model.User;
import wang.miansen.roothub.modules.user.service.UserService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.miansen.roothub.common.dto.UserExecution;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.modules.topic.model.Topic;
import wang.miansen.roothub.modules.tab.model.Tab;
import wang.miansen.roothub.modules.collect.service.CollectService;
import wang.miansen.roothub.modules.notice.service.NoticeService;
import wang.miansen.roothub.modules.topic.service.TopicService;
import wang.miansen.roothub.modules.topic.service.TabService;
import wang.miansen.roothub.common.util.CookieAndSessionUtil;
import wang.miansen.roothub.common.util.bcrypt.BCryptPasswordEncoder;

@Controller
public class IndexController extends SessionController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private NodeTabService nodeTabService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private CollectService collectDaoService;
	
	@Autowired
	private TabService tabService;
	@Autowired
	private SiteConfig siteConfig;
	@Autowired
	private BaseEntity baseEntity;
	@Autowired
	private NodeService nodeService;

	/**
	 * 首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	private String index(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "p", defaultValue = "1") Integer p,
			@RequestParam(value = "tab", defaultValue = "all") String tab) {
		Page<Topic> page = topicService.pageAllByTab(p, 25, tab);
		List<Tab> tabList = tabService.selectAll();
		List<Node> nodeList = nodeService.findAllByTab(tab, 0, 5);
		// 热门话题榜
		List<Topic> findHot = topicService.findHot(0, 10);
		// 今日等待回复的话题
		List<Topic> findTodayNoReply = topicService.findTodayNoReply(0, 10);
		// 最热标签
		Page<Tag> tag = topicService.findByTag(1, 10);
		List<Node> nodeList2 = nodeService.findAll(0, 10);
		// 注册会员的数量
		int countUserAll = userService.countUserAll();
		// 所有话题的数量
		int countAllTopic = topicService.countAllTopic(null);
		// 所有评论的数量
		int countAllReply = replyService.countAll();
		request.setAttribute("page", page);
		request.setAttribute("findHot", findHot);
		request.setAttribute("findTodayNoReply", findTodayNoReply);
		request.setAttribute("tabList", tabList);
		request.setAttribute("nodeList", nodeList);
		request.setAttribute("nodeList2", nodeList2);
		request.setAttribute("tab", tab);
		request.setAttribute("tag", tag);
		request.setAttribute("countUserAll", countUserAll);
		request.setAttribute("countAllTopic", countAllTopic);
		request.setAttribute("countAllReply", countAllReply);
		return "/default/front/index";
	}

	/**
	 * 注册页面
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	private String register(HttpServletRequest request) {
		return "/default/front/register";
	}

	/**
	 * 注册接口
	 * @param username
	 * @param password
	 * @param email
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	private Result<UserExecution> register(@RequestParam("username") String username,
                                           @RequestParam("password") String password, @RequestParam("email") String email,
                                           HttpServletRequest request) {
		ApiAssert.notEmpty(username, "请输入用户名");
		ApiAssert.notEmpty(password, "请输入密码");
		ApiAssert.notEmpty(email, "请输入邮箱");
		User user = userService.findByName(username);
		ApiAssert.isNull(user, "用户已存在");
		user = userService.findByEmail(email);
		ApiAssert.isNull(user, "邮箱已存在");
		UserExecution save = userService.createUser(username, password, email);
		CookieAndSessionUtil.setSession(request, "user", save.getUser());
		return new Result<UserExecution>(true, save);
	}

	/**
	 * 登录页面
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	private String login(HttpServletRequest request) {
		return "/default/front/login";
	}

	/**
	 * 登录接口
	 * 
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	private Result<User> login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response) {
		User user = userService.findByName(username);
		ApiAssert.notNull(user, "用户不存在");
		ApiAssert.isTrue(new BCryptPasswordEncoder().matches(password, user.getPassword()), "密码不正确");
		// 设置cookie
		/*CookieAndSessionUtil.setCookie(siteConfig.getCookieConfig().getName(),
				Base64Util.encode(user.getThirdAccessToken()), siteConfig.getCookieConfig().getMaxAge(),
				siteConfig.getCookieConfig().getPath(), siteConfig.getCookieConfig().isHttpOnly(), response);*/
		// 设置session
		CookieAndSessionUtil.setSession(request, "user", user);
		return new Result<User>(true, user);
	}

	/**
	 * 退出
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	private String logout(HttpServletRequest request, HttpServletResponse response) {
		// stringRedisTemplate.delete("user");
		CookieAndSessionUtil.removeSession(request, "user");
		/*CookieAndSessionUtil.removeCookie(response, siteConfig.getCookieConfig().getName(),
				siteConfig.getCookieConfig().getPath(), siteConfig.getCookieConfig().isHttpOnly());*/
		return "redirect:/";
	}

	/**
	 * 标签页
	 * 
	 * @param request
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/tags", method = RequestMethod.GET)
	private String tag(HttpServletRequest request, @RequestParam(value = "p", defaultValue = "1") Integer p) {
		Page<Tag> tag = topicService.findByTag(p, 50);
		request.setAttribute("tag", tag);
		return "/default/front/tag/list";
	}

	@RequestMapping(value = "/session", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, String> session(HttpServletRequest request) {
		User user = getUser(request);
		HashedMap map = new HashedMap();
		if (user != null) {
			map.put("success", true);
			map.put("user", user.getUserName());
			return map;
		} else {
			map.put("success", false);
			map.put("user", "");
			return map;
		}
	}

	/**
	 * 搜索
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	private String search(HttpServletRequest request, @RequestParam("s") String search,
			@RequestParam(value = "p", defaultValue = "1") Integer p) {
		if (search == null || search.equals("")) {
			return "search";
		}
		Page<Topic> pageLike = topicService.pageLike(p, 50, search);
		// BaseEntity baseEntity = new BaseEntity();
		// request.setAttribute("baseEntity", baseEntity);
		request.setAttribute("pageLike", pageLike);
		request.setAttribute("search", search);
		return "/default/front/search/view";
	}

	/**
	 * Top100积分榜
	 * 
	 * @return
	 */
	@RequestMapping(value = "/top100")
	private String top100() {
		return "/default/front/integral/list";
	}

	/**
	 * 关于
	 * 
	 * @return
	 */
	@RequestMapping(value = "/about")
	private String about() {
		return "/default/front/common/about";
	}

	/**
	 * faq
	 * 
	 * @return
	 */
	@RequestMapping(value = "/faq")
	private String faq() {
		return "/default/front/common/faq";
	}

	/**
	 * api
	 * 
	 * @return
	 */
	@RequestMapping(value = "/api")
	private String api() {
		return "/default/front/common/api";
	}

	/**
	 * mission
	 * 
	 * @return
	 */
	@RequestMapping(value = "/mission")
	private String mission() {
		return "/default/front/common/mission";
	}

	/**
	 * advertise
	 * 
	 * @return
	 */
	@RequestMapping(value = "/advertise")
	private String advertise() {
		return "/default/front/common/advertise";
	}

	/**
	 * 反馈建议
	 * 
	 * @return
	 */
	@RequestMapping(value = "/feedback")
	private String feedback() {
		return "/default/front/common/feedback";
	}

	@RequestMapping(value = "/feedback/add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	private Map<String,Object> feedbackAdd(String info) {
		Map<String, Object> redisMap = new HashedMap();
		Map<String, Object> returnMap = new HashedMap();
		List<String> list = new ArrayList<>();
		if (info == null) {
			returnMap.put("success", false);
			returnMap.put("msg", "建议不能为空");
			return returnMap;
		} else {
			list.add("感谢您宝贵的建议!");
			redisMap.put(StringUtils.getUUID(), info);
			returnMap.put("success", true);
			returnMap.put("msg", list);
			return returnMap;
		}
	}

	/**
	 * 这是测试代码，与项目无关
	 * excel
	 * 
	 * @return
	 */
	@RequestMapping(value = "/excel")
	private String excel(HttpServletRequest request) {
		List<Topic> row1 = topicService.findAll();// 全部话题
		List<Tab> row2 = tabService.selectAll();// 父板块
		List<NodeTab> row3 = nodeTabService.findAll();// 子版块
		request.setAttribute("row1", row1);
		request.setAttribute("row2", row2);
		request.setAttribute("row3", row3);
		return "/default/front/common/excel";
	}

}
