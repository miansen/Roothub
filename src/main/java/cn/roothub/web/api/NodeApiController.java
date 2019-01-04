package cn.roothub.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.roothub.service.NodeService;

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
