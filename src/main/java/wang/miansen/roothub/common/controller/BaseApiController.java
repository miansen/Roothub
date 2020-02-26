package wang.miansen.roothub.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.vo.BaseVO;

/**
 * 该接口是 Api Controller 层的基础接口，定义了常用的业务增删改查方法，返回给客户端的都是 JSON 数据。
 * <p>每个方法的返回值都是 {@code ResponseEntity}，子类实现时，应该根据不同的状态设置 {@code ResponseEntity} 的 {@code HttpStatus} 属性，
 * 以便返回正确的 HTTP 响应码。
 * 
 * @param <VO> 视图传输的类型
 * 
 * @author miansen.wang
 * @date 2020-02-26
 * @since 3.0
 */
public interface BaseApiController<VO extends BaseVO> {


	/**
	 * 添加接口
	 * 
	 * @param vo 要添加的数据对象
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return ResponseEntity 返回 JSON 数据
	 */
	ResponseEntity<Result<VO>> save(VO vo, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 删除接口
	 * <p>根据主键 ID 删除一条数据，ID 不允许为空。
	 * 
	 * @param id 主键 ID，不允许为空
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return ResponseEntity 返回 JSON 数据
	 */
	ResponseEntity<Result<VO>> remove(Integer id, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 更新接口
	 * <p>根据主键 ID 更新一条数据，ID 不允许为空。
	 * 
	 * @param vo 要更新的数据对象
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return ResponseEntity 返回 JSON 数据
	 */
	ResponseEntity<Result<VO>> update(VO vo, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 获取单条数据
	 * 
	 * @param id 主键 ID，不允许为空
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return ResponseEntity 返回 JSON 数据
	 */
	ResponseEntity<Result<VO>> getOne(Integer id, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 获取多条数据并分页
	 * 
	 * @param pageNumber 当前的页数，默认是第一页
	 * @param request 当前的HTTP请求
	 * @param response 当前的HTTP响应
	 * @return ResponseEntity 返回 JSON 数据
	 */
	ResponseEntity<Result<Page<? extends VO>>> page(Integer pageNumber, HttpServletRequest request,
			HttpServletResponse response);

}
