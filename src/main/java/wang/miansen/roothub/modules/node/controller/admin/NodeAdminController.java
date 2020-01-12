package wang.miansen.roothub.modules.node.controller.admin;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.util.ApiAssert;
import wang.miansen.roothub.modules.node.model.Node;
import wang.miansen.roothub.modules.node.service.NodeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

	/**
	 * 节点列表页
	 * @param nodeTitle
	 * @param p
	 * @param model
	 * @return
	 */
	@RequiresPermissions("node:list")
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(@RequestParam(value = "nodeTitle",required = false) String nodeTitle,
					   @RequestParam(value = "p",defaultValue = "1") Integer p,Model model) {
		if(StringUtils.isEmpty(nodeTitle)) nodeTitle = null;
		Page<Node> page = nodeService.pageForAdmin(nodeTitle, p, 25);
		model.addAttribute("page", page);
		model.addAttribute("nodeTitle", nodeTitle);
		model.addAttribute("p", p);
		return "/default/admin/node/list";
	}

	/**
	 * 编辑节点页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("node:edit")
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id") Integer id, Model model) {
		Node node = nodeService.findById(id);
		model.addAttribute("node", node);
		return "/default/admin/node/edit";
	}

	/**
	 * 编辑节点接口
	 * @param nodeId
	 * @param nodeTitle
	 * @param avatarNormal
	 * @param avatarLarge
	 * @param nodeDesc
	 * @return
	 */
	@RequiresPermissions("node:edit")
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> edit(Integer nodeId, String parentNodeCode, String nodeTitle, String avatarNormal, String avatarLarge, String nodeDesc) {
		ApiAssert.notNull(nodeId, "节点ID不能为空");
		ApiAssert.notEmpty(nodeTitle, "节点名称不能为空");
		if(StringUtils.isEmpty(avatarNormal)) avatarNormal = null;
		if(StringUtils.isEmpty(avatarLarge)) avatarLarge = null;
		if(StringUtils.isEmpty(nodeDesc)) nodeDesc = null;
		nodeService.update(nodeId, parentNodeCode, nodeTitle, avatarNormal, avatarLarge, nodeDesc);
		return new Result(true, "更新成功");
	}

	/**
	 * 删除节点接口
	 * @param id
	 * @return
	 */
	@RequiresPermissions("node:delete")
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> delete(Integer id){
		ApiAssert.notNull(id, "节点ID不能为空");
		nodeService.deleteById(id);
		return new Result(true, "删除成功");
	}

	/**
	 * 添加节点页面
	 * @return
	 */
	@RequiresPermissions("node:add")
	@RequestMapping(value = "/add",method = RequestMethod.GET)
	public String add() {
		return "/default/admin/node/add";
	}

	/**
	 * 添加节点接口
	 * @param node
	 * @return
	 */
	@RequiresPermissions("node:add")
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Result add(@RequestBody Node node){
		ApiAssert.notNull(node.getNodeTitle(),"节点名称不能为空");
		ApiAssert.isNull(nodeService.findByTitle(node.getNodeTitle()),"节点已存在");
		node.setNodeCode(node.getNodeTitle());
		node.setAvatarMini(node.getAvatarNormal());
		node.setUrl("/n/"+node.getNodeTitle());
		node.setCreateDate(new Date());
		node.setIsDelete(false);
		nodeService.save(node);
		return new Result(true,"添加成功");
	}

	/**
	 * 父节点页面
	 * @param nodeTitle
	 * @param p
	 * @param model
	 * @return
	 */
	@RequiresPermissions("node:add")
	@RequestMapping(value = "/parent",method = RequestMethod.GET)
	public String parentNode(@RequestParam(value = "nodeTitle",required = false) String nodeTitle,
							 @RequestParam(value = "p",defaultValue = "1") Integer p,
							 Model model){
		if(StringUtils.isEmpty(nodeTitle)) nodeTitle = null;
		Page<Node> page = nodeService.pageForAdmin(nodeTitle, p, 25);
		model.addAttribute("page", page);
		model.addAttribute("nodeTitle", nodeTitle);
		model.addAttribute("p", p);
		return "/default/admin/node/parent_node_list";
	}
}
