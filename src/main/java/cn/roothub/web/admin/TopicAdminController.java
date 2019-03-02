package cn.roothub.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.roothub.config.SiteConfig;
import cn.roothub.dto.PageDataBody;
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
}
