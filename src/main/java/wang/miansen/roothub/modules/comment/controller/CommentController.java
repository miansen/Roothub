package wang.miansen.roothub.modules.comment.controller;

import java.util.function.Function;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import wang.miansen.roothub.common.controller.AbstractBaseController;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.common.util.DateUtils;
import wang.miansen.roothub.modules.comment.dto.CommentDTO;
import wang.miansen.roothub.modules.comment.model.Comment;
import wang.miansen.roothub.modules.comment.service.CommentService;
import wang.miansen.roothub.modules.comment.vo.CommentVO;
import wang.miansen.roothub.modules.post.dto.PostDTO;
import wang.miansen.roothub.modules.post.service.PostService;
import wang.miansen.roothub.modules.user.dto.UserDTO;
import wang.miansen.roothub.modules.user.service.UserService;

@Controller
public class CommentController extends AbstractBaseController<Comment, CommentDTO, CommentVO> {


	@Autowired
	private CommentService commentService;

	@Autowired
	private PostService topicService;

	@Autowired
	private UserService userService;

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
			commentVO.setCreateDate(DateUtils.formatDateTime(commentDTO.getCreateDate()));
			commentVO.setUpdateDate(DateUtils.formatDateTime(commentDTO.getUpdateDate()));
			return commentVO;
		};
	}

	@Override
	protected Function<? super CommentVO, ? extends CommentDTO> getVO2DTO() {
		return commentVO -> {
			CommentDTO commentDTO = new CommentDTO();
			BeanUtils.copyProperties(commentVO, commentDTO);
			PostDTO topicDTO = topicService.getById(commentVO.getPostId());
			UserDTO userDTO = userService.getById(commentVO.getUserId());
			commentDTO.setPostDTO(topicDTO);
			commentDTO.setUserDTO(userDTO);
			commentDTO.setCreateDate(DateUtils.string2Date(commentVO.getCreateDate(), DateUtils.FORMAT_DATETIME));
			commentDTO.setUpdateDate(DateUtils.string2Date(commentVO.getUpdateDate(), DateUtils.FORMAT_DATETIME));
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

	/**
	 * 评论接口
	 *
	 * @param request
	 * @param topicId：话题ID
	 * @param content：评论内容
	 * @param type：类型      0：富文本 1：Markdown
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/reply/save", method = RequestMethod.POST)
	 * 
	 * @ResponseBody private Result<ReplyExecution> save(HttpServletRequest request,
	 * 
	 * @RequestParam("topicId") Integer topicId,
	 * 
	 * @RequestParam("content") String content, String type) { User user = getUser(request); Reply reply = new Reply();
	 * reply.setTopicId(topicId);//话题id
	 * 
	 * // 如果是 Markdown 格式的正文，则先渲染后再保存进数据库 if ("1".equals(type)) { content = baseEntity.formatContent(content); }
	 * reply.setReplyContent(content);//回复内容 reply.setCreateDate(new Date());//回复时间 reply.setUpdateDate(new
	 * Date());//更新时间 reply.setReplyAuthorId(user.getUserId());//当前回复用户ID
	 * reply.setReplyAuthorName(user.getUserName());//当前回复用户昵称 reply.setIsDelete(false);//是否删除 0:默认 1:删除
	 * reply.setIsRead(false);//是否已读 0:默认 1:未读 reply.setIsShow(false);//是否可见 0:默认 1:不可见 reply.setReplyGoodCount(0);//点赞
	 * reply.setReplyBadCount(0);//踩数 reply.setReplyType(null); reply.setReplyReadCount(0);
	 * reply.setStatusCd("1000");//回复状态 1000:有效 1100:无效 1200:未生效 ReplyExecution save = replyService.save(reply);//添加回复
	 * Topic findByTopicId = topicService.findByTopicId(topicId);
	 * findByTopicId.setReplyCount(findByTopicId.getReplyCount() + 1);//回复量+1
	 * findByTopicId.setLastReplyAuthor(user.getUserName());//最后回复人昵称 findByTopicId.setLastReplyTime(new
	 * Date());//最后回复时间 topicService.updateTopic(findByTopicId);//更新话题 //回复者与话题作者不是同一个人的时候发送通知 if
	 * (!user.getUserName().equals(findByTopicId.getAuthor())) { Notice notice = new Notice();
	 * //notice.setNoticeTitle(noticeTitle);//通知标题 notice.setIsRead(false);//是否已读：0:默认 1:已读
	 * notice.setNoticeAuthorId(user.getUserId());//发起通知用户ID notice.setNoticeAuthorName(user.getUserName());//发起通知用户昵称
	 * notice.setTargetAuthorName(findByTopicId.getAuthor());//要通知用户的昵称 notice.setCreateDate(new Date());//创建时间
	 * notice.setUpdateDate(new Date());//更新时间 notice.setNoticeAction("reply");//通知动作
	 * notice.setTopicId(findByTopicId.getTopicId());//话题ID notice.setNoticeContent(content);//通知内容
	 * notice.setStatusCd("1000");//通知状态 1000:有效 1100:无效 1200:未生效 noticeService.save(notice);//添加通知 } return new
	 * Result<ReplyExecution>(true, save); }
	 */


}