package wang.miansen.roothub.modules.sidebar.controller;

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
import wang.miansen.roothub.modules.sidebar.dto.SidebarDTO;
import wang.miansen.roothub.modules.sidebar.model.Sidebar;
import wang.miansen.roothub.modules.sidebar.service.SidebarService;
import wang.miansen.roothub.modules.sidebar.vo.SidebarVO;

/**
 * 侧边栏 Admin Controller
 * 
 * @author miansen.wang
 * @date 2020-03-06
 */
@Controller
@RequestMapping(value = "/admin/sidebar")
public class SidebarAdminController extends AbstractBaseController<Sidebar, SidebarDTO, SidebarVO> {

	@Autowired
	private SidebarService sidebarService;

	/**
	 * 返回添加侧边栏页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@Override
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		return super.add(request, response);
	}

	/**
	 * 添加侧边栏接口
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@Override
	public ModelAndView save(SidebarVO sidebarVO, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String sidebarName = sidebarVO.getSidebarName();
		SidebarVO parentSidebarVO = sidebarVO.getParentSidebarVO();
		if (StringUtils.isEmpty(sidebarName)) {
			mv.setViewName(this.getJspPrefix() + "/add");
			mv.addObject("error", "侧边栏的名字不能为空");
			return mv;
		}
		if (StringUtils.isEmpty(parentSidebarVO.getSidebarId())) {
			parentSidebarVO.setSidebarId(null);
		}
		sidebarVO.setPrimaryKey(IDGenerator.generateID());
		sidebarVO.setUserId(getUser().getUserId());
		sidebarVO.setUserName(getUser().getUserName());
		sidebarVO.setCreateDate(DateUtils.formatDateTime(new Date()));
		sidebarService.save(getVO2DTO().apply(sidebarVO));
		mv.setViewName(redirect(request, "/admin/sidebar/list"));
		return mv;
	}

	/**
	 * 删除侧边栏接口
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	@Override
	public ModelAndView remove(@RequestParam(value = "id", required = true) String id, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		sidebarService.removeById(id);
		mv.setViewName(redirect(request, "/admin/sidebar/list"));
		return mv;
	}
	
	/**
	 * 返回编辑侧边栏页面
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	@Override
	public ModelAndView edit(String id, HttpServletRequest request, HttpServletResponse response) {
		return super.edit(id, request, response);
	}
	
	/**
	 * 更新侧边栏接口
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@Override
	public ModelAndView update(SidebarVO sidebarVO, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String sidebarId = sidebarVO.getSidebarId();
		String sidebarName = sidebarVO.getSidebarName();
		SidebarVO parentSidebarVO = sidebarVO.getParentSidebarVO();
		if (StringUtils.isEmpty(sidebarId)) {
			mv.setViewName(getJspPrefix() + "/edit");
			mv.addObject("error", "侧边栏的ID不能为空");
			return mv;
		}
		if (StringUtils.isEmpty(sidebarName)) {
			mv.setViewName(getJspPrefix() + "/edit");
			mv.addObject("error", "侧边栏的名称不能为空");
			return mv;
		}
		if (StringUtils.isEmpty(parentSidebarVO.getSidebarId())) {
			parentSidebarVO.setSidebarId(null);
		}
		SidebarDTO sidebarDTO = sidebarService.getById(sidebarId);
		if (sidebarDTO == null) {
			throw new BaseException(BaseErrorCodeEnum.INTERNAL_ERROR);
		}
		sidebarVO.setUpdateDate(DateUtils.formatDateTime(new Date()));
		sidebarService.updateById(getVO2DTO().apply(sidebarVO));
		mv.setViewName(redirect(request, "/admin/sidebar/list"));
		return mv;
	}

	/**
	 * 侧边栏列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
			HttpServletRequest request,
			HttpServletResponse response) {
		String sidebarName = request.getParameter("sidebarName");
		QueryWrapper<Sidebar> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByAsc("parent_sidebar_id", "sidebar_sort");
		queryWrapper.orderByDesc("create_date");
		if (StringUtils.notEmpty(sidebarName)) {
			queryWrapper.like("sidebar_name", sidebarName);
		}
		Page<SidebarDTO> dtoPage = sidebarService.page(pageNumber, 10, queryWrapper);
		List<? extends SidebarVO> voList = dtoPage.getList().stream().filter(Objects::nonNull).map(getDTO2VO())
				.collect(Collectors.toList());
		Page<? extends SidebarVO> voPage = new Page<>(voList, dtoPage.getPageNumber(), dtoPage.getPageSize(),
				dtoPage.getTotalRow());
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getJspPrefix() + "/list");
		mv.addObject("page", voPage);
		mv.addObject("pageNumber", pageNumber);
		mv.addObject("sidebarName", sidebarName);
		return mv;
	}

	@RequestMapping(value = "/list/parent", method = RequestMethod.GET)
	public ModelAndView listParent(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
			@RequestParam(value = "sidebarName", defaultValue = "") String sidebarName, HttpServletRequest request,
			HttpServletResponse response) {
		QueryWrapper<Sidebar> queryWrapper = new QueryWrapper<>();
		if (StringUtils.notEmpty(sidebarName)) {
			queryWrapper.like("sidebar_name", sidebarName);
		}
		queryWrapper.orderByDesc("create_date");
		Page<SidebarDTO> dtoPage = sidebarService.page(pageNumber, 25, queryWrapper);
		List<? extends SidebarVO> voList = dtoPage.getList().stream().filter(Objects::nonNull).map(getDTO2VO())
				.collect(Collectors.toList());
		Page<? extends SidebarVO> voPage = new Page<>(voList, dtoPage.getPageNumber(), dtoPage.getPageSize(),
				dtoPage.getTotalRow());
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getJspPrefix() + "/list_parent");
		mv.addObject("page", voPage);
		mv.addObject("pageNumber", pageNumber);
		mv.addObject("sidebarName", sidebarName);
		return mv;
	}

	@Override
	protected Function<? super SidebarDTO, ? extends SidebarVO> getDTO2VO() {
		return sidebarDTO -> (SidebarVO) BeanUtils.DTO2VO(sidebarDTO, SidebarVO.class);
	}

	@Override
	protected Function<? super SidebarVO, ? extends SidebarDTO> getVO2DTO() {
		return sidebarVO -> (SidebarDTO) BeanUtils.VO2DTO(sidebarVO, SidebarDTO.class);
	}

	@Override
	protected BaseService<Sidebar, SidebarDTO> getService() {
		return sidebarService;
	}

	@Override
	protected String getModuleName() {
		return "sidebar";
	}

	@Override
	protected String getJspPrefix() {
		return "/admin/" + getModuleName();
	}

	@Override
	protected QueryWrapper<Sidebar> getQueryWrapper() {
		QueryWrapper<Sidebar> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("create_date");
		return queryWrapper;
	}

}
