package wang.miansen.roothub.modules.app.controller;

import java.util.Date;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import wang.miansen.roothub.common.controller.AbstractBaseController;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.common.util.BeanUtils;
import wang.miansen.roothub.common.util.DateUtils;
import wang.miansen.roothub.common.util.IDGenerator;
import wang.miansen.roothub.common.util.StringUtils;
import wang.miansen.roothub.modules.app.dto.AppCategoryDTO;
import wang.miansen.roothub.modules.app.enums.AppCategoryErrorCodeEnum;
import wang.miansen.roothub.modules.app.exception.AppCategoryException;
import wang.miansen.roothub.modules.app.model.AppCategory;
import wang.miansen.roothub.modules.app.service.AppCategoryService;
import wang.miansen.roothub.modules.app.vo.AppCategoryVO;

/**
 * 应用类别 Admin Controller
 * 
 * @author miansen.wang
 * @date 2020-03-08
 */
@Controller
@RequestMapping(value = "/admin/app/category")
public class AppCategoryAdminController extends AbstractBaseController<AppCategory, AppCategoryDTO, AppCategoryVO> {

	@Autowired
	private AppCategoryService appCategoryService;

	/**
	 * 返回添加应用类别页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@Override
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		return super.add(request, response);
	}

	/**
	 * 返回编辑应用类别页面
	 */
	@Override
	public ModelAndView edit(@RequestParam(value = "id", defaultValue = "1") String id, HttpServletRequest request,
			HttpServletResponse response) {
		return super.edit(id, request, response);
	}

	/**
	 * 保存应用类别
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@Override
	public ModelAndView save(AppCategoryVO appCategoryVO, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String appCategoryName = appCategoryVO.getAppCategoryName();
		if (StringUtils.isEmpty(appCategoryName)) {
			mv.setViewName(this.getJspPrefix() + "/add");
			mv.addObject("error", "应用类别的名称不能为空");
			return mv;
		}
		QueryWrapper<AppCategory> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("app_category_name", appCategoryName);
		AppCategoryDTO appCategoryDTO = appCategoryService.getOne(queryWrapper);
		if (appCategoryDTO != null) {
			mv.setViewName(this.getJspPrefix() + "/add");
			mv.addObject("error", "应用类别的名称已存在，请另换一个名称。");
			return mv;
		}
		appCategoryVO.setAppCategoryId(IDGenerator.generateID());
		appCategoryVO.setUserId(getUser().getUserId().toString());
		appCategoryVO.setUserName(getUser().getUsername());
		appCategoryVO.setCreateDate(DateUtils.formatDateTime(new Date()));
		appCategoryService.save(getVO2DTO().apply(appCategoryVO));
		mv.setViewName(redirect(request, "/admin/app/category/list"));
		return mv;
	}

	/**
	 * 更新应用类别
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@Override
	public ModelAndView update(AppCategoryVO appCategoryVO, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String appCategoryName = appCategoryVO.getAppCategoryName();
		if (StringUtils.isEmpty(appCategoryName)) {
			mv.setViewName(this.getJspPrefix() + "/edit");
			mv.addObject("error", "应用类别的名称不能为空");
			mv.addObject("appCategoryVO", appCategoryVO);
			return mv;
		}
		QueryWrapper<AppCategory> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("app_category_name", appCategoryName);
		AppCategoryDTO appCategoryDTO = appCategoryService.getOne(queryWrapper);
		if (appCategoryDTO != null) {
			mv.setViewName(this.getJspPrefix() + "/add");
			mv.addObject("error", "应用类别的名称已存在，请另换一个名称。");
			mv.addObject("appCategoryVO", appCategoryVO);
			return mv;
		}
		String appCategoryId = appCategoryVO.getAppCategoryId();
		if (StringUtils.isEmpty(appCategoryId)) {
			throw new AppCategoryException(AppCategoryErrorCodeEnum.ID_MISSING);
		}
		appCategoryDTO = appCategoryService.getById(appCategoryId);
		if (appCategoryDTO == null) {
			throw new AppCategoryException(AppCategoryErrorCodeEnum.INVALIDATE_ID);
		}
		appCategoryVO.setUserId(appCategoryDTO.getUserDTO().getUserId());
		appCategoryVO.setUserName(appCategoryDTO.getUserDTO().getUserName());
		appCategoryVO.setUpdateDate(DateUtils.formatDateTime(new Date()));
		appCategoryService.updateById(getVO2DTO().apply(appCategoryVO));
		mv.setViewName(redirect(request, "/admin/app/category/list"));
		return mv;
	}

	/**
	 * 应用类别列表
	 */
	@Override
	public ModelAndView list(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
			HttpServletRequest request, HttpServletResponse response) {
		return super.list(pageNumber, request, response);
	}

	@Override
	protected Function<? super AppCategoryDTO, ? extends AppCategoryVO> getDTO2VO() {
		return dto -> (AppCategoryVO) BeanUtils.DTO2VO(dto, AppCategoryVO.class);
	}

	@Override
	protected Function<? super AppCategoryVO, ? extends AppCategoryDTO> getVO2DTO() {
		return vo -> (AppCategoryDTO) BeanUtils.VO2DTO(vo, AppCategoryDTO.class);
	}

	@Override
	protected BaseService<AppCategory, AppCategoryDTO> getService() {
		return appCategoryService;
	}

	@Override
	protected String getModuleName() {
		return "app";
	}

	@Override
	protected String getJspPrefix() {
		return "/admin/" + getModuleName() + "/category";
	}

	@Override
	protected QueryWrapper<AppCategory> getQueryWrapper() {
		QueryWrapper<AppCategory> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("create_date");
		return queryWrapper;
	}

}
