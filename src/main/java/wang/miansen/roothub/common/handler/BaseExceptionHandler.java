package wang.miansen.roothub.common.handler;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.enums.BaseErrorCodeEnum;
import wang.miansen.roothub.common.exception.BaseException;

/**
 * 全局异常处理
 * <p>注意：这个只能处理 Controller 层抛出的异常，无法处理 DispatcherServlet 抛出的异常，
 * 所以用 {@link GlobalExceptionHandler} 替代。
 * 
 * @author miansen.wang
 * @date 2020-01-21
 * @since 3.0
 */
// @Component
public class BaseExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception e) {
		String contentType = request.getContentType();
		if (Objects.equals("application/json", contentType)) {
			MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
			jsonView.setExtractValueFromSingleKeyModel(true);
			ModelAndView mv = new ModelAndView(jsonView);
			if (e instanceof BaseException) {
				BaseException be = (BaseException) e;
				response.setStatus(be.getHttpCode());
				mv.addObject(new Result<>(be.getErrorCode(), be.getMessage()));
				return mv;
			} else {
				response.setStatus(BaseErrorCodeEnum.INTERNAL_ERROR.getHttpCode());
				mv.addObject(new Result<>(BaseErrorCodeEnum.INTERNAL_ERROR.getErrorCode(),
						BaseErrorCodeEnum.INTERNAL_ERROR.getMessage()));
				return mv;
			}
		} else {
			ModelAndView mv = new ModelAndView();
			if (e instanceof BaseException) {
				BaseException be = (BaseException) e;
				response.setStatus(be.getHttpCode());
				mv.addObject("exception", be.getMessage());
				mv.addObject("errorCode", be.getErrorCode());
				mv.setViewName("/default/front/error/error");
				return mv;
			} else {
				response.setStatus(BaseErrorCodeEnum.INTERNAL_ERROR.getHttpCode());
				mv.addObject("exception", BaseErrorCodeEnum.INTERNAL_ERROR.getMessage());
				mv.addObject("errorCode", BaseErrorCodeEnum.INTERNAL_ERROR.getErrorCode());
				mv.setViewName("/default/front/error/error");
				return mv;
			}
		}
	}

}
