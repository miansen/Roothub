package cn.roothub.web.front;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.Result;
import cn.roothub.dto.RootUserExecution;
import cn.roothub.entity.ReplyAndTopicByName;
import cn.roothub.entity.RootTopic;
import cn.roothub.entity.RootUser;
import cn.roothub.service.CollectService;
import cn.roothub.service.RootNoticeService;
import cn.roothub.service.RootReplyService;
import cn.roothub.service.RootTopicService;
import cn.roothub.service.RootUserService;
import cn.roothub.util.Base64Util;
import cn.roothub.util.CookieAndSessionUtil;

@Controller
public class UserController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RootReplyService rootReplyService;
	@Autowired
	private RootUserService rootUserService;
	@Autowired
	private RootTopicService rootTopicService;
	@Autowired
	private CollectService collectDaoService;
	@Autowired
	private RootNoticeService rootNoticeService;
	/**
	 * 用户主页
	 * @return
	 */
	@RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
	private String detail(@PathVariable String name, Model model,@RequestParam(value = "tp", defaultValue = "1") Integer tp,@RequestParam(value = "rp", defaultValue = "1") Integer rp,HttpServletRequest request) {
		if(name == null) {
			return "error-page/404.jsp";
		}
		RootUser user = null;
		user = rootUserService.findByName(name);
		if(user == null) {
			return "error-page/404.jsp";
		}
		RootUser user2 = getUser(request);//当前用户
		PageDataBody<RootTopic> topicPage = rootTopicService.pageByAuthor(tp, 20, name);
		PageDataBody<ReplyAndTopicByName> replyPage = rootReplyService.findAllByNameAndTopic(name, rp, 20);
		int countTopic = rootTopicService.countByUserName(user.getUserName());//主题数量
		int countCollect = collectDaoService.count(user.getUserId());//用户收藏话题的数量
		int countReply = rootReplyService.countByName(name);//评论的数量
		model.addAttribute("user", user);
		model.addAttribute("user2", user2);
		model.addAttribute("replyPage", replyPage);
		model.addAttribute("topicPage", topicPage);
		model.addAttribute("countTopic", countTopic);
		model.addAttribute("countCollect", countCollect);
		model.addAttribute("countReply", countReply);
		return "user/detail";
	}
	
	/**
	 * 查看用户创建的更多话题
	 * @param name
	 * @param model
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/user/{name}/topics", method = RequestMethod.GET)
	private String topics(@PathVariable String name, Model model,@RequestParam(value = "p", defaultValue = "1") Integer p,HttpServletRequest request) {
		if(name == null) {
			return "error-page/404.jsp";
		}
		RootUser user = null;
		user = rootUserService.findByName(name);
		if(user == null) {
			return "error-page/404.jsp";
		}
		RootUser user2 = getUser(request);//当前用户
		PageDataBody<RootTopic> topicPage = rootTopicService.pageByAuthor(p, 50, name);
		int countTopic = rootTopicService.countByUserName(user.getUserName());//主题数量
		int countCollect = collectDaoService.count(user.getUserId());//用户收藏话题的数量
		int countReply = rootReplyService.countByName(user.getUserName());//评论的数量
		model.addAttribute("user", user);
		model.addAttribute("user2", user2);
		model.addAttribute("topicPage", topicPage);
		model.addAttribute("countTopic", countTopic);
		model.addAttribute("countCollect", countCollect);
		model.addAttribute("countReply", countReply);
		//model.addAttribute("countCollect", getCountCollect(request));
		//model.addAttribute("countTopicByUserName", getCountTopicByUserName(request));
		//model.addAttribute("notReadNotice", getNotReadNotice(request));
		//model.addAttribute("myUser", getUser(request));
		return "user/topics";
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
		RootUser user = null;
		user = rootUserService.findByName(name);
		if(user == null) {
			return "error-page/404.jsp";
		}
		RootUser user2 = getUser(request);//当前用户
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
		return "user/replys";
	}
	
	/**
	 * 进入个人设置页面
	 * @return
	 */
	@RequestMapping(value = "/user/settings/profile", method = RequestMethod.GET)
	private String setting(HttpServletRequest request) {
		RootUser user = null;
		String cookie = CookieAndSessionUtil.getCookie(request, "user");
		if(cookie == null) {
			return "error-page/500";
		}
		user = rootUserService.findByName(Base64Util.decode(cookie));
		request.setAttribute("user", user);
		return "user/profile";
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
		RootUser user = null;
		String cookie = CookieAndSessionUtil.getCookie(request, "user");
		if(cookie != null) {
			user = rootUserService.findByName(Base64Util.decode(cookie));
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
		return "user/changeAvatar";
	}
	
	@RequestMapping(value = "/user/setting/changeAvatar", method = RequestMethod.POST)
	@ResponseBody
	private Result<RootUserExecution> changeAvatar(String avatar,HttpServletRequest request){
		RootUser user = null;
		String cookie = CookieAndSessionUtil.getCookie(request, "user");
		user = rootUserService.findByName(Base64Util.decode(cookie));
		if(user == null) {
			return new Result<>(false,"请先登录");
		}
		if(avatar == null) {
			return new Result<>(false,"头像不能为空");
		}
		String _avatar = avatar.substring(avatar.indexOf(",") + 1, avatar.length());
		RootUserExecution updateUser = null;
		try {
		byte[] bytes = Base64Util.decode2(_avatar);
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		BufferedImage bufferedImage = ImageIO.read(bais);
		//String __avatar = null;
		String fileName = new Date().getTime()+cookie+"avatar.png";
		String filePath = request.getSession().getServletContext().getRealPath("/")+"/resources/images/"+fileName;
		File file = new File(filePath);
		ImageIO.write(bufferedImage, "PNG", file);
		user.setAvatar(fileName);
		user.setUpdateDate(new Date());
		updateUser = rootUserService.updateUser(user);
		rootTopicService.updateTopicAvatar(user);
		RootUser user2 = rootUserService.findByName(user.getUserName());
		CookieAndSessionUtil.removeSession(request, "user");
		CookieAndSessionUtil.setSession(request, "user", user2);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		return new Result<RootUserExecution>(true,updateUser); 
	}
	
	/**
	 * 进入修改密码界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/settings/changePassword",method = RequestMethod.GET)
	private String changePassword(HttpServletRequest request) {
		return isLogin(request, "error-page/500", "user/changePassword");
	}
	
	@RequestMapping(value = "/user/setting/changePassword",method = RequestMethod.POST)
	@ResponseBody
	private Result<RootUserExecution> changePassword(HttpServletRequest request,String oldPassword,String newPassword){
		if(newPassword == null) {
			return new Result<>(false,"密码不能为空");
		}
		RootUser user = getUser(request);
		if(!user.getPassword().equals(oldPassword)) {
			return new Result<>(false,"旧密码不正确");
		}
		user.setPassword(newPassword);
		RootUserExecution updateUser = rootUserService.updateUser(user);
		return new Result<RootUserExecution>(true,updateUser);
	}
}
