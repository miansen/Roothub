package wang.miansen.roothub.modules.security.controller;

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
import wang.miansen.roothub.common.util.IDGenerator;
import wang.miansen.roothub.common.util.StringUtils;
import wang.miansen.roothub.modules.security.dto.ResourceCategoryDTO;
import wang.miansen.roothub.modules.security.model.ResourceCategory;
import wang.miansen.roothub.modules.security.service.ResourceCategoryService;
import wang.miansen.roothub.modules.security.vo.ResourceCategoryVO;

/**
 * 资源类别 Admin Controller
 * 
 * @author miansen.wang
 * @date 2020-03-14
 */
@Controller
@RequestMapping(value = "/admin/security/resource/category")
public class ResourceCategoryAdminController
		extends AbstractBaseController<ResourceCategory, ResourceCategoryDTO, ResourceCategoryVO> {

	@Autowired
	private ResourceCategoryService resourceCategoryService;

	/**
	 * 返回添加资源类别页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@Override
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		return super.add(request, response);
	}

	/**
	 * 添加资源类别接口
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@Override
	public ModelAndView save(ResourceCategoryVO resourceCategoryVO, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String resourceCategoryName = resourceCategoryVO.getResourceCategoryName();
		if (StringUtils.isEmpty(resourceCategoryName)) {
			mv.setViewName(this.getJspPrefix() + "/add");
			mv.addObject("error", "资源类别名称不能为空");
			return mv;
		}
		QueryWrapper<ResourceCategory> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("resource_category_name", resourceCategoryName);
		ResourceCategoryDTO resourceCategoryDTO = resourceCategoryService.getOne(queryWrapper);
		if (resourceCategoryDTO != null) {
			mv.setViewName(this.getJspPrefix() + "/add");
			mv.addObject("error", "资源类别名称已存在");
			return mv;
		}
		resourceCategoryVO.setResourceCategoryId(IDGenerator.generateID());
		resourceCategoryVO.setUserId(getUser().getUserId());
		resourceCategoryVO.setUserName(getUser().getUserName());
		resourceCategoryVO.setCreateDate(DateUtils.formatDateTime(new Date()));
		resourceCategoryService.save(getVO2DTO().apply(resourceCategoryVO));
		mv.setViewName(redirect(request, "/admin/security/resource/category/list"));
		return mv;
	}

	/**
	 * 删除资源类别接口
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	@Override
	public ModelAndView remove(@RequestParam(value = "id", required = true) String id, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		resourceCategoryService.removeById(id);
		mv.setViewName(redirect(request, "/admin/security/resource/category/list"));
		return mv;
	}

	/**
	 * 返回编辑页面
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	@Override
	public ModelAndView edit(@RequestParam(value = "id", required = true) String id, HttpServletRequest request,
			HttpServletResponse response) {
		return super.edit(id, request, response);
	}

	/**
	 * 资源类别列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@Override
	public ModelAndView list(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
			HttpServletRequest request, HttpServletResponse response) {
		String resourceCategoryName = request.getParameter("resourceCategoryName");
		QueryWrapper<ResourceCategory> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("create_date");
		if (StringUtils.notEmpty(resourceCategoryName)) {
			queryWrapper.like("resource_category_name", resourceCategoryName);
		}
		Page<ResourceCategoryDTO> dtoPage = resourceCategoryService.page(pageNumber, 25, queryWrapper);
		List<ResourceCategoryDTO> dtoList = dtoPage.getList();
		List<ResourceCategoryVO> voList = dtoList.stream().filter(Objects::nonNull).map(getDTO2VO())
				.collect(Collectors.toList());
		Page<ResourceCategoryVO> voPage = new Page<>(voList, dtoPage.getPageNumber(), dtoPage.getPageSize(),
				dtoPage.getTotalRow());
		ModelAndView mv = new ModelAndView();
		mv.setViewName(getJspPrefix() + "/list");
		mv.addObject("page", voPage);
		mv.addObject("pageNumber", pageNumber);
		mv.addObject("resourceCategoryName", resourceCategoryName);
		return mv;
	}

	@Override
	protected Function<? super ResourceCategoryDTO, ? extends ResourceCategoryVO> getDTO2VO() {
		return dto -> (ResourceCategoryVO) BeanUtils.DTO2VO(dto, ResourceCategoryVO.class);
	}

	@Override
	protected Function<? super ResourceCategoryVO, ? extends ResourceCategoryDTO> getVO2DTO() {
		return vo -> (ResourceCategoryDTO) BeanUtils.VO2DTO(vo, ResourceCategoryDTO.class);
	}

	@Override
	protected BaseService<ResourceCategory, ResourceCategoryDTO> getService() {
		return resourceCategoryService;
	}

	@Override
	protected String getModuleName() {
		return "security";
	}

	@Override
	protected String getJspPrefix() {
		return "/" + getModuleName() + "/resource/category";
	}

	@Override
	protected QueryWrapper<ResourceCategory> getQueryWrapper() {
		QueryWrapper<ResourceCategory> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("create_date");
		return queryWrapper;
	}

}
