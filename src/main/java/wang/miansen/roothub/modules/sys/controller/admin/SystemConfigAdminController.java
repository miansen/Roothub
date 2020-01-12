package wang.miansen.roothub.modules.sys.controller.admin;

import java.util.List;
import java.util.Map;

import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.modules.sys.model.SystemConfig;
import wang.miansen.roothub.modules.sys.service.SystemConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@Qualifier("systemConfigServiceImpl")
	private SystemConfigService systemConfigService;

	/**
	 * 获取系统配置
	 * @param pid:父级ID
	 * @param index:当前点击的<li>标签
	 * @param model
	 * @return
	 */
	@RequiresPermissions("system:edit")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "pid", defaultValue = "1") Integer pid, @RequestParam(value = "index", defaultValue = "0") Integer index, Model model) {
		// 父级配置
		SystemConfig systemConfig = systemConfigService.getById(pid);
		// 子配置
		List<SystemConfig> systemConfigs = systemConfigService.getByPid(pid);
		model.addAttribute("systemConfig", systemConfig);
		model.addAttribute("systemConfigs", systemConfigs);
		model.addAttribute("index", index);
		return "/default/admin/system/edit";
	}

	/**
	 * 根据父级ID获取对应的子元素，主要用于获取上传配置
	 * @param pid:父级ID
	 * @return
	 */
	@RequiresPermissions("system:edit")
	@RequestMapping(value = "/upload/list", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<SystemConfig>> list(Integer pid) {
		List<SystemConfig> systemConfigs = systemConfigService.getByPid(pid);
		systemConfigs.forEach(systemConfig -> {
			if(systemConfig.getKey().equals("accessKeyId") || systemConfig.getKey().equals("accessKeySecret")) {
				systemConfig.setValue("******");
			}
		});
		return new Result<>(true, systemConfigs);

	}

	@RequiresPermissions("system:edit")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> edit(@RequestBody List<Map<String, String>> list) {
		systemConfigService.update(list);
		return new Result(true, "success");
	}
}
