package wang.miansen.roothub.modules.post.controller;

import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.controller.AbstractBaseController;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.common.util.DateUtil;
import wang.miansen.roothub.common.util.IDGenerator;
import wang.miansen.roothub.modules.node.dto.NodeDTO;
import wang.miansen.roothub.modules.node.service.NodeService;
import wang.miansen.roothub.modules.post.dto.PostDTO;
import wang.miansen.roothub.modules.post.enums.PostErrorCodeEnum;
import wang.miansen.roothub.modules.post.exception.PostException;
import wang.miansen.roothub.modules.post.model.Post;
import wang.miansen.roothub.modules.post.service.PostService;
import wang.miansen.roothub.modules.post.vo.PostVO;
import wang.miansen.roothub.modules.user.dto.UserDTO;
import wang.miansen.roothub.modules.user.model.User;
import wang.miansen.roothub.modules.user.service.UserService;

/**
 * @author miansen.wang
 * @date 2020-01-20
 */
@RestController
@RequestMapping(value = "api/v3")
public class PostApiController extends AbstractBaseController<Post, PostDTO, PostVO> {

	@Autowired
	private PostService topicService;
	
	@Autowired 
	private NodeService nodeService;
	
	@Autowired
	private UserService userService;
	
	@Override
	@RequestMapping(value = "/topic", method = RequestMethod.POST)
	public ResponseEntity<Result<PostVO>> save(@RequestBody PostVO topicVO, HttpServletRequest request, HttpServletResponse response) {
		User user = getUser(request);
		if (user == null) {
			throw new PostException(PostErrorCodeEnum.INVALIDATE_USER);
		}
		topicVO.setPostId(IDGenerator.generateID());
		topicVO.setUserId(user.getUserId());
		topicVO.setUserName(user.getUserName());
		return super.save(topicVO, request, response);
	}

	@Override
	protected Function<? super PostDTO, ? extends PostVO> getDTO2VO() {
		return topicDTO -> {
			PostVO topicVO = new PostVO();
			BeanUtils.copyProperties(topicDTO, topicVO);
			topicVO.setNodeId(topicDTO.getNodeDTO().getNodeId());
			topicVO.setNodeName(topicDTO.getNodeDTO().getNodeTitle());
			topicVO.setUserId(topicDTO.getUserDTO().getUserId());
			topicVO.setUserName(topicDTO.getUserDTO().getUserName());
			topicVO.setCreateDate(DateUtil.formatDateTime(topicDTO.getCreateDate()));
			topicVO.setUpdateDate(DateUtil.formatDateTime(topicDTO.getUpdateDate()));
			return topicVO;
		};
	}

	@Override
	protected Function<? super PostVO, ? extends PostDTO> getVO2DTO() {
		return topicVO -> {
			PostDTO topicDTO = new PostDTO();
			BeanUtils.copyProperties(topicVO, topicDTO);
			NodeDTO nodeDTO = nodeService.getById(topicVO.getNodeId());
			UserDTO userDTO = userService.getById(topicVO.getUserId());
			topicDTO.setNodeDTO(nodeDTO);
			topicDTO.setUserDTO(userDTO);
			topicDTO.setCreateDate(DateUtil.string2Date(topicVO.getCreateDate(), DateUtil.FORMAT_DATETIME));
			topicDTO.setUpdateDate(DateUtil.string2Date(topicVO.getUpdateDate(), DateUtil.FORMAT_DATETIME));
			return topicDTO;
		};
	}

	@Override
	protected BaseService<Post, PostDTO> getService() {
		return topicService;
	}

	@Override
	protected String getModuleName() {
		return "topic";
	}

	@Override
	protected QueryWrapper<Post> getQueryWrapper() {
		return null;
	}

}
