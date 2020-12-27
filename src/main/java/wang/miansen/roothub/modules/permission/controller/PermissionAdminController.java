package wang.miansen.roothub.modules.permission.controller;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import wang.miansen.roothub.modules.permission.dto.PermissionDTO;
import wang.miansen.roothub.modules.permission.model.Permission;
import wang.miansen.roothub.modules.permission.service.PermissionBakService;
import wang.miansen.roothub.modules.permission.vo.PermissionVO;

/**
 * 权限后台 Controller
 * 
 * @author miansen.wang
 * @date 2020-02-29
 * @since 3.0
 */
@Controller
@RequestMapping("/admin/permission")
public class PermissionAdminController extends AbstractBaseController<Permission, PermissionDTO, PermissionVO> {

	@Autowired
	private PermissionBakService permissionService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@Override
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		return super.add(request, response);
	}

	@RequestMapping(value = "/add/parent", method = RequestMethod.GET)
	public ModelAndView addParent(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getJspPrefix() + "/add_parent");
		return mv;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@Override
	public ModelAndView save(PermissionVO permissionVO, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String permissionName = permissionVO.getPermissionName();
		String permissionValue = permissionVO.getPermissionValue();
		if (StringUtils.isEmpty(permissionName)) {
			mv.setViewName(this.getJspPrefix() + "/add");
			mv.addObject("error", "权限名不能为空");
			return mv;
		}
		if (StringUtils.isEmpty(permissionValue)) {
			mv.setViewName(this.getJspPrefix() + "/add");
			mv.addObject("error", "权限值不能为空");
			return mv;
		}
		QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("permission_name", permissionName);
		PermissionDTO permissionDTO = permissionService.getOne(queryWrapper);
		if (permissionDTO != null) {
			mv.setViewName(this.getJspPrefix() + "/add");
			mv.addObject("error", "权限名已存在");
			return mv;
		}
		permissionVO.setCreateDate(DateUtils.formatDateTime(new Date()));
		mv = super.save(permissionVO, request, response);
		mv.setViewName(redirect(request, "/admin/permission/list"));
		return mv;
	}

	@RequestMapping(value = "/save/parent", method = RequestMethod.POST)
	public ModelAndView saveParent(PermissionVO permissionVO, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String permissionName = permissionVO.getPermissionName();
		if (StringUtils.isEmpty(permissionName)) {
			mv.setViewName(this.getJspPrefix() + "/add_parent");
			mv.addObject("error", "权限名不能为空");
			return mv;
		}
		QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("permission_name", permissionName);
		PermissionDTO permissionDTO = permissionService.getOne(queryWrapper);
		if (permissionDTO != null) {
			mv.setViewName(this.getJspPrefix() + "/add_parent");
			mv.addObject("error", "权限名已存在");
			return mv;
		}
		permissionVO.setCreateDate(DateUtils.formatDateTime(new Date()));
		mv = super.save(permissionVO, request, response);
		mv.setViewName(redirect(request, "/admin/permission/list"));
		return mv;
	}


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@Override
	public ModelAndView list(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
			HttpServletRequest request, HttpServletResponse response) {
		String permissionName = request.getParameter("permissionName");
		QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
		if (StringUtils.notEmpty(permissionName)) {
			queryWrapper.like("permission_name", permissionName);
		}
		queryWrapper.orderByDesc("create_date");
		Page<PermissionDTO> dtoPage = this.getService().page(pageNumber, 25, queryWrapper);
		List<? extends PermissionVO> voList = dtoPage.getList().stream().filter(Objects::nonNull).map(getDTO2VO())
				.collect(Collectors.toList());
		Page<? extends PermissionVO> voPage = new Page<>(voList, dtoPage.getPageNumber(), dtoPage.getPageSize(),
				dtoPage.getTotalRow());
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getJspPrefix() + "/list");
		mv.addObject("page", voPage);
		mv.addObject("pageNumber", pageNumber);
		mv.addObject("permissionName", permissionName);
		return mv;
	}

	/**
	 * 父权限页面
	 * 
	 * @param pageNumber
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list/parent", method = RequestMethod.GET)
	public ModelAndView listParent(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
			@RequestParam(value = "permissionName", defaultValue = "") String permissionName,
			HttpServletRequest request, HttpServletResponse response) {
		QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
		if (StringUtils.notEmpty(permissionName)) {
			queryWrapper.like("permission_name", permissionName);
		}
		queryWrapper.isNull("parent_permission_id");
		queryWrapper.orderByDesc("create_date");
		Page<PermissionDTO> dtoPage = this.getService().page(pageNumber, 25, queryWrapper);
		List<? extends PermissionVO> voList = dtoPage.getList().stream().filter(Objects::nonNull).map(getDTO2VO())
				.collect(Collectors.toList());
		Page<? extends PermissionVO> voPage = new Page<>(voList, dtoPage.getPageNumber(), dtoPage.getPageSize(),
				dtoPage.getTotalRow());
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getJspPrefix() + "/list_parent");
		mv.addObject("page", voPage);
		mv.addObject("pageNumber", pageNumber);
		mv.addObject("permissionName", permissionName);
		return mv;
	}

	@Override
	protected Function<? super PermissionDTO, ? extends PermissionVO> getDTO2VO() {
		return dto -> (PermissionVO) BeanUtils.DTO2VO(dto, PermissionVO.class);
	}

	@Override
	protected Function<? super PermissionVO, ? extends PermissionDTO> getVO2DTO() {
		return vo -> (PermissionDTO) BeanUtils.VO2DTO(vo, PermissionDTO.class);
	}

	@Override
	protected BaseService<Permission, PermissionDTO> getService() {
		return permissionService;
	}

	@Override
	protected String getModuleName() {
		return "permission";
	}

	@Override
	protected String getJspPrefix() {
		return "/admin/" + getModuleName();
	}

	@Override
	protected QueryWrapper<Permission> getQueryWrapper() {
		QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("parent_permission_id", "create_date");
		return queryWrapper;
	}
}
