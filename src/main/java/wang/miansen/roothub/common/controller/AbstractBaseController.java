package wang.miansen.roothub.common.controller;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.entity.BaseDO;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.common.util.CookieAndSessionUtil;
import wang.miansen.roothub.common.util.IDGenerator;
import wang.miansen.roothub.common.vo.BaseVO;
import wang.miansen.roothub.config.ApplicationConfig;
import wang.miansen.roothub.modules.user.model.User;

/**
 * 该类是 Controller 层的基础父类，实现了常用的业务增删改查方法，建议大部分的 Controller 层继承。
 * <p>继承该类后即可拥有简单的页面跳转和数据输出能力，前提是子类必须实现父类定义的抽象方法。
 * <p>如果父类的方法无法满足业务需求，子类可以重写父类的方法。
 * <p>注意：要继承该类，对应的 {@code DO} 要继承 {@link BaseDO}，{@code DTO} 要继承 {@link BaseDTO}，
 * {@code VO} 要继承 {@link BaseVO}
 * 
 * @param <DO> 数据库表映射实体类的类型
 * @param <DTO> 数据传输的类型
 * @param <VO> 视图传输的类型
 * 
 * @author miansen.wang
 * @date 2020-01-14
 * @since 3.0
 */
public abstract class AbstractBaseController<DO extends BaseDO, DTO extends BaseDTO, VO extends BaseVO>
		implements BaseController<VO> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected ApplicationConfig applicationConfig;

	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getJspPrefix() + "/add");
		return mv;
	}
	
	@Override
	public ModelAndView save(VO vo, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		vo.setPrimaryKey(IDGenerator.generateID());
		this.getService().save(this.getVO2DTO().apply(vo));
		mv.setViewName("redirect:/");
		return mv;
	}

	public ModelAndView delete(String id, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		DTO dto = this.getService().getById(id);
		VO vo = getDTO2VO().apply(dto);
		mv.addObject(vo.getClass().getSimpleName(), vo);
		mv.setViewName(this.getJspPrefix() + "/delete");
		return mv;
	}
	
	@Override
	public ModelAndView remove(String id, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		this.getService().removeById(id);
		mv.setViewName("redirect:/");
		return mv;
	}

	public ModelAndView edit(String id, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		DTO dto = this.getService().getById(id);
		VO vo = getDTO2VO().apply(dto);
		mv.setViewName(this.getJspPrefix() + "/edit");
		mv.addObject(vo.getClass().getSimpleName(), vo);
		return mv;
	}
	
	@Override
	public ModelAndView update(VO vo, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		this.getService().updateById(getVO2DTO().apply(vo));
		mv.setViewName("redirect:/");
		return mv;
	}

	public ModelAndView detail(String id, HttpServletRequest request, HttpServletResponse response) {
		DTO dto = this.getService().getById(id);
		VO vo = getDTO2VO().apply(dto);
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getJspPrefix() + "/detail");
		mv.addObject(vo.getClass().getSimpleName(), vo);
		return mv;
	}

	public ModelAndView list(Integer pageNumber, HttpServletRequest request, HttpServletResponse response) {
		Page<DTO> dtoPage = this.getService().page(pageNumber, 25, this.getQueryWrapper());
		List<? extends VO> voList = dtoPage.getList().stream().map(getDTO2VO()).collect(Collectors.toList());
		Page<? extends VO> voPage = new Page<>(voList, dtoPage.getPageNumber(), dtoPage.getPageSize(),
				dtoPage.getTotalRow());
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getJspPrefix() + "/list");
		mv.addObject("page", voPage);
		mv.addObject("pageNumber", pageNumber);
		return mv;
	}

	protected abstract Function<? super DTO, ? extends VO> getDTO2VO();

	protected abstract Function<? super VO, ? extends DTO> getVO2DTO();

	protected abstract BaseService<DO, DTO> getService();

	protected abstract String getModuleName();

	protected abstract QueryWrapper<DO> getQueryWrapper();
	
	/**
	 * JSP 页面的路径前缀
	 * @return
	 */
	protected String getJspPrefix() {
		return "/" + this.applicationConfig.getTheme() + "/" + getModuleName();
	}

	/**
	 * 获取当前登录用户的信息
	 * 
	 * @param request
	 * @return
	 */
	public User getUser(HttpServletRequest request) {
		return CookieAndSessionUtil.getSession(request, "user");
	}

	/**
	 * 重定向
	 * 
	 * @param request 当前的HTTP请求
	 * @param path 重定向的路径
	 * @return String
	 */
	protected String redirect(HttpServletRequest request, String path) {
		return "redirect:" + path;
	}
	
	/**
	 * 转发
	 * 
	 * @param request 当前的HTTP请求
	 * @param path 转发的路径
	 * @return String
	 */
	protected String forward(HttpServletRequest request, String path) {
		return "forward:" + path;
	}

}
