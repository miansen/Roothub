package wang.miansen.roothub.modules.node.controller.front;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.controller.AbstractBaseController;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.common.util.BeanUtils;
import wang.miansen.roothub.common.util.StringUtils;
import wang.miansen.roothub.modules.node.dto.NodeDTO;
import wang.miansen.roothub.modules.node.enums.NodeErrorCodeEnum;
import wang.miansen.roothub.modules.node.exception.NodeException;
import wang.miansen.roothub.modules.node.model.Node;
import wang.miansen.roothub.modules.node.service.NodeService;
import wang.miansen.roothub.modules.node.vo.NodeVO;
import wang.miansen.roothub.modules.post.dto.PostDTO;
import wang.miansen.roothub.modules.post.model.Post;
import wang.miansen.roothub.modules.post.service.PostService;
import wang.miansen.roothub.modules.post.vo.PostVO;

/**
 * @author miansen.wang
 * @date 2018年11月3日 下午3:48:28
 */
@Controller
public class NodeController extends AbstractBaseController<Node, NodeDTO, NodeVO> {

	@Autowired
	private NodeService nodeService;

	@Autowired
	private PostService postService;

	/**
	 * 节点详情
	 */
	@RequestMapping(value = "/nodes/{name}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable(value = "name") String name, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String tabName = request.getParameter("tabName");
		if (StringUtils.isEmpty(tabName)) {
			tabName = "all";
		}
		String pageNumber = request.getParameter("pageNumber");
		if (StringUtils.isEmpty(pageNumber)) {
			pageNumber = "1";
		}
		QueryWrapper<Node> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("node_name", name);
		NodeDTO nodeDTO = nodeService.getOne(queryWrapper);
		if (nodeDTO == null) {
			throw new NodeException(NodeErrorCodeEnum.NOT_FOUND);
		}
		// 节点
		NodeVO nodeVO = getDTO2VO().apply(nodeDTO);
		// 帖子
		Page<PostDTO> postDTOPage = postService.pageByNode(Integer.valueOf(pageNumber), 25, name, tabName);
		Page<PostVO> postVOPage = postDTOPage2PostVOPage(postDTOPage);
		// 父节点
		NodeVO parentNodeVO = null;
		String parentNodeId = nodeVO.getParentNodeId();
		if (StringUtils.notEmpty(parentNodeId)) {
			NodeDTO parentNodeDTO = nodeService.getById(parentNodeId);
			parentNodeVO = getDTO2VO().apply(parentNodeDTO);
		}
		// 子节点
		Page<NodeVO> childrenNodeVOPage = null;
		QueryWrapper<Node> childrenNodeQueryWrapper = new QueryWrapper<>();
		childrenNodeQueryWrapper.eq("parent_node_id", nodeVO.getNodeId());
		childrenNodeQueryWrapper.orderByDesc("create_date");
		childrenNodeVOPage = nodeDTOPage2NodeVOPage(nodeService.page(1, 5, childrenNodeQueryWrapper));
		// 相邻节点
		Page<NodeVO> adjacencyNodeVOPage = null;
		if (StringUtils.notEmpty(parentNodeId)) {
			QueryWrapper<Node> adjacencyNodeQueryWrapper = new QueryWrapper<>();
			adjacencyNodeQueryWrapper.eq("parent_node_id", parentNodeId);
			adjacencyNodeQueryWrapper.orderByDesc("create_date");
			adjacencyNodeVOPage = nodeDTOPage2NodeVOPage(nodeService.page(1, 5, adjacencyNodeQueryWrapper));
		}
		// 总帖子数
		QueryWrapper<Post> postQueryWrapper = new QueryWrapper<>();
		postQueryWrapper.eq("node_id", nodeVO.getNodeId());
		Integer postCount = postService.count(postQueryWrapper);
		mv.addObject("nodeVO", nodeVO);
		mv.addObject("postVOPage", postVOPage);
		mv.addObject("tabName", tabName);
		mv.addObject("parentNodeVO", parentNodeVO);
		mv.addObject("childrenNodeVOPage", childrenNodeVOPage);
		mv.addObject("adjacencyNodeVOPage", adjacencyNodeVOPage);
		mv.addObject("postCount", postCount);
		mv.setViewName(this.getFrontPrefix() + "/detail");
		return mv;
	}

	/**
	 * 节点列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/nodes")
	private String nodes(HttpServletRequest request) {
		List<Node> nodeList = nodeService.findAll(null, null);
		request.setAttribute("nodeList", nodeList);
		return "/default/front/node/list";
	}

	@Override
	protected Function<? super NodeDTO, ? extends NodeVO> getDTO2VO() {
		return nodeDTO -> {
			if (nodeDTO != null) {
				NodeVO nodeVO = new NodeVO();
				BeanUtils.DTO2VO(nodeDTO, nodeVO);
				return nodeVO;
			}
			return null;
		};
	}

	@Override
	protected Function<? super NodeVO, ? extends NodeDTO> getVO2DTO() {
		return nodeVO -> {
			if (nodeVO != null) {
				NodeDTO nodeDTO = new NodeDTO();
				BeanUtils.VO2DTO(nodeVO, nodeDTO);
				return nodeDTO;
			}
			return null;
		};
	}

	private Page<NodeVO> nodeDTOPage2NodeVOPage(Page<NodeDTO> nodeDTOPage) {
		List<NodeDTO> nodeDTOList = nodeDTOPage.getList();
		List<NodeVO> nodeVOList = nodeDTOList.stream().map(getDTO2VO()).collect(Collectors.toList());
		return new Page<NodeVO>(nodeVOList, nodeDTOPage.getPageNumber(), nodeDTOPage.getPageSize(),
				nodeDTOPage.getTotalRow());
	}

	private Page<PostVO> postDTOPage2PostVOPage(Page<PostDTO> postDTOPage) {
		List<PostDTO> postDTOList = postDTOPage.getList();
		List<PostVO> postVOList = postDTOList.stream().map(postDTO2PostVO()).collect(Collectors.toList());
		return new Page<PostVO>(postVOList, postDTOPage.getPageNumber(), postDTOPage.getPageSize(),
				postDTOPage.getTotalRow());
	}

	private Function<? super PostDTO, ? extends PostVO> postDTO2PostVO() {
		return postDTO -> {
			if (postDTO != null) {
				PostVO postVO = new PostVO();
				BeanUtils.DTO2VO(postDTO, postVO);
				return postVO;
			}
			return null;
		};
	}

	@Override
	protected BaseService<Node, NodeDTO> getService() {
		return nodeService;
	}

	@Override
	protected String getModuleName() {
		return "node";
	}

	@Override
	protected QueryWrapper<Node> getQueryWrapper() {
		return null;
	}

}
