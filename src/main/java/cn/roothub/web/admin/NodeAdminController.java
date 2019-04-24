package cn.roothub.web.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.Result;
import cn.roothub.entity.Node;
import cn.roothub.exception.ApiAssert;
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
	
	@RequiresPermissions("node:edit")
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id") Integer id, Model model) {
		Node node = nodeService.findById(id);
		model.addAttribute("node", node);
		return "admin/node/edit";
	}
	
	@RequiresPermissions("node:edit")
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> edit(Integer nodeId, String nodeTitle, String avatarNormal, String avatarLarge, String nodeDesc) {
		ApiAssert.notNull(nodeId, "节点ID不能为空");
		ApiAssert.notEmpty(nodeTitle, "节点名称不能为空");
		if(StringUtils.isEmpty(avatarNormal)) avatarNormal = null;
		if(StringUtils.isEmpty(avatarLarge)) avatarLarge = null;
		if(StringUtils.isEmpty(nodeDesc)) nodeDesc = null;
		nodeService.update(nodeId, nodeTitle, avatarNormal, avatarLarge, nodeDesc);
		return new Result<>(true, "更新成功");
	}
	
	@RequiresPermissions("node:delete")
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> delete(Integer id){
		ApiAssert.notNull(id, "节点ID不能为空");
		nodeService.deleteById(id);
		return new Result<>(true, "删除成功");
	}
}
