package cn.roothub.web.front;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.roothub.dto.Result;
import cn.roothub.entity.Node;
import cn.roothub.exception.ApiAssert;
import cn.roothub.service.NodeService;

/**
 * @author miansen.wang
 * @date 2018年11月3日 下午3:48:28
 */
@Controller
public class NodeController {
	
	@Autowired
	private NodeService nodeService;
	
	/**
	 * 根据板块查询节点
	 * @param tabName
	 * @return
	 */
	@RequestMapping(value = "/node/{tabName}",method = RequestMethod.GET)
	@ResponseBody
	private Result<List> nodeByTab(@PathVariable String tabName){
		ApiAssert.notNull(tabName, "板块不能为空");
		List<Node> list = nodeService.findAll(tabName, null, null);
		return new Result<List>(true, list);
	}
}
