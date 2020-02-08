package wang.miansen.roothub.modules.user.controller.front;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wang.miansen.roothub.common.beans.BaseEntity;
import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.controller.AbstractBaseController;
import wang.miansen.roothub.common.controller.SessionController;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.modules.user.dto.UserDTO;
import wang.miansen.roothub.modules.user.enums.UserErrorCodeEnum;
import wang.miansen.roothub.modules.user.exception.UserException;
import wang.miansen.roothub.modules.user.model.User;
import wang.miansen.roothub.modules.user.service.UserService;
import wang.miansen.roothub.modules.user.vo.UserVO;
import wang.miansen.roothub.modules.visit.model.Visit;
import wang.miansen.roothub.store.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import wang.miansen.roothub.common.dto.UserExecution;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.modules.collect.service.CollectService;
import wang.miansen.roothub.modules.comment.dto.CommentDTO;
import wang.miansen.roothub.modules.comment.model.ReplyAndTopicByName;
import wang.miansen.roothub.modules.comment.service.CommentService;
import wang.miansen.roothub.modules.comment.vo.CommentVO;
import wang.miansen.roothub.modules.node.dto.NodeDTO;
import wang.miansen.roothub.modules.notice.service.NoticeService;
import wang.miansen.roothub.modules.post.dto.PostDTO;
import wang.miansen.roothub.modules.post.model.Post;
import wang.miansen.roothub.modules.post.service.PostService;
import wang.miansen.roothub.modules.post.vo.PostVO;
import wang.miansen.roothub.modules.visit.service.VisitService;
import wang.miansen.roothub.common.util.ApiAssert;
import wang.miansen.roothub.common.util.CookieAndSessionUtil;
import wang.miansen.roothub.common.util.DateUtil;
import wang.miansen.roothub.common.util.bcrypt.BCryptPasswordEncoder;

@Controller
public class UserController extends AbstractBaseController<User, UserDTO, UserVO> {

	@Autowired
	private CommentService rootReplyService;
	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;
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
	private String detail(@PathVariable String name, Model model,
			@RequestParam(value = "tp", defaultValue = "1") Integer tp,
			@RequestParam(value = "rp", defaultValue = "1") Integer rp, HttpServletRequest request) {
		if (name == null) {
			return "error-page/404";
		}
		User user = null;
		user = userService.findByName(name);
		if (user == null) {
			return "error-page/404";
		}
		User user2 = getUser(request);// 当前用户
		Page<Post> topicPage = postService.pageByAuthor(tp, 20, name);
		Page<ReplyAndTopicByName> replyPage = null;//rootReplyService.findAllByNameAndTopic(name, rp, 20);
		int countTopic = postService.countByUserName(user.getUserName());// 主题数量
		int countCollect = collectDaoService.count(user.getUserId());// 用户收藏话题的数量
		int countReply = 0;//rootReplyService.countByName(name);// 评论的数量
		int countScore = 0;//userService.countScore(user.getUserId());// 积分
		int countVisit = 0;//visitService.count(user.getUserId());// 被访问的次数
		// 当用户为登录状态并且访问者与被访问者不是同一个人时，添加访问记录
		if (user2 != null && user.getUserId() != user2.getUserId()) {
			Visit visit = new Visit();
			visit.setSourceId(user2.getUserId());
			visit.setTargetId(user.getUserId());
			visit.setCreateDate(new Date());
			//visit.setDelete(false);
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

	@RequestMapping(value = "/users/{userName}", method = RequestMethod.GET)
	@Override
	public ModelAndView detail(@PathVariable String userName, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
		userQueryWrapper.eq("user_name", userName);
		UserDTO userDTO = userService.getOne(userQueryWrapper);
		if (userDTO == null) {
			throw new UserException(UserErrorCodeEnum.INVALIDATE_USER);
		}
		Integer pageNumber = 1;
		try {
			pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
		} catch (NumberFormatException e) {
			pageNumber = 1;
		}
		QueryWrapper<Post> postQueryWrapper = new QueryWrapper<>();
		postQueryWrapper.eq("user_id", userDTO.getUserId());
		Page<PostDTO> postDTOPage = postService.page(Integer.valueOf(pageNumber), 25, postQueryWrapper);
		Page<PostVO> postVOPage = doPostDTO2PostVO(postDTOPage);
		mv.setViewName(this.getFrontPrefix() + "/detail");
		mv.addObject("userVO", getDTO2VO().apply(userDTO));
		mv.addObject("postVOPage", postVOPage);
		return mv;
	}



	/**
	 * 查看用户创建的更多话题
	 * @param model
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/user/topics", method = RequestMethod.GET)
	private String topics(Model model, @RequestParam(value = "p", defaultValue = "1") Integer p,
			HttpServletRequest request) {
		User user2 = getUser(request);// 当前用户
		if (user2 == null) return "error-page/404.jsp";
		Page<Post> topicPage = postService.pageByAuthor(p, 50, user2.getUserName());
		int countCollect = collectDaoService.count(user2.getUserId());// 用户收藏话题的数量
		int countTopicByUserName = postService.countByUserName(user2.getUserName());// 用户发布的主题的数量
		int notReadNotice = rootNoticeService.countNotReadNotice(user2.getUserName());// 未读通知的数量
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
	private String replys(@PathVariable String name, Model model,
			@RequestParam(value = "p", defaultValue = "1") Integer p, HttpServletRequest request) {
		if (name == null) {
			return "error-page/404.jsp";
		}
		User user = null;
		user = userService.findByName(name);
		if (user == null) {
			return "error-page/404.jsp";
		}
		User user2 = getUser(request);// 当前用户
		Page<ReplyAndTopicByName> replyPage = null;// rootReplyService.findAllByNameAndTopic(name, p, 20);
		int countTopic = postService.countByUserName(user.getUserName());// 主题数量
		int countCollect = collectDaoService.count(user.getUserId());// 用户收藏话题的数量
		int countReply = 0;//rootReplyService.countByName(user.getUserName());// 评论的数量
		model.addAttribute("user", user);
		model.addAttribute("user2", user2);
		model.addAttribute("replyPage", replyPage);
		model.addAttribute("countTopic", countTopic);
		model.addAttribute("countCollect", countCollect);
		model.addAttribute("countReply", countReply);
		// model.addAttribute("countCollect", getCountCollect(request));
		// model.addAttribute("countTopicByUserName", getCountTopicByUserName(request));
		// model.addAttribute("notReadNotice", getNotReadNotice(request));
		// model.addAttribute("myUser", getUser(request));
		return "/default/front/user/replys";
	}

	/**
	 * 进入个人设置页面
	 * @return
	 */
	@RequestMapping(value = "/user/settings/profile", method = RequestMethod.GET)
	private String setting(HttpServletRequest request) {
		/*
		 * RootUser user = null; String cookie = CookieAndSessionUtil.getCookie(request, "user"); if(cookie == null) {
		 * return "error-page/500"; } user = rootUserService.findByName(Base64Util.decode(cookie));
		 * request.setAttribute("user", user); return "user/profile";
		 */

		User user2 = getUser(request);
		if (user2 == null) {
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
	private String updateUserInfo(String email, String url, String thirdId, String userAddr, String signature,
			HttpServletRequest request, HttpServletResponse response) {
		// User user = null;
		User user = getUser(request);
		// String cookie = CookieAndSessionUtil.getCookie(request, "user");
		if (user != null) {
			// user = rootUserService.findByName(Base64Util.decode(cookie));
			user.setEmail(email);
			user.setUrl(url);
			user.setThirdId(thirdId);
			user.setUserAddr(userAddr);
			user.setSignature(signature);
			userService.updateUser(user);
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
		if (cookie == null) {
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
	private Result<String> changeAvatar(String avatarBase64, String path, HttpServletRequest request) {
		User user = getUser(request);
		ApiAssert.notNull(user, "请先登录");
		ApiAssert.notEmpty(avatarBase64, "头像不能为空");
		userService.updateAvatar(avatarBase64, path, user, request);
		return new Result<>(200, true, "更新成功");
	}

	/**
	 * 进入修改密码界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/settings/changePassword", method = RequestMethod.GET)
	private String changePassword(HttpServletRequest request) {
		return "/default/front/user/changePassword";
	}

	@RequestMapping(value = "/user/setting/changePassword", method = RequestMethod.POST)
	@ResponseBody
	private Result<UserExecution> changePassword(HttpServletRequest request, String oldPassword, String newPassword) {
		if (newPassword == null) {
			return new Result<>(201, false, "密码不能为空");
		}
		User user = getUser(request);
		if (!user.getPassword().equals(oldPassword)) {
			return new Result<>(201, false, "旧密码不正确");
		}
		// 加密保存
		user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
		UserExecution updateUser = userService.updateUser(user);
		return new Result<UserExecution>(200, true, updateUser);
	}

	@Override
	protected Function<? super UserDTO, ? extends UserVO> getDTO2VO() {
		return userDTO -> {
			UserVO userVO = new UserVO();
			if (userDTO != null) {
				BeanUtils.copyProperties(userDTO, userVO);
			}
			userVO.setCreateDate(DateUtil.formatDateTime(userDTO.getCreateDate()));
			userVO.setUpdateDate(DateUtil.formatDateTime(userDTO.getUpdateDate()));
			return userVO;
		};
	}

	@Override
	protected Function<? super UserVO, ? extends UserDTO> getVO2DTO() {
		return userVO -> {
			UserDTO userDTO = new UserDTO();
			if (userVO != null) {
				BeanUtils.copyProperties(userVO, userDTO);
			}
			userDTO.setCreateDate(DateUtil.string2Date(userVO.getCreateDate(), DateUtil.FORMAT_DATETIME));
			userDTO.setUpdateDate(DateUtil.string2Date(userVO.getUpdateDate(), DateUtil.FORMAT_DATETIME));
			return userDTO;
		};
	}

	@Override
	protected BaseService<User, UserDTO> getService() {
		return userService;
	}

	@Override
	protected String getModuleName() {
		return "user";
	}

	@Override
	protected QueryWrapper<User> getQueryWrapper() {
		return null;
	}
	
	private Page<PostVO> doPostDTO2PostVO(Page<PostDTO> postDTOPage) {
		List<PostDTO> postDTOList = postDTOPage.getList();
		List<PostVO> postVOList = postDTOList.stream().map(getPostDTO2PostVO())
				.collect(Collectors.toList());
		return new Page<PostVO>(postVOList, postDTOPage.getPageNumber(), postDTOPage.getPageSize(),
				postDTOPage.getTotalRow());
	}
	
	private Function<? super PostDTO, ? extends PostVO> getPostDTO2PostVO() {
		return postDTO -> {
			if (postDTO != null) {
				PostVO postVO = new PostVO();
				BeanUtils.copyProperties(postDTO, postVO);
				NodeDTO nodeDTO = postDTO.getNodeDTO();
				UserDTO userDTO = postDTO.getUserDTO();
				if (nodeDTO != null) {
					postVO.setNodeId(postDTO.getNodeDTO().getNodeId());
					postVO.setNodeName(postDTO.getNodeDTO().getNodeTitle());
				}
				if (userDTO != null) {
					postVO.setUserId(postDTO.getUserDTO().getUserId());
					postVO.setUserName(postDTO.getUserDTO().getUserName());
				}
				postVO.setCreateDate(DateUtil.formatDateTime(postDTO.getCreateDate()));
				postVO.setUpdateDate(DateUtil.formatDateTime(postDTO.getUpdateDate()));
				return postVO;
			}
			return null;
		};
	}

}