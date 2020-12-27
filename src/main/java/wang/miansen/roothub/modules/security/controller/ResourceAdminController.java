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
import wang.miansen.roothub.modules.security.dto.ResourceDTO;
import wang.miansen.roothub.modules.security.dto.ResourceTypeDTO;
import wang.miansen.roothub.modules.security.model.Resource;
import wang.miansen.roothub.modules.security.service.ResourceService2;
import wang.miansen.roothub.modules.security.service.ResourceTypeService;
import wang.miansen.roothub.modules.security.vo.ResourceVO;

/**
 * 资源 Admin Controller
 * 
 * @author miansen.wang
 * @date 2020-03-13
 */
@Controller
@RequestMapping(value = "/admin/security/resource")
public class ResourceAdminController extends AbstractBaseController<Resource, ResourceDTO, ResourceVO> {

	@Autowired
	private ResourceService2 resourceService;
	
	@Autowired
	private ResourceTypeService resourceTypeService;
	
	/**
	 * 返回资源添加页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@Override
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		List<ResourceTypeDTO> resourceTypeDTOList = resourceTypeService.list();
		ModelAndView mv = new ModelAndView();
		mv.setViewName(getJspPrefix() + "/add");
		mv.addObject("resourceTypeDTOList", resourceTypeDTOList);
		return mv;
	}
	
	/**
	 * 资源添加接口
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@Override
	public ModelAndView save(ResourceVO resourceVO, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String resourceName = resourceVO.getResourceName();
		String resourceValue = resourceVO.getResourceValue();
		if (StringUtils.isEmpty(resourceName)) {
			mv.setViewName(this.getJspPrefix() + "/add");
			mv.addObject("error", "资源名称不能为空");
			return mv;
		}
		if (StringUtils.isEmpty(resourceValue)) {
			mv.setViewName(this.getJspPrefix() + "/add");
			mv.addObject("error", "资源值不能为空");
			return mv;
		}
		QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("resource_name", resourceName);
		ResourceDTO resourceDTO = resourceService.getOne(queryWrapper);
		if (resourceDTO != null) {
			mv.setViewName(this.getJspPrefix() + "/add");
			mv.addObject("error", "资源名称已存在");
			return mv;
		}
		resourceVO.setResourceId(IDGenerator.generateID());
		resourceVO.setCreateDate(DateUtils.formatDateTime(new Date()));
		resourceService.save(getVO2DTO().apply(resourceVO));
		mv.setViewName(redirect(request, "/admin/security/resource/list"));
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
		resourceService.removeById(id);
		mv.setViewName(redirect(request, "/admin/security/resource/list"));
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
	 * 返回资源列表页面
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@Override
	public ModelAndView list(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber, HttpServletRequest request, HttpServletResponse response) {
		String resourceName = request.getParameter("resourceName");
		String resourceCategoryName = request.getParameter("resourceCategoryName");
		Page<ResourceDTO> dtoPage = resourceService.pageByNameAndCategoryName(pageNumber, resourceName, resourceCategoryName);
		List<ResourceVO> voList = dtoPage.getList().stream().filter(Objects::nonNull).map(getDTO2VO()).collect(Collectors.toList());
		Page<ResourceVO> voPage = new Page<>(voList, dtoPage.getPageNumber(), dtoPage.getPageSize(), dtoPage.getTotalRow());
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getJspPrefix() + "/list");
		mv.addObject("page", voPage);
		mv.addObject("pageNumber", pageNumber);
		mv.addObject("resourceName", resourceName);
		mv.addObject("resourceCategoryName", resourceCategoryName);
		return mv;
	}

	@Override
	protected Function<? super ResourceDTO, ? extends ResourceVO> getDTO2VO() {
		return dto -> (ResourceVO) BeanUtils.DTO2VO(dto, ResourceVO.class);
	}

	@Override
	protected Function<? super ResourceVO, ? extends ResourceDTO> getVO2DTO() {
		return vo -> (ResourceDTO) BeanUtils.VO2DTO(vo, ResourceDTO.class);
	}

	@Override
	protected BaseService<Resource, ResourceDTO> getService() {
		return resourceService;
	}

	@Override
	protected String getModuleName() {
		return "security";
	}
	
	@Override
	protected String getJspPrefix() {
		return "/" + getModuleName() + "/resource";
	}

	@Override
	protected QueryWrapper<Resource> getQueryWrapper() {
		QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("create_date");
		return queryWrapper;
	}

}
