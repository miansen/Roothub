package cn.roothub.bbs.module.sys.controller.front;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.roothub.bbs.common.controller.BaseController;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.roothub.bbs.core.base.BaseEntity;
import cn.roothub.bbs.config.SiteConfig;
import cn.roothub.bbs.core.base.PageDataBody;
import cn.roothub.bbs.core.base.Result;
import cn.roothub.bbs.common.dto.UserExecution;
import cn.roothub.bbs.module.node.model.Node;
import cn.roothub.bbs.module.node.model.NodeTab;
import cn.roothub.bbs.module.topic.model.Topic;
import cn.roothub.bbs.module.user.model.User;
import cn.roothub.bbs.core.exception.ApiAssert;
import cn.roothub.bbs.module.tab.model.Tab;
import cn.roothub.bbs.module.tag.model.Tag;
import cn.roothub.bbs.module.collect.service.CollectService;
import cn.roothub.bbs.module.node.service.NodeService;
import cn.roothub.bbs.module.notice.service.NoticeService;
import cn.roothub.bbs.module.reply.service.ReplyService;
import cn.roothub.bbs.module.node.service.NodeTabService;
import cn.roothub.bbs.module.topic.service.TopicService;
import cn.roothub.bbs.module.user.service.UserService;
import cn.roothub.bbs.module.topic.service.TabService;
import cn.roothub.bbs.common.util.Base64Util;
import cn.roothub.bbs.common.util.CookieAndSessionUtil;
import cn.roothub.bbs.common.util.bcrypt.BCryptPasswordEncoder;
import cn.roothub.bbs.common.util.StringUtil;

@Controller
public class IndexController extends BaseController {

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
	private RedisTemplate<String, List<String>> redisTemplate;
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
		PageDataBody<Topic> page = topicService.pageAllByTab(p, 25, tab);
		List<Tab> tabList = tabService.selectAll();
		List<Node> nodeList = nodeService.findAllByTab(tab, 0, 5);
		// 热门话题榜
		List<Topic> findHot = topicService.findHot(0, 10);
		// 今日等待回复的话题
		List<Topic> findTodayNoReply = topicService.findTodayNoReply(0, 10);
		// 最热标签
		PageDataBody<Tag> tag = topicService.findByTag(1, 10);
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
		CookieAndSessionUtil.setCookie(siteConfig.getCookieConfig().getName(),
				Base64Util.encode(user.getThirdAccessToken()), siteConfig.getCookieConfig().getMaxAge(),
				siteConfig.getCookieConfig().getPath(), siteConfig.getCookieConfig().isHttpOnly(), response);
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
		CookieAndSessionUtil.removeCookie(response, siteConfig.getCookieConfig().getName(),
				siteConfig.getCookieConfig().getPath(), siteConfig.getCookieConfig().isHttpOnly());
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
		PageDataBody<Tag> tag = topicService.findByTag(p, 50);
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
		PageDataBody<Topic> pageLike = topicService.pageLike(p, 50, search);
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
		HashOperations<String, String, Object> opsForHash = redisTemplate.opsForHash();
		if (info == null) {
			returnMap.put("success", false);
			returnMap.put("msg", "建议不能为空");
			return returnMap;
		} else {
			list.add("感谢您宝贵的建议!");
			redisMap.put(StringUtil.getUUID(), info);
			opsForHash.putAll("feedback", redisMap);
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

	/**
	 * 这是测试代码，与项目无关
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/excel/download")
	private void excel02(HttpServletResponse response) throws Exception {
		List<Topic> row1 = topicService.findAll();
		// List<RootTopic> row2 = rootTopicService.findHot(1, 50);
		List<Tab> row2 = tabService.selectAll();
		List<NodeTab> row3 = nodeTabService.findAll();
		List<Topic> rows1 = CollUtil.newArrayList(row1);
		List<Tab> rows2 = CollUtil.newArrayList(row2);
		List<NodeTab> rows3 = CollUtil.newArrayList(row3);
		// List<List<? extends Object>> rows3 = CollUtil.newArrayList(row1,row2,row3);
		ExcelWriter writer = ExcelUtil.getWriter("d:/writeTest04.xlsx", "话题");
		writer.addHeaderAlias("topicId", "话题标识");
		writer.addHeaderAlias("ptab", "父板块标识");
		writer.addHeaderAlias("tab", "子版块标识");
		writer.addHeaderAlias("title", "话题标题");
		writer.addHeaderAlias("tag", "话题内容标签");
		writer.addHeaderAlias("content", "话题内容");
		writer.addHeaderAlias("createDate", "创建时间");
		writer.addHeaderAlias("updateDate", "更新时间");
		writer.addHeaderAlias("lastReplyTime", "最后回复话题时间");
		writer.addHeaderAlias("lastReplyAuthor", "最后回复话题的用户");
		writer.addHeaderAlias("viewCount", "浏览量");
		writer.addHeaderAlias("author", "话题作者");
		writer.addHeaderAlias("top", "1置顶 0默认");
		writer.addHeaderAlias("good", "1精华 0默认");
		writer.addHeaderAlias("showStatus", "1显示 0不显示");
		writer.addHeaderAlias("replyCount", "回复数量");
		writer.addHeaderAlias("isDelete", "1删除 0默认");
		writer.addHeaderAlias("tagIsCount", "话题内容标签是否被统计过 1是 0否默认");
		writer.addHeaderAlias("postGoodCount", "点赞");
		writer.addHeaderAlias("postBadCount", "踩数");
		writer.addHeaderAlias("statusCd", "话题状态 1000:有效 1100:无效 1200:未生效");
		writer.addHeaderAlias("nodeSlug", "所属节点");
		writer.addHeaderAlias("nodeTitle", "节点名称");
		writer.addHeaderAlias("remark", "备注");
		writer.addHeaderAlias("avatar", "话题作者头像");
		writer.write(rows1);
		writer.setSheet("父板块");
		writer.addHeaderAlias("id", "父板块标识");
		writer.addHeaderAlias("tabName", "父板块名称");
		writer.addHeaderAlias("tabDesc", "父板块描述");
		writer.addHeaderAlias("isDelete", "是否删除 0：否 1：是");
		writer.addHeaderAlias("createDate", "创建时间");
		writer.addHeaderAlias("tabOrder", "排列顺序");
		writer.write(rows2);
		writer.setSheet("子板块");
		writer.addHeaderAlias("sectionId", "子板块标识");
		writer.addHeaderAlias("sectionName", "子板块名称");
		writer.addHeaderAlias("sectionTab", "子板块标签");
		writer.addHeaderAlias("sectionDesc", "子板块描述");
		writer.addHeaderAlias("sectionTopicNum", "板块帖子数目");
		writer.addHeaderAlias("showStatus", "是否显示，0:不显示 1:显示");
		writer.addHeaderAlias("displayIndex", "子板块排序");
		writer.addHeaderAlias("defaultShow", "默认显示板块 0:默认 1:显示");
		writer.addHeaderAlias("pid", "模块父节点");
		writer.addHeaderAlias("createDate", "创建时间");
		writer.addHeaderAlias("updateDate", "更新时间");
		writer.addHeaderAlias("statusCd", "板块状态 1000:有效 1100:无效 1200:未生效");
		writer.write(rows3);
		// response为HttpServletResponse对象
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		// test.xlsx是弹出下载对话框的文件名，不能为中文，中文请自行编码
		response.setHeader("Content-Disposition", "attachment;filename=test02.xlsx");
		ServletOutputStream out = response.getOutputStream();
		writer.flush(out);
		// 关闭writer，释放内存
		// 关闭writer，释放内存
		writer.close();
	}
}
