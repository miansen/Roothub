package wang.miansen.roothub.modules.role.controller;

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
import wang.miansen.roothub.modules.role.dto.RoleDTO;
import wang.miansen.roothub.modules.role.model.Role;
import wang.miansen.roothub.modules.role.service.RoleService;
import wang.miansen.roothub.modules.role.vo.RoleVO;

/**
 * @author miansen.wang
 * @date 2020-02-23
 */
@Controller
@RequestMapping("/admin/role")
public class RoleAdminController extends AbstractBaseController<Role, RoleDTO, RoleVO> {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@Override
	public ModelAndView list(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
			HttpServletRequest request, HttpServletResponse response) {
		return super.list(pageNumber, request, response);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@Override
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		return super.add(request, response);
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
	protected String getFrontPrefix() {
		return super.applicationConfig.getAdminPath() + "/" + getModuleName();
	}

	@Override
	protected QueryWrapper<Role> getQueryWrapper() {
		return new QueryWrapper<>();
	}

}
