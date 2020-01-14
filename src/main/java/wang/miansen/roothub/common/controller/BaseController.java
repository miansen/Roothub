package wang.miansen.roothub.common.controller;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.beans.Result;
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
public abstract class BaseController<DO extends BaseDO, DTO extends BaseDTO, VO extends BaseVO> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SiteConfig siteConfig;

	/**
	 * 跳转到添加页面
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getFrontPrefix() + "/add");
		return mv;
	}

	/**
	 * 添加接口
	 * @param vo 视图传输对象
	 * @param request
	 * @param response
	 * @return Result
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result<VO> save(VO vo, HttpServletRequest request, HttpServletResponse response) {
		this.getService().save(this.getVO2DTO().apply(vo));
		return new Result<>("200", true, vo);
	}

	/**
	 * 跳转到删除页面
	 * @param id 主键 ID，不允许为空。
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable(value = "id") Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		DTO dto = this.getService().getById(id);
		VO vo = getDTO2VO().apply(dto);
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getFrontPrefix() + "/delete");
		mv.addObject(vo.getClass().getSimpleName(), vo);
		return mv;
	}

	/**
	 * 删除接口
	 * @param id 主键 ID，不允许为空。
	 * @param request
	 * @param response
	 * @return Result
	 */
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result<VO> remove(@PathVariable(value = "id") Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		this.getService().removeById(id);
		return new Result<>("200", true);
	}

	/**
	 * 跳转到编辑页面
	 * @param id 主键 ID，不允许为空。
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(value = "id") Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		DTO dto = this.getService().getById(id);
		VO vo = getDTO2VO().apply(dto);
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getFrontPrefix() + "/edit");
		mv.addObject(vo.getClass().getSimpleName(), vo);
		return mv;
	}

	/**
	 * 更新接口
	 * @param vo 视图传输对象
	 * @param request
	 * @param response
	 * @return Result
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Result<VO> update(@RequestBody VO vo, HttpServletRequest request, HttpServletResponse response) {
		this.getService().updateById(getVO2DTO().apply(vo));
		return new Result<>("200", true, vo);
	}

	/**
	 * 跳转到详情页面
	 * @param id 主键 ID，不允许为空。
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public ModelAndView view(@PathVariable(value = "id") Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		DTO dto = this.getService().getById(id);
		VO vo = getDTO2VO().apply(dto);
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getFrontPrefix() + "/view");
		mv.addObject(vo.getClass().getSimpleName(), vo);
		return mv;
	}

	/**
	 * 跳转到列表页面
	 * @param pageNumber 当前的页数，默认是第一页。
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
			HttpServletRequest request, HttpServletResponse response) {
		Page<DTO> dtoPage = this.getService().page(pageNumber, 25, this.getQueryWrapper());
		List<? extends VO> voList = dtoPage.getList().stream().map(getDTO2VO()).collect(Collectors.toList());
		Page<? extends VO> voPage = new Page<>(voList, dtoPage.getPageNumber(), dtoPage.getPageSize(),
				dtoPage.getTotalRow());
		ModelAndView mv = new ModelAndView();
		mv.setViewName(this.getFrontPrefix() + "/list");
		mv.addObject("page", voPage);
		return mv;
	}

	/**
	 * 输出 JSON 格式的多条数据
	 * @param pageNumber 当前的页数，默认是第一页。
	 * @param request
	 * @param response
	 * @return Result
	 */
	@RequestMapping(value = "/data", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page<? extends VO>> data(@RequestParam(value = "pageNumber") Integer pageNumber,
			HttpServletRequest request, HttpServletResponse response) {
		Page<DTO> dtoPage = this.getService().page(pageNumber, 25, this.getQueryWrapper());
		List<? extends VO> voList = dtoPage.getList().stream().map(getDTO2VO()).collect(Collectors.toList());
		Page<? extends VO> voPage = new Page<>(voList, dtoPage.getPageNumber(), dtoPage.getPageSize(),
				dtoPage.getTotalRow());
		return new Result<>("200", true, voPage);
	}

	/**
	 * 输出 JSON 格式的单条数据
	 * @param id 主键 ID，不允许为空。
	 * @param request
	 * @param response
	 * @return Result
	 */
	@RequestMapping(value = "/data/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result<VO> dataDetail(@PathVariable(value = "id") Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		DTO dto = this.getService().getById(id);
		VO vo = getDTO2VO().apply(dto);
		return new Result<>("200", true, vo);
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

}
