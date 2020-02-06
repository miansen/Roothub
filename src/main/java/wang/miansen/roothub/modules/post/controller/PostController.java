package wang.miansen.roothub.modules.post.controller;

import java.util.Arrays;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import wang.miansen.roothub.common.dto.TopicExecution;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.common.util.ApiAssert;
import wang.miansen.roothub.common.util.DateUtil;
import wang.miansen.roothub.modules.node.model.Node;
import wang.miansen.roothub.modules.user.model.User;
import wang.miansen.roothub.modules.tab.model.Tab;
import wang.miansen.roothub.modules.collect.service.CollectService;
import wang.miansen.roothub.modules.comment.dto.CommentDTO;
import wang.miansen.roothub.modules.comment.model.Comment;
import wang.miansen.roothub.modules.comment.service.CommentService;
import wang.miansen.roothub.modules.comment.vo.CommentVO;
import wang.miansen.roothub.modules.node.service.NodeService;
import wang.miansen.roothub.modules.notice.service.NoticeService;
import wang.miansen.roothub.modules.post.dto.PostDTO;
import wang.miansen.roothub.modules.post.model.Post;
import wang.miansen.roothub.modules.post.service.PostService;
import wang.miansen.roothub.modules.post.service.TabService;
import wang.miansen.roothub.modules.post.vo.PostVO;

@Controller
public class PostController extends AbstractBaseController<Post, PostDTO, PostVO> {

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private CollectService collectService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private TabService tabService;
	@Autowired
	private NodeService nodeService;
	@Autowired
	private BaseEntity baseEntity;

	/**
	 * 话题详情
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/posts/{postId}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable String postId, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		PostDTO topicDTO = postService.getById(postId);
		ApiAssert.notNull(topicDTO, "话题消失了~");
		// 浏览量+1
		topicDTO.setViewCount(topicDTO.getViewCount() + 1);
		// 更新话题
		postService.updateById(topicDTO);
		String p = request.getParameter("p");
		if (wang.miansen.roothub.common.util.StringUtils.isBlank(p)) {
			p = "1";
		}
		// 查询回复
		Page<CommentVO> commentVOPage = doCommentDTO2CommentVO(commentService.page(Integer.valueOf(p), 25));

		// 话题被收藏的数量
		// int countByTid = collectService.countByTid(1);
		int countByTid = 0;
		
		mv.addObject("topic", getDTO2VO().apply(topicDTO));
		mv.addObject("commentVOPage", commentVOPage);
		mv.addObject("countByTid", countByTid);
		mv.setViewName(this.getFrontPrefix() + "/view");
		return mv;
	}

	/**
	 * 发布话题页面
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/topic/create", method = RequestMethod.GET)
	private String add(String n, HttpServletRequest request) {
		List<Tab> tabList = tabService.selectAll();
		List<Node> nodeList = nodeService.findAll(null, null);
		request.setAttribute("tabList", tabList);
		request.setAttribute("nodeList", nodeList);
		request.setAttribute("node", n);
		return "/default/front/topic/add";
	}

	/**
	 * 发布话题接口
	 *
	 * @param title：标题
	 * @param content：正文
	 * @param nodeTitle：节点
	 * @param tag：标签，暂时只能输入一个
	 * @param type：类型         0：富文本 1：Markdown
	 * @param request
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/topic/save", method = RequestMethod.POST)
	 * 
	 * @ResponseBody private Result<TopicExecution> save(String title, String content, String nodeTitle, String tag,
	 * String type, HttpServletRequest request) { User user = getUser(request); ApiAssert.notNull(user, "请先登录");
	 * ApiAssert.notNull(title, "标题不能为空"); if (StringUtils.isEmpty(tag)) tag = null;
	 * 
	 * // 如果是 Markdown 格式的正文，则先渲染再保存进数据库 if ("1".equals(type)) { content = baseEntity.formatContent(content); }
	 * TopicExecution saveTopic = topicService.createTopic(title, content, null, null, nodeTitle, tag, user); return new
	 * Result<TopicExecution>(true, saveTopic); }
	 */

	/**
	 * 根据标签分页查找话题
	 *
	 * @param name
	 * @param model
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/tag/{name}", method = RequestMethod.GET)
	private String tag(@PathVariable String name, Model model,
			@RequestParam(value = "p", defaultValue = "1") Integer p) {
		Page<Post> pageByTag = postService.pageByTag(name, p, 20);
		model.addAttribute("tagName", name);
		model.addAttribute("pageByTag", pageByTag);
		return "/default/front/tag/view";
	}

	private Page<CommentVO> doCommentDTO2CommentVO(Page<CommentDTO> commentDTOPage) {
		List<CommentDTO> commentDTOList = commentDTOPage.getList();
		List<CommentVO> commentVOList = commentDTOList.stream().map(getCommentDTO2CommentVO())
				.collect(Collectors.toList());
		return new Page<CommentVO>(commentVOList, commentDTOPage.getPageNumber(), commentDTOPage.getPageSize(),
				commentDTOPage.getTotalRow());
	}

	@Override
	protected Function<? super PostDTO, ? extends PostVO> getDTO2VO() {
		return topicDTO -> {
			PostVO topicVO = new PostVO();
			BeanUtils.copyProperties(topicDTO, topicVO);
			return topicVO;
		};
	}

	@Override
	protected Function<? super PostVO, ? extends PostDTO> getVO2DTO() {
		return topicVO -> {
			PostDTO topicDTO = new PostDTO();
			BeanUtils.copyProperties(topicVO, topicDTO);
			return topicDTO;
		};
	}

	private Function<? super CommentDTO, ? extends CommentVO> getCommentDTO2CommentVO() {
		return commentDTO -> {
			CommentVO commentVO = new CommentVO();
			BeanUtils.copyProperties(commentDTO, commentVO);
			commentVO.setPostId(commentDTO.getPostDTO().getTopicId());
			commentVO.setPostName(commentDTO.getPostDTO().getTitle());
			commentVO.setPostAvatar(commentDTO.getPostDTO().getAvatar());
			commentVO.setUserId(commentDTO.getUserDTO().getUserId());
			commentVO.setUserName(commentDTO.getUserDTO().getUserName());
			commentVO.setUserAvatar(commentDTO.getUserDTO().getAvatar());
			commentVO.setCreateDate(DateUtil.formatDateTime(commentDTO.getCreateDate()));
			commentVO.setUpdateDate(DateUtil.formatDateTime(commentDTO.getUpdateDate()));
			return commentVO;
		};
	}

	@Override
	protected BaseService<Post, PostDTO> getService() {
		return postService;
	}

	@Override
	protected String getModuleName() {
		return "post";
	}

	@Override
	protected QueryWrapper<Post> getQueryWrapper() {
		return null;
	}

}
