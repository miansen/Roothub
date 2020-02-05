package wang.miansen.roothub.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.vo.BaseVO;

/**
 * 该接口是 Controller 层的基础接口，实现了常用的业务增删改查方法
 * 
 * @param <VO> 视图传输的类型
 * 
 * @author miansen.wang
 * @date 2020-01-19
 * @since 3.0
 */
public interface BaseController<VO extends BaseVO> {

	/**
	 * 返回添加页面
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return 要呈现的 ModelAndView
	 */
	ModelAndView add(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 返回删除页面
	 * @param id 主键 ID，不允许为空
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return 要呈现的 ModelAndView
	 */
	ModelAndView delete(Integer id, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 返回编辑页面
	 * @param id 主键 ID，不允许为空
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return 要呈现的 ModelAndView
	 */
	ModelAndView edit(Integer id, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 返回详情页面
	 * @param id 主键 ID，不允许为空
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return 要呈现的 ModelAndView
	 */
	ModelAndView detail(Integer id, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 返回列表页面
	 * @param pageNumber 当前的页数，默认是第一页
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return 要呈现的 ModelAndView
	 */
	ModelAndView list(Integer pageNumber, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 添加接口
	 * @param vo 要添加的数据（JSON 格式）
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return ResponseEntity
	 */
	ResponseEntity<Result<VO>> save(VO vo, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 删除接口
	 * <p>根据主键 ID 删除一条数据，ID 不允许为空。
	 * 
	 * @param id 主键 ID，不允许为空
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return ResponseEntity
	 */
	ResponseEntity<Result<VO>> remove(Integer id, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 更新接口
	 * <p>根据主键 ID 更新一条数据，对象 JSON 结构体中必须包含主键 ID 字段。
	 * 
	 * @param vo 要更新的数据（JSON 格式）
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return ResponseEntity
	 */
	ResponseEntity<Result<VO>> update(VO vo, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 返回单条数据
	 * @param id 主键 ID，不允许为空
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return ResponseEntity
	 */
	ResponseEntity<Result<VO>> getOne(Integer id, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 返回多条数据并分页
	 * @param pageNumber 当前的页数，默认是第一页
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return ResponseEntity
	 */
	ResponseEntity<Result<Page<? extends VO>>> page(Integer pageNumber, HttpServletRequest request,
			HttpServletResponse response);

}
