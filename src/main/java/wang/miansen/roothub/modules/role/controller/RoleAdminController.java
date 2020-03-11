package wang.miansen.roothub.modules.role.controller;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.controller.AbstractBaseController;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.common.util.BeanUtils;
import wang.miansen.roothub.common.util.DateUtils;
import wang.miansen.roothub.common.util.StringUtils;
import wang.miansen.roothub.modules.role.dto.RoleDTO;
import wang.miansen.roothub.modules.role.model.Role;
import wang.miansen.roothub.modules.role.service.RoleService;
import wang.miansen.roothub.modules.role.vo.RoleVO;

/**
 * 角色后台管理 Controller
 * 
 * @author miansen.wang
 * @date 2020-02-23
 */
@Controller
@RequestMapping("/admin/role")
public class RoleAdminController extends AbstractBaseController<Role, RoleDTO, RoleVO> {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@Override
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		return super.add(request, response);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@Override
	public ModelAndView save(RoleVO roleVO, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String roleName = roleVO.getRoleName();
		if (StringUtils.isEmpty(roleName)) {
			mv.setViewName(this.getJspPrefix() + "/add");
			mv.addObject("error", "角色名称不能为空");
			return mv;
		}
		QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("role_name", roleVO.getRoleName());
		RoleDTO roleDTO = this.roleService.getOne(queryWrapper);
		if (roleDTO != null) {
			mv.setViewName(this.getJspPrefix() + "/add");
			mv.addObject("error", "角色名称已存在");
			return mv;
		}
		roleVO.setCreateDate(DateUtils.formatDateTime(new Date()));
		mv = super.save(roleVO, request, response);
		mv.setViewName(redirect(request, "/admin/role/list"));
		return mv;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@Override
	public ModelAndView list(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
			HttpServletRequest request, HttpServletResponse response) {
		String roleName = request.getParameter("roleName");
		QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
		if (StringUtils.notEmpty(roleName)) {
			queryWrapper.like("role_name", roleName);
		}
		queryWrapper.orderByDesc("create_date");
		Page<RoleDTO> dtoPage = this.getService().page(pageNumber, 25, this.getQueryWrapper());
		List<? extends RoleVO> voList = dtoPage.getList().stream().map(getDTO2VO()).collect(Collectors.toList());
		Page<? extends RoleVO> voPage = new Page<>(voList, dtoPage.getPageNumber(), dtoPage.getPageSize(),
				dtoPage.getTotalRow());
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getJspPrefix() + "/list");
		mv.addObject("page", voPage);
		mv.addObject("pageNumber", pageNumber);
		mv.addObject("roleName", roleName);
		return mv;
	}
	
	@Override
	protected Function<? super RoleDTO, ? extends RoleVO> getDTO2VO() {
		return roleDTO -> (RoleVO) BeanUtils.DTO2VO(roleDTO, RoleVO.class);
	}

	@Override
	protected Function<? super RoleVO, ? extends RoleDTO> getVO2DTO() {
		return roleVO -> (RoleDTO) BeanUtils.VO2DTO(roleVO, RoleDTO.class);
	}

	@Override
	protected BaseService<Role, RoleDTO> getService() {
		return roleService;
	}

	@Override
	protected String getModuleName() {
		return "role";
	}

	@Override
	protected String getJspPrefix() {
		return "/admin/" + getModuleName();
	}

	@Override
	protected QueryWrapper<Role> getQueryWrapper() {
		QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("create_date");
		return queryWrapper;
	}

}
