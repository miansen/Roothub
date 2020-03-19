package wang.miansen.roothub.modules.menu.controller;

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
import wang.miansen.roothub.common.enums.BaseErrorCodeEnum;
import wang.miansen.roothub.common.exception.BaseException;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.common.util.BeanUtils;
import wang.miansen.roothub.common.util.DateUtils;
import wang.miansen.roothub.common.util.IDGenerator;
import wang.miansen.roothub.common.util.StringUtils;
import wang.miansen.roothub.modules.menu.dto.MenuDTO;
import wang.miansen.roothub.modules.menu.model.Menu;
import wang.miansen.roothub.modules.menu.service.MenuService;
import wang.miansen.roothub.modules.menu.vo.MenuVO;

/**
 * 菜单 Admin Controller
 * 
 * @author miansen.wang
 * @date 2020-03-06
 */
@Controller
@RequestMapping(value = "/admin/menu")
public class MenuAdminController extends AbstractBaseController<Menu, MenuDTO, MenuVO> {

	@Autowired
	private MenuService menuService;

	/**
	 * 添加菜单页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@Override
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		return super.add(request, response);
	}

	/**
	 * 添加菜单接口
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@Override
	public ModelAndView save(MenuVO menuVO, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String menuName = menuVO.getMenuName();
		MenuVO parentMenuVO = menuVO.getParentMenuVO();
		if (StringUtils.isEmpty(menuName)) {
			mv.setViewName(this.getJspPrefix() + "/add");
			mv.addObject("error", "菜单的名称不能为空");
			return mv;
		}
		if (StringUtils.isEmpty(parentMenuVO.getMenuId())) {
			parentMenuVO.setMenuId(null);
		}
		menuVO.setMenuId(IDGenerator.generateID());
		menuVO.setUserId(getUser().getUserId());
		menuVO.setUserName(getUser().getUsername());
		menuVO.setCreateDate(DateUtils.formatDateTime(new Date()));
		menuService.save(getVO2DTO().apply(menuVO));
		mv.setViewName(redirect(request, "/admin/menu/list"));
		return mv;
	}

	/**
	 * 删除菜单接口
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	@Override
	public ModelAndView remove(@RequestParam(value = "id", required = true) String id, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		menuService.removeById(id);
		mv.setViewName(redirect(request, "/admin/menu/list"));
		return mv;
	}
	
	/**
	 * 编辑菜单页面
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	@Override
	public ModelAndView edit(String id, HttpServletRequest request, HttpServletResponse response) {
		return super.edit(id, request, response);
	}
	
	/**
	 * 更新菜单接口
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@Override
	public ModelAndView update(MenuVO menuVO, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String menuId = menuVO.getMenuId();
		String menuName = menuVO.getMenuName();
		MenuVO parentMenuVO = menuVO.getParentMenuVO();
		if (StringUtils.isEmpty(menuId)) {
			mv.setViewName(getJspPrefix() + "/edit");
			mv.addObject("error", "菜单的ID不能为空");
			return mv;
		}
		if (StringUtils.isEmpty(menuName)) {
			mv.setViewName(getJspPrefix() + "/edit");
			mv.addObject("error", "菜单的名称不能为空");
			return mv;
		}
		if (StringUtils.isEmpty(parentMenuVO.getMenuId())) {
			parentMenuVO.setMenuId(null);
		}
		MenuDTO menuDTO = menuService.getById(menuId);
		if (menuDTO == null) {
			throw new BaseException(BaseErrorCodeEnum.INTERNAL_ERROR);
		}
		menuVO.setUpdateDate(DateUtils.formatDateTime(new Date()));
		menuService.updateById(getVO2DTO().apply(menuVO));
		mv.setViewName(redirect(request, "/admin/menu/list"));
		return mv;
	}

	/**
	 * 菜单列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
			HttpServletRequest request,
			HttpServletResponse response) {
		String menuName = request.getParameter("menuName");
		QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByAsc("parent_menu_id", "menu_sort");
		queryWrapper.orderByDesc("create_date");
		if (StringUtils.notEmpty(menuName)) {
			queryWrapper.like("menu_name", menuName);
		}
		Page<MenuDTO> dtoPage = menuService.page(pageNumber, 10, queryWrapper);
		List<? extends MenuVO> voList = dtoPage.getList().stream().filter(Objects::nonNull).map(getDTO2VO())
				.collect(Collectors.toList());
		Page<? extends MenuVO> voPage = new Page<>(voList, dtoPage.getPageNumber(), dtoPage.getPageSize(),
				dtoPage.getTotalRow());
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getJspPrefix() + "/list");
		mv.addObject("page", voPage);
		mv.addObject("pageNumber", pageNumber);
		mv.addObject("menuName", menuName);
		return mv;
	}

	@RequestMapping(value = "/list/parent", method = RequestMethod.GET)
	public ModelAndView listParent(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
			@RequestParam(value = "sidebarName", defaultValue = "") String sidebarName, HttpServletRequest request,
			HttpServletResponse response) {
		QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
		if (StringUtils.notEmpty(sidebarName)) {
			queryWrapper.like("sidebar_name", sidebarName);
		}
		queryWrapper.orderByDesc("create_date");
		Page<MenuDTO> dtoPage = menuService.page(pageNumber, 25, queryWrapper);
		List<? extends MenuVO> voList = dtoPage.getList().stream().filter(Objects::nonNull).map(getDTO2VO())
				.collect(Collectors.toList());
		Page<? extends MenuVO> voPage = new Page<>(voList, dtoPage.getPageNumber(), dtoPage.getPageSize(),
				dtoPage.getTotalRow());
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getJspPrefix() + "/list_parent");
		mv.addObject("page", voPage);
		mv.addObject("pageNumber", pageNumber);
		mv.addObject("sidebarName", sidebarName);
		return mv;
	}

	@Override
	protected Function<? super MenuDTO, ? extends MenuVO> getDTO2VO() {
		return sidebarDTO -> (MenuVO) BeanUtils.DTO2VO(sidebarDTO, MenuVO.class);
	}

	@Override
	protected Function<? super MenuVO, ? extends MenuDTO> getVO2DTO() {
		return sidebarVO -> (MenuDTO) BeanUtils.VO2DTO(sidebarVO, MenuDTO.class);
	}

	@Override
	protected BaseService<Menu, MenuDTO> getService() {
		return menuService;
	}

	@Override
	protected String getModuleName() {
		return "menu";
	}

	@Override
	protected String getJspPrefix() {
		return "/admin/" + getModuleName();
	}

	@Override
	protected QueryWrapper<Menu> getQueryWrapper() {
		QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("create_date");
		return queryWrapper;
	}

}
