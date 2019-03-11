package cn.roothub.web.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.roothub.entity.SystemConfig;
import cn.roothub.service.SystemConfigService;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-11
 */
@Controller
@RequestMapping("/admin/system")
public class SystemConfigAdminController {

	@Autowired
	private SystemConfigService systemConfigService;
	
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public String edit(@RequestParam(value = "key",defaultValue = "基础配置") String key,Model model) {
		List<SystemConfig> systemConfigs = (List<SystemConfig>) systemConfigService.getAllMap().get(key);
		model.addAttribute("systemConfigs", systemConfigs);
		return "admin/system/edit";
	}
}
