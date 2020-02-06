package wang.miansen.roothub.modules.comment.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.modules.comment.dao.CommentDao;
import wang.miansen.roothub.modules.comment.dto.CommentDTO;
import wang.miansen.roothub.modules.comment.model.Comment;
import wang.miansen.roothub.modules.comment.service.CommentService;
import wang.miansen.roothub.modules.post.dto.PostDTO;
import wang.miansen.roothub.modules.post.service.PostService;
import wang.miansen.roothub.modules.user.dto.UserDTO;
import wang.miansen.roothub.modules.user.service.UserService;
import wang.miansen.roothub.common.service.impl.AbstractBaseServiceImpl;

/**
 * 评论 Service Impl
 * @author miansen.wang
 * @date 2018-05-25 20:54:27
 * @since 1.0
 * @version 3.0
 */
@Service
public class CommentServiceImpl extends AbstractBaseServiceImpl<Comment, CommentDTO> implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private PostService topicService;

	@Autowired
	private UserService userService;

	@Override
	public int countToday() {
		return commentDao.countToday();
	}

	/**
	 * 后台评论分页列表
	 */
	@Override
	public Page<Map<String, Object>> pageForAdmin(String author, String topic, String startDate, String endDate,
			Integer pageNumber, Integer pageSize) {
		List<Map<String,Object>> list = commentDao.selectAllForAdmin(author, topic, startDate, endDate, (pageNumber - 1) * pageSize, pageSize);
		int totalRow = countAllForAdmin(author, topic, startDate, endDate);
		return new Page<Map<String, Object>>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 统计后台评论
	 */
	@Override
	public int countAllForAdmin(String author, String topic, String startDate, String endDate) {
		return commentDao.countAllForAdmin(author, topic, startDate, endDate);
	}
	
	@Override
	public Function<? super CommentDTO, ? extends Comment> getDTO2DOMapper() {
		return commentDTO -> {
			Comment comment = new Comment();
			BeanUtils.copyProperties(commentDTO, comment);
			comment.setPostId(commentDTO.getPostDTO().getTopicId());
			comment.setUser_id(commentDTO.getUserDTO().getUserId());
			return comment;
		};
	}

	@Override
	public Function<? super Comment, ? extends CommentDTO> getDO2DTOMapper() {
		return comment -> {
			CommentDTO commentDTO = new CommentDTO();
			BeanUtils.copyProperties(comment, commentDTO);
			PostDTO topicDTO = topicService.getById(commentDTO.getPostDTO().getTopicId());
			UserDTO userDTO = userService.getById(commentDTO.getUserDTO().getUserId());
			commentDTO.setPostDTO(topicDTO);
			commentDTO.setUserDTO(userDTO);
			return commentDTO;
		};
	}

	@Override
	public BaseDao<Comment> getDao() {
		return commentDao;
	}
}