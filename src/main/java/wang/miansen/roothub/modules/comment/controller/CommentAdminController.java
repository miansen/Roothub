package wang.miansen.roothub.modules.comment.controller;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.controller.AbstractBaseController;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.common.util.DateUtil;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import wang.miansen.roothub.modules.comment.dto.CommentDTO;
import wang.miansen.roothub.modules.comment.model.Comment;
import wang.miansen.roothub.modules.comment.service.CommentService;
import wang.miansen.roothub.modules.comment.vo.CommentVO;
import wang.miansen.roothub.modules.post.dto.PostDTO;
import wang.miansen.roothub.modules.post.service.PostService;
import wang.miansen.roothub.modules.user.dto.UserDTO;
import wang.miansen.roothub.modules.user.service.UserService;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-30
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController extends AbstractBaseController<Comment, CommentDTO, CommentVO>{

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;
	
	/**
	 * 后台评论列表
	 * @param author: 评论用户
	 * @param topic: 话题
	 * @param startDate: 开始时间
	 * @param endDate: 结束时间
	 * @param p: 页数
	 * @param model
	 * @return
	 */
	@RequiresPermissions("reply:list")
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(@RequestParam(value = "author",required = false) String author,
					   @RequestParam(value = "topic",required = false) String topic,
					   @RequestParam(value = "startDate",required = false) String startDate,
					   @RequestParam(value = "endDate",required = false) String endDate,
					   @RequestParam(value = "p",required = false,defaultValue = "1") Integer p,Model model) {
	    if (StringUtils.isEmpty(author)) author = null;
	    if (StringUtils.isEmpty(topic)) topic = null;
	    if (StringUtils.isEmpty(startDate)) startDate = null;
	    if (StringUtils.isEmpty(endDate)) endDate = null;
	    Page<Map<String, Object>> page = commentService.pageForAdmin(author, topic, startDate, endDate, p, 25);
	    model.addAttribute("page", page);
	    model.addAttribute("author", author);
	    model.addAttribute("topic", topic);
	    model.addAttribute("startDate", startDate);
	    model.addAttribute("endDate", endDate);
	    model.addAttribute("p", p);
	    return "/default/admin/comment/list";
	}
	
	@Override
	public ModelAndView list(@RequestParam(value = "pageNumber",required = false,defaultValue = "1") Integer pageNumber, HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		String postName = request.getParameter("postName");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
		
		return super.list(pageNumber, request, response);
	}



	/**
	 * 后台评论编辑页面
	 * @param id: 评论ID
	 * @param model
	 * @return
	 */
	@RequiresPermissions("reply:edit")
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id") Integer id,Model model) {
		// Comment reply = commentService.findById(id);
		CommentVO commentVO = getDTO2VO().apply(commentService.getById(id));
		model.addAttribute("commentVO", commentVO);
		model.addAttribute("vEnter", "\n");
		return "/default/admin/comment/edit";
	}
	
	/**
	 * 后台评论编辑接口
	 * @param id: 评论ID
	 * @param content: 评论内容
	 * @return
	 */
	@RequiresPermissions("reply:edit")
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> edit(@RequestParam(value = "id") Integer id,
                               @RequestParam(value = "content") String content){
		CommentDTO commentDTO = commentService.getById(id);
		commentDTO.setContent(content);
		commentDTO.setUpdateDate(new Date());
		commentService.updateById(commentDTO);
		return new Result(true, "编辑成功！");
	}
	
	/**
	 * 删除评论
	 * @param id: 评论ID
	 * @return
	 */
	@RequiresPermissions("reply:delete")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@ResponseBody
	public Result<String> delete(@RequestParam(value = "id") Integer id){
		commentService.removeById(id);
		return new Result(true, "删除成功！");
	}

	@Override
	protected Function<? super CommentDTO, ? extends CommentVO> getDTO2VO() {
		return commentDTO -> {
			CommentVO commentVO = new CommentVO();
			BeanUtils.copyProperties(commentDTO, commentVO);
			commentVO.setPostId(commentDTO.getPostDTO().getPostId());
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
	protected Function<? super CommentVO, ? extends CommentDTO> getVO2DTO() {
		return commentVO -> {
			CommentDTO commentDTO = new CommentDTO();
			BeanUtils.copyProperties(commentVO, commentDTO);
			PostDTO topicDTO = postService.getById(commentVO.getPostId());
			UserDTO userDTO = userService.getById(commentVO.getUserId());
			commentDTO.setPostDTO(topicDTO);
			commentDTO.setUserDTO(userDTO);
			commentDTO.setCreateDate(DateUtil.string2Date(commentVO.getCreateDate(), DateUtil.FORMAT_DATETIME));
			commentDTO.setUpdateDate(DateUtil.string2Date(commentVO.getUpdateDate(), DateUtil.FORMAT_DATETIME));
			return commentDTO;
		};
	}

	@Override
	protected BaseService<Comment, CommentDTO> getService() {
		return commentService;
	}

	@Override
	protected String getModuleName() {
		return "comment";
	}

	@Override
	protected QueryWrapper<Comment> getQueryWrapper() {
		return null;
	}
}
