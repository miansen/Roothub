package wang.miansen.roothub.modules.node.controller.api;

import wang.miansen.roothub.modules.node.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author miansen.wang
 * @date 2019年1月4日 下午9:16:38
 */
@RestController
public class NodeApiController {
	
	@Autowired
	private NodeService nodeService;
	
	/*@RequestMapping(value = "/api/user/top100",method = RequestMethod.GET)
	private Result<Node> getNode(){
		
	}*/
}
