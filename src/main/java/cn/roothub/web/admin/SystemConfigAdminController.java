package cn.roothub.web.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.roothub.dto.Result;
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
	public String edit(@RequestParam(value = "pid",defaultValue = "1") Integer pid,Model model) {
		SystemConfig systemConfig = systemConfigService.getById(pid);
		List<SystemConfig> systemConfigs = systemConfigService.getByPid(pid);
		model.addAttribute("systemConfig", systemConfig);
		model.addAttribute("systemConfigs", systemConfigs);
		model.addAttribute("pid", pid-1);
		return "admin/system/edit";
	}
	
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public Result<List<SystemConfig>> list(Integer pid) {
		List<SystemConfig> systemConfigs = systemConfigService.getByPid(pid);
		return new Result<>(true, systemConfigs);
				
	}
}
