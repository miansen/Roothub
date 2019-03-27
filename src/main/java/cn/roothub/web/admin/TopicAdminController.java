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

import cn.roothub.config.SiteConfig;
import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.Result;
import cn.roothub.entity.Topic;
import cn.roothub.service.TopicService;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-02
 */
@Controller
@RequestMapping("/admin/topic")
public class TopicAdminController {

	@Autowired
	private TopicService topicService;
	@Autowired
	private SiteConfig siteConfig;
	
	@RequiresPermissions("topic:list")
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(String startDate, String endDate,String author, Model model,@RequestParam(defaultValue = "1") Integer p) {
		if (StringUtils.isEmpty(startDate)) startDate = null;
	    if (StringUtils.isEmpty(endDate)) endDate = null;
	    if (StringUtils.isEmpty(author)) author = null;
		PageDataBody<Topic> page = topicService.pageForAdmin(author, startDate, endDate, p, 25);
		model.addAttribute("page", page);
	    model.addAttribute("startDate", startDate);
	    model.addAttribute("endDate", endDate);
	    model.addAttribute("author", author);
	    return "admin/topic/list";
	}
	
	@RequiresPermissions("topic:top")
	@RequestMapping(value = "/top",method = RequestMethod.GET)
	@ResponseBody
	public Result<String> top(@RequestParam("id") Integer id){
		Topic topic = topicService.findById(id);
		topic.setTop(!topic.getTop());
		topicService.updateTopic(topic);
		return new Result<>(true, "更新成功！");
	}
	
	@RequiresPermissions("topic:good")
	@RequestMapping(value = "/good",method = RequestMethod.GET)
	@ResponseBody
	public Result<String> good(@RequestParam("id") Integer id){
		Topic topic = topicService.findById(id);
		topic.setGood(!topic.getGood());
		topicService.updateTopic(topic);
		return new Result<>(true, "更新成功！");
	}
	
	@RequiresPermissions("topic:delete")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@ResponseBody
	public Result<String> delete(@RequestParam("id") Integer id){
		Topic topic = topicService.findById(id);
		topic.setIsDelete(!topic.getIsDelete());
		topicService.updateTopic(topic);
		return new Result<>(true, "删除成功！");
	}
}
