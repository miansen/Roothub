package cn.roothub.web.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.roothub.dto.PageDataBody;
import cn.roothub.entity.Node;
import cn.roothub.service.NodeService;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-04-03
 */
@Controller
@RequestMapping("/admin/node")
public class NodeAdminController {

	@Autowired
	private NodeService nodeService;
	
	@RequiresPermissions("node:list")
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(@RequestParam(value = "nodeTitle",required = false) String nodeTitle,
					   @RequestParam(value = "p",defaultValue = "1") Integer p,Model model) {
		if(StringUtils.isEmpty(nodeTitle)) nodeTitle = null;
		PageDataBody<Node> page = nodeService.pageForAdmin(nodeTitle, p, 25);
		model.addAttribute("page", page);
		model.addAttribute("nodeTitle", nodeTitle);
		model.addAttribute("p", p);
		return "admin/node/list";
	}
}
