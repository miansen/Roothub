package wang.miansen.roothub.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import wang.miansen.roothub.common.vo.BaseVO;

/**
 * 该接口是 Controller 层的基础接口，实现了常用的业务增删改查方法，返回给客户端的都是 JSP 页面。
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
	 * 
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return ModelAndView 返回的页面
	 */
	ModelAndView add(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 添加接口
	 * 
	 * @param vo 要添加的对象
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return ModelAndView 返回的页面
	 */
	ModelAndView save(VO vo, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 返回删除界面
	 * 
	 * @param id 主键 ID，不允许为空
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return ModelAndView 返回的页面
	 */
	ModelAndView delete(String id, HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 删除接口
	 * 
	 * @param id 主键 ID，不允许为空
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return ModelAndView 返回的页面
	 */
	ModelAndView remove(String id, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 返回更新页面
	 * 
	 * @param id 主键 ID，不允许为空
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return ModelAndView 返回的页面
	 */
	ModelAndView edit(String id, HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 更新接口，vo 对象的主键 ID 字段不能为空。
	 * 
	 * @param vo 要更新的对象
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return ModelAndView 返回的页面
	 */
	ModelAndView update(VO vo, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 返回详情页面
	 * 
	 * @param id 主键 ID，不允许为空
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return ModelAndView 返回的页面
	 */
	ModelAndView detail(String id, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 返回列表页面
	 * 
	 * @param pageNumber 当前的页数，默认是第一页
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return 要呈现的 ModelAndView
	 */
	ModelAndView list(Integer pageNumber, HttpServletRequest request, HttpServletResponse response);

}
