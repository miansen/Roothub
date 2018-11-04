package cn.roothub.web.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.Result;
import cn.roothub.entity.Node;
import cn.roothub.entity.Section;
import cn.roothub.entity.Topic;
import cn.roothub.exception.ApiAssert;
import cn.roothub.service.NodeService;
import cn.roothub.service.SectionService;
import cn.roothub.service.TopicService;

/**
 * @author miansen.wang
 * @date 2018年11月3日 下午3:48:28
 */
@Controller
public class NodeController {
	
	@Autowired
	private NodeService nodeService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private SectionService sectionService;
	
	/**
	 * 根据板块查询节点
	 * @param tabName
	 * @return
	 */
	@RequestMapping(value = "/node/tab/{tabName}",method = RequestMethod.GET)
	@ResponseBody
	private Result<List> nodeByTab(@PathVariable String tabName){
		ApiAssert.notNull(tabName, "板块不能为空");
		List<Node> list = nodeService.findAllByTab(tabName, null, null);
		return new Result<List>(true, list);
	}
	
	@RequestMapping(value = "/node/{nodeCode}",method = RequestMethod.GET)
	private String toNode(@PathVariable String nodeCode,
						  @RequestParam(value = "s", defaultValue = "all") String sectionName,
						  @RequestParam(value = "p", defaultValue = "1") Integer p,
						  HttpServletRequest request,HttpServletResponse response) {
		List<Section> sectionList = sectionService.findAll();
		//PageDataBody<Topic> page = topicService.pageAllByNode(p, 50, nodeCode);
		PageDataBody<Topic> page = topicService.pageByNodeAndSection(p, 1, sectionName, nodeCode);
		Node node = nodeService.findByNodeCode(nodeCode);
		List<Node> atherNode = nodeService.findAtherByNodeCode(nodeCode, 0, 50);
		int countTopicByNode = topicService.countTopicByNode(nodeCode);
		request.setAttribute("sectionList", sectionList);
		request.setAttribute("page", page);
		request.setAttribute("node", node);
		request.setAttribute("sectionName", sectionName);
		request.setAttribute("atherNode", atherNode);
		request.setAttribute("countTopicByNode", countTopicByNode);
		return "node/node";
	}
}
