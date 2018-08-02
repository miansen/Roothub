package cn.roothub.web.front;

import java.util.Date;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.Result;
import cn.roothub.dto.RootUserExecution;
import cn.roothub.entity.RootSection;
import cn.roothub.entity.RootTopic;
import cn.roothub.entity.RootUser;
import cn.roothub.entity.Tab;
import cn.roothub.entity.Tag;
import cn.roothub.service.CollectService;
import cn.roothub.service.RootNoticeService;
import cn.roothub.service.RootReplyService;
import cn.roothub.service.RootSectionService;
import cn.roothub.service.RootTopicService;
import cn.roothub.service.RootUserService;
import cn.roothub.service.TabService;
import cn.roothub.util.Base64Util;
import cn.roothub.util.CookieAndSessionUtil;
import cn.roothub.util.JsonUtil;
import cn.roothub.util.PtabUtil;
import cn.roothub.util.StringUtil;
import cn.roothub.util.WebUtil;
import priv.sen.root.redis.test.RedisUtil;

@Controller // 标注这是一个控制类，类名不能和注解名一样
//@RequestMapping("/root") // 访问父路径
public class IndexController extends BaseController{

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
	private TabService tabService;
	
	/**
	 * 首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)//访问子路径
	private String index(HttpServletRequest request,HttpServletResponse response,
						 @RequestParam(value = "tab", defaultValue = "all") String tab,
            			 @RequestParam(value = "p", defaultValue = "1") Integer p,
            			 @RequestParam(value = "ptab", defaultValue = "def") String ptab) {
		ptab = PtabUtil.getPtab(request,response,ptab);
		PageDataBody<RootTopic> page;
		if(ptab == null || ptab.equals("all")) {
			page = rootTopicService.page(p, 50, tab,null);
		}else {
			page = rootTopicService.page(p, 50, tab,ptab);
		}
		List<RootSection> sectionAll = rootSectionService.findAll();
		List<Tab> ptabList = tabService.selectAll();
		List<RootTopic> findHot = rootTopicService.findHot(0, 10);//热门话题榜
		PageDataBody<Tag> tag = rootTopicService.findByTag(1, 10);//最热标签
		int countUserAll = rootUserService.countUserAll();//注册会员的数量
		int countAllTopic = rootTopicService.countAllTopic(null);//所有话题的数量
		int countAllReply = rootReplyService.countAll();//所有评论的数量
		RootUser user = null;
    	String cookie = CookieAndSessionUtil.getCookie(request, "user");
    	int notReadNotice = 0;//未读通知的数量
    	int countCollect = 0;//用户收藏话题的数量
    	int countTopicByUserName = 0;//用户发布的主题的数量
    	if(cookie != null) {
    		user = rootUserService.findByName(Base64Util.decode(cookie));
    		notReadNotice = rootNoticeService.countNotReadNotice(user.getUserName());
    		countCollect = collectDaoService.count(user.getUserId());
    		countTopicByUserName = rootTopicService.countByUserName(user.getUserName());
    		request.setAttribute("user", user);
    	}
		request.setAttribute("page", page);
		request.setAttribute("findHot", findHot);
		request.setAttribute("sectionAll", sectionAll);
		request.setAttribute("ptabList", ptabList);
		request.setAttribute("tab", tab);
		request.setAttribute("ptab", ptab);
		request.setAttribute("notReadNotice", notReadNotice);
		request.setAttribute("tag", tag);
		request.setAttribute("countUserAll", countUserAll);
		request.setAttribute("countAllTopic", countAllTopic);
		request.setAttribute("countAllReply", countAllReply);
		request.setAttribute("countCollect", countCollect);
		request.setAttribute("countTopicByUserName", countTopicByUserName);
		return "index";
	}
	
	/**
     * 注册页面
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    private String register(HttpServletRequest request) {
            return "register";   
    }

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	private Result<RootUserExecution> register(@RequestParam("username") String username,
											   @RequestParam("password") String password, 
											   @RequestParam("email") String email,
											   HttpServletRequest request) {
		if(username == null || username.equals("") && username.length() <= 0) {
			return new Result<RootUserExecution>(false, "用户名不能为空");
		}
		if(password == null || password.equals("") && password.length() <= 0) {
			return new Result<>(false, "密码不能为空");
		}
		if(email == null || email.equals("") && email.length() <= 0) {
			return new Result<>(false, "邮箱不能为空");
		}
		RootUser findByName = rootUserService.findByName(username);
		if(findByName != null) {
			return new Result<>(false, "用户已存在");
		}
		RootUser findByEmail = rootUserService.findByEmail(email);
		if(findByEmail != null) {
			return new Result<>(false, "邮箱已存在");
		}
		RootUser rootUser = new RootUser();
		rootUser.setUserName(username);
		rootUser.setPassword(password);
		rootUser.setScore(0);
		rootUser.setEmail(email);
		rootUser.setReceiveMsg(false);
		rootUser.setCreateDate(new Date());
		rootUser.setUpdateDate(new Date());
		rootUser.setIsBlock(false);
		rootUser.setStatusCd("1000");
		rootUser.setUserType("2");
		rootUser.setAvatar("69290780aaafb00aa37ff2a61342dded.png");
		rootUser.setSignature("这家伙很懒，什么都没留下");
		RootUserExecution save = rootUserService.save(rootUser);
		return new Result<RootUserExecution>(true, save);
	}
	
	/**
     * 登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    private String login(HttpServletRequest request) {
            return "login";   
    }
    
    /**
     * 登录处理
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    private Result<RootUser> login(@RequestParam("username") String username,
								   @RequestParam("password") String password,HttpServletRequest request,
								   HttpServletResponse response){
    	RootUser user = null;
    	user = rootUserService.findByUserNameAndPassword(username, password);
    	if(user == null) {
    		return new Result<>(false, "用户名或者密码错误");
    	}else {
    		//将用户的登录信息存进redis
    		//RedisUtil.setString("user", user.toString());
    		//1.先将对象转成json
    		String str = JsonUtil.objectToJson(user);
    		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
    		//2.将json存进redis
    		opsForValue.set("user", str);
    		//设置cookie
    		CookieAndSessionUtil.setCookie("user", Base64Util.encode(username), 30 * 24 * 60 * 60, "/", true, response);
    		//设置session
    		CookieAndSessionUtil.setSession(request, "user", user);
    		return new Result<RootUser>(true, user);
    	}
    }

    /**
     * 退出
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    private String logout(HttpServletRequest request,HttpServletResponse response) {
    	stringRedisTemplate.delete("user");
    	CookieAndSessionUtil.removeSession(request, "user");
    	CookieAndSessionUtil.removeCookie(response, "user", "/", true);
    	return "redirect:/";
    }
    
    /**
     * 标签页
     * @param request
     * @param p
     * @return
     */
    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    private String tag(HttpServletRequest request,@RequestParam(value = "p", defaultValue = "1") Integer p) {
    	PageDataBody<Tag> tag = rootTopicService.findByTag(p, 20);
    	request.setAttribute("tag", tag);
    	return "tag/tag";
    }
    
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    @ResponseBody
    private Result<RootUser> session(HttpServletRequest request) {
    	/*RootUser user = null;
    	String cookie = CookieAndSessionUtil.getCookie(request, "user");
    	if(cookie != null) {
    		user = rootUserService.findByName(Base64Util.decode(cookie));
    		return new Result<RootUser>(true, user);
    	}
    	return new Result<>(false, "no session");*/
    	RootUser user = getUser(request);
    	if(user != null) return new Result<RootUser>(true, user);
    	return new Result<>(false, "no session");
    }
    
    /**
     * 搜索
     * @param request
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    private String search(HttpServletRequest request,@RequestParam("s") String search,@RequestParam(value = "p", defaultValue = "1") Integer p) {
    	if(search == null || search.equals("")) {
    		return "search";
    	}
    	PageDataBody<RootTopic> pageLike = rootTopicService.pageLike(p, 50, search);
    	request.setAttribute("pageLike", pageLike);
    	request.setAttribute("search", search);
    	return "search";
    }
}
