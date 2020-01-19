package wang.miansen.roothub.common.controller;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.beans.ResultStatus;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.entity.BaseDO;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.common.util.CookieAndSessionUtil;
import wang.miansen.roothub.common.vo.BaseVO;
import wang.miansen.roothub.config.SiteConfig;
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
	private SiteConfig siteConfig;

	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getFrontPrefix() + "/add");
		return mv;
	}

	public ModelAndView delete(Integer id, HttpServletRequest request, HttpServletResponse response) {
		DTO dto = this.getService().getById(id);
		VO vo = getDTO2VO().apply(dto);
		ModelAndView mv = new ModelAndView();
		mv.addObject(vo.getClass().getSimpleName(), vo);
		mv.setViewName(this.getFrontPrefix() + "/delete");
		return mv;
	}

	public ModelAndView edit(Integer id, HttpServletRequest request, HttpServletResponse response) {
		DTO dto = this.getService().getById(id);
		VO vo = getDTO2VO().apply(dto);
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getFrontPrefix() + "/edit");
		mv.addObject(vo.getClass().getSimpleName(), vo);
		return mv;
	}

	public ModelAndView detail(Integer id, HttpServletRequest request, HttpServletResponse response) {
		DTO dto = this.getService().getById(id);
		VO vo = getDTO2VO().apply(dto);
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getFrontPrefix() + "/detail");
		mv.addObject(vo.getClass().getSimpleName(), vo);
		return mv;
	}

	public ModelAndView list(Integer pageNumber, HttpServletRequest request, HttpServletResponse response) {
		Page<DTO> dtoPage = this.getService().page(pageNumber, 25, this.getQueryWrapper());
		List<? extends VO> voList = dtoPage.getList().stream().map(getDTO2VO()).collect(Collectors.toList());
		Page<? extends VO> voPage = new Page<>(voList, dtoPage.getPageNumber(), dtoPage.getPageSize(),
				dtoPage.getTotalRow());
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getFrontPrefix() + "/list");
		mv.addObject("page", voPage);
		return mv;
	}

	public Result<VO> save(VO vo, HttpServletRequest request, HttpServletResponse response) {
		this.getService().save(this.getVO2DTO().apply(vo));
		return new Result<>(ResultStatus.OK.getValue(), ResultStatus.OK.getReasonPhrase(), vo);
	}

	public Result<VO> remove(Integer id, HttpServletRequest request, HttpServletResponse response) {
		this.getService().removeById(id);
		return new Result<>(ResultStatus.NO_CONTENT.getValue(), ResultStatus.NO_CONTENT.getReasonPhrase(), null);
	}

	public Result<VO> update(Integer id, VO vo, HttpServletRequest request, HttpServletResponse response) {
		this.getService().updateById(getVO2DTO().apply(vo));
		return new Result<>(ResultStatus.OK.getValue(), ResultStatus.OK.getReasonPhrase(), vo);
	}

	public Result<VO> getOne(Integer id, HttpServletRequest request, HttpServletResponse response) {
		DTO dto = this.getService().getById(id);
		VO vo = getDTO2VO().apply(dto);
		return new Result<>(ResultStatus.OK.getValue(), ResultStatus.OK.getReasonPhrase(), vo);
	}

	public Result<Page<? extends VO>> page(Integer pageNumber, HttpServletRequest request,
			HttpServletResponse response) {
		Page<DTO> dtoPage = this.getService().page(pageNumber, 25, this.getQueryWrapper());
		List<? extends VO> voList = dtoPage.getList().stream().map(getDTO2VO()).collect(Collectors.toList());
		Page<? extends VO> voPage = new Page<>(voList, dtoPage.getPageNumber(), dtoPage.getPageSize(),
				dtoPage.getTotalRow());
		return new Result<>(ResultStatus.OK.getValue(), ResultStatus.OK.getReasonPhrase(), voPage);
	}

	protected abstract Function<? super DTO, ? extends VO> getDTO2VO();

	protected abstract Function<? super VO, ? extends DTO> getVO2DTO();

	protected abstract BaseService<DO, DTO> getService();

	protected abstract String getModuleName();

	protected abstract QueryWrapper<DO> getQueryWrapper();

	protected String getFrontPrefix() {
		return siteConfig.getFrontPath() + "/" + getModuleName();
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

	protected String redirect(String path) {
		return "redirect:" + path;
	}

}
