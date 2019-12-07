package wang.miansen.roothub.module.user.controller.front;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wang.miansen.roothub.common.controller.BaseController;
import wang.miansen.roothub.core.base.BaseEntity;
import wang.miansen.roothub.core.base.PageDataBody;
import wang.miansen.roothub.core.base.Result;
import wang.miansen.roothub.core.exception.ApiAssert;
import wang.miansen.roothub.module.reply.service.ReplyService;
import wang.miansen.roothub.module.user.model.User;
import wang.miansen.roothub.module.user.service.UserService;
import wang.miansen.roothub.module.visit.model.Visit;
import wang.miansen.roothub.store.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import wang.miansen.roothub.common.dto.UserExecution;
import wang.miansen.roothub.module.reply.model.ReplyAndTopicByName;
import wang.miansen.roothub.module.topic.model.Topic;
import wang.miansen.roothub.module.collect.service.CollectService;
import wang.miansen.roothub.module.notice.service.NoticeService;
import wang.miansen.roothub.module.topic.service.TopicService;
import wang.miansen.roothub.module.visit.service.VisitService;
import wang.miansen.roothub.common.util.CookieAndSessionUtil;
import wang.miansen.roothub.common.util.bcrypt.BCryptPasswordEncoder;

@Controller
public class UserController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ReplyService rootReplyService;
	@Autowired
	private UserService rootUserService;
	@Autowired
	private TopicService rootTopicService;
	@Autowired
	private CollectService collectDaoService;
	@Autowired
	private NoticeService rootNoticeService;
	@Autowired
	private VisitService visitService;
	@Autowired
	private StorageService storageService;
	/**
	 * 用户主页
	 * @return
	 */
	@RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
	private String detail(@PathVariable String name, Model model,@RequestParam(value = "tp", defaultValue = "1") Integer tp,@RequestParam(value = "rp", defaultValue = "1") Integer rp,HttpServletRequest request) {
		if(name == null) {
			return "error-page/404";
		}
		User user = null;
		user = rootUserService.findByName(name);
		if(user == null) {
			return "error-page/404";
		}
		User user2 = getUser(request);//当前用户
		PageDataBody<Topic> topicPage = rootTopicService.pageByAuthor(tp, 20, name);
		PageDataBody<ReplyAndTopicByName> replyPage = rootReplyService.findAllByNameAndTopic(name, rp, 20);
		int countTopic = rootTopicService.countByUserName(user.getUserName());//主题数量
		int countCollect = collectDaoService.count(user.getUserId());//用户收藏话题的数量
		int countReply = rootReplyService.countByName(name);//评论的数量
		int countScore = rootUserService.countScore(user.getUserId());//积分
		int countVisit = visitService.count(user.getUserId());//被访问的次数
		//当用户为登录状态并且访问者与被访问者不是同一个人时，添加访问记录
		if(user2 != null && user.getUserId() != user2.getUserId()) {
			Visit visit = new Visit();
			visit.setUid(user2.getUserId());
			visit.setVid(user.getUserId());
			visit.setCreateDate(new Date());
			visit.setDelete(false);
			visitService.save(visit);
		}
		model.addAttribute("user", user);
		model.addAttribute("user2", user2);
		model.addAttribute("replyPage", replyPage);
		model.addAttribute("topicPage", topicPage);
		model.addAttribute("countTopic", countTopic);
		model.addAttribute("countCollect", countCollect);
		model.addAttribute("countReply", countReply);
		model.addAttribute("countScore", countScore);
		model.addAttribute("countVisit", countVisit);
		return "/default/front/user/view";
	}
	
	/**
	 * 查看用户创建的更多话题
	 * @param model
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/user/topics", method = RequestMethod.GET)
	private String topics(Model model,@RequestParam(value = "p", defaultValue = "1") Integer p,HttpServletRequest request) {
		User user2 = getUser(request);//当前用户
		if(user2 == null) return "error-page/404.jsp";
		PageDataBody<Topic> topicPage = rootTopicService.pageByAuthor(p, 50, user2.getUserName());
		int countCollect = collectDaoService.count(user2.getUserId());//用户收藏话题的数量
		int countTopicByUserName = rootTopicService.countByUserName(user2.getUserName());//用户发布的主题的数量
		int notReadNotice = rootNoticeService.countNotReadNotice(user2.getUserName());//未读通知的数量
		BaseEntity baseEntity = new BaseEntity();
		model.addAttribute("baseEntity", baseEntity);
		model.addAttribute("user", user2);
		model.addAttribute("topicPage", topicPage);
		request.setAttribute("countCollect", countCollect);
		request.setAttribute("countTopicByUserName", countTopicByUserName);
		request.setAttribute("notReadNotice", notReadNotice);
		return "/default/front/user/topics";
	}
	
	/**
	 * 查看用户评论的更多话题
	 * @param name
	 * @param model
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/user/{name}/replys", method = RequestMethod.GET)
	private String replys(@PathVariable String name, Model model,@RequestParam(value = "p", defaultValue = "1") Integer p,HttpServletRequest request) {
		if(name == null) {
			return "error-page/404.jsp";
		}
		User user = null;
		user = rootUserService.findByName(name);
		if(user == null) {
			return "error-page/404.jsp";
		}
		User user2 = getUser(request);//当前用户
		PageDataBody<ReplyAndTopicByName> replyPage = rootReplyService.findAllByNameAndTopic(name, p, 20);
		int countTopic = rootTopicService.countByUserName(user.getUserName());//主题数量
		int countCollect = collectDaoService.count(user.getUserId());//用户收藏话题的数量
		int countReply = rootReplyService.countByName(user.getUserName());//评论的数量
		model.addAttribute("user", user);
		model.addAttribute("user2", user2);
		model.addAttribute("replyPage", replyPage);
		model.addAttribute("countTopic", countTopic);
		model.addAttribute("countCollect", countCollect);
		model.addAttribute("countReply", countReply);
		//model.addAttribute("countCollect", getCountCollect(request));
		//model.addAttribute("countTopicByUserName", getCountTopicByUserName(request));
		//model.addAttribute("notReadNotice", getNotReadNotice(request));
		//model.addAttribute("myUser", getUser(request));
		return "/default/front/user/replys";
	}
	
	/**
	 * 进入个人设置页面
	 * @return
	 */
	@RequestMapping(value = "/user/settings/profile", method = RequestMethod.GET)
	private String setting(HttpServletRequest request) {
		/*RootUser user = null;
		String cookie = CookieAndSessionUtil.getCookie(request, "user");
		if(cookie == null) {
			return "error-page/500";
		}
		user = rootUserService.findByName(Base64Util.decode(cookie));
		request.setAttribute("user", user);
		return "user/profile";*/
		
		User user2 = getUser(request);
		if(user2 == null) {
			return "error-page/500";
		}
		request.setAttribute("user", user2);
		return "/default/front/user/profile";
		
	}
	
	/**
	 * 修改个人设置
	 * @param email
	 * @param url
	 * @param thirdId
	 * @param userAddr
	 * @param signature
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/setting/profile", method = RequestMethod.POST)
	private String updateUserInfo(String email,String url,String thirdId,String userAddr,
			String signature,HttpServletRequest request,HttpServletResponse response) {
		//User user = null;
		User user = getUser(request);
		//String cookie = CookieAndSessionUtil.getCookie(request, "user");
		if(user != null) {
			//user = rootUserService.findByName(Base64Util.decode(cookie));
			user.setEmail(email);
			user.setUrl(url);
			user.setThirdId(thirdId);
			user.setUserAddr(userAddr);
			user.setSignature(signature);
			rootUserService.updateUser(user);
			try {
				response.sendRedirect("/user/settings/profile");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "error-page/500";
	}
	
	/**
	 * 进入修改头像界面
	 * @return
	 */
	@RequestMapping(value = "/user/settings/changeAvatar", method = RequestMethod.GET)
	private String changeAvatar(HttpServletRequest request) {
		String cookie = CookieAndSessionUtil.getCookie(request, "user");
		if(cookie == null) {
			return "error-page/500";
		}
		return "/default/front/user/changeAvatar";
	}
	
	/**
	 * 更新头像
	 * @param avatarBase64:base64格式的图片
	 * @param path:自定义保存路径
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/setting/changeAvatar", method = RequestMethod.POST)
	@ResponseBody
	private Result<String> changeAvatar(String avatarBase64, String path, HttpServletRequest request){
		User user = getUser(request);
		ApiAssert.notNull(user, "请先登录");
		ApiAssert.notEmpty(avatarBase64, "头像不能为空");
		rootUserService.updateAvatar(avatarBase64, path, user, request);
		return new Result(true, "更新成功");
	}
	
	/**
	 * 进入修改密码界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/settings/changePassword",method = RequestMethod.GET)
	private String changePassword(HttpServletRequest request) {
		return isLogin(request, "error-page/500", "/default/front/user/changePassword");
	}
	
	@RequestMapping(value = "/user/setting/changePassword",method = RequestMethod.POST)
	@ResponseBody
	private Result<UserExecution> changePassword(HttpServletRequest request,String oldPassword,String newPassword){
		if(newPassword == null) {
			return new Result<>(false,"密码不能为空");
		}
		User user = getUser(request);
		if(!user.getPassword().equals(oldPassword)) {
			return new Result<>(false,"旧密码不正确");
		}
		//加密保存
		user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
		UserExecution updateUser = rootUserService.updateUser(user);
		return new Result<UserExecution>(true,updateUser);
	}
}
