package cn.roothub.web.admin;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.roothub.dto.Result;
import cn.roothub.entity.SystemConfig;
import cn.roothub.service.SystemConfigService;

/**
 * <p>
 * </p>
 * 
 * @author: miansen.wang
 * @date: 2019-03-11
 */
@Controller
@RequestMapping("/admin/system")
public class SystemConfigAdminController {

	@Autowired
	private SystemConfigService systemConfigService;

	@RequiresPermissions("system:edit")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "pid", defaultValue = "1") Integer pid, @RequestParam(value = "index", defaultValue = "0") Integer index, Model model) {
		SystemConfig systemConfig = systemConfigService.getById(pid);
		List<SystemConfig> systemConfigs = systemConfigService.getByPid(pid);
		model.addAttribute("systemConfig", systemConfig);
		model.addAttribute("systemConfigs", systemConfigs);
		model.addAttribute("index", index);
		return "admin/system/edit";
	}

	/**
	 * 获取上传配置并更新
	 * @param pid
	 * @return
	 */
	@RequiresPermissions("system:edit")
	@RequestMapping(value = "/upload/list", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<SystemConfig>> list(Integer pid) {
		List<SystemConfig> systemConfigs = systemConfigService.getByPid(pid);
		return new Result<>(true, systemConfigs);

	}

	@RequiresPermissions("system:edit")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> edit(@RequestBody List<Map<String, String>> list) {
		systemConfigService.update(list);
		return new Result<>(true, "success");
	}
}
