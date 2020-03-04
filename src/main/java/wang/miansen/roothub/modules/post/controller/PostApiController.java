package wang.miansen.roothub.modules.post.controller;

import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.controller.AbstractBaseApiController;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.common.util.BeanUtils;
import wang.miansen.roothub.common.util.IDGenerator;
import wang.miansen.roothub.modules.node.service.NodeService;
import wang.miansen.roothub.modules.post.dto.PostDTO;
import wang.miansen.roothub.modules.post.enums.PostErrorCodeEnum;
import wang.miansen.roothub.modules.post.exception.PostException;
import wang.miansen.roothub.modules.post.model.Post;
import wang.miansen.roothub.modules.post.service.PostService;
import wang.miansen.roothub.modules.post.vo.PostVO;
import wang.miansen.roothub.modules.user.model.User;
import wang.miansen.roothub.modules.user.service.UserService;

/**
 * @author miansen.wang
 * @date 2020-01-20
 */
@RestController
@RequestMapping(value = "api/v3")
public class PostApiController extends AbstractBaseApiController<Post, PostDTO, PostVO> {

	@Autowired
	private PostService postService;
	
	@Autowired 
	private NodeService nodeService;
	
	@Autowired
	private UserService userService;
	
	@Override
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseEntity<Result<PostVO>> save(@RequestBody PostVO postVO, HttpServletRequest request, HttpServletResponse response) {
		User user = getUser(request);
		if (user == null) {
			throw new PostException(PostErrorCodeEnum.INVALIDATE_USER);
		}
		postVO.setPostId(IDGenerator.generateID());
		postVO.setUserId(user.getUserId());
		postVO.setUserName(user.getUserName());
		return super.save(postVO, request, response);
	}

	@Override
	protected Function<? super PostDTO, ? extends PostVO> getDTO2VO() {
		return postDTO -> (PostVO) BeanUtils.DTO2VO(postDTO, PostVO.class);		

	}

	@Override
	protected Function<? super PostVO, ? extends PostDTO> getVO2DTO() {
		return postVO -> (PostDTO) BeanUtils.VO2DTO(postVO, PostDTO.class);
	}

	@Override
	protected BaseService<Post, PostDTO> getService() {
		return postService;
	}

	@Override
	protected QueryWrapper<Post> getQueryWrapper() {
		return null;
	}

}
