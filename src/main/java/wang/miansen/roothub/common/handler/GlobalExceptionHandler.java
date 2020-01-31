package wang.miansen.roothub.common.handler;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.enums.BaseErrorCodeEnum;
import wang.miansen.roothub.common.exception.BaseException;

/**
 * 全局异常处理
 * <p>这个类可以处理 Controller 层和 DispatcherServlet 抛出的异常，
 * 但是无法处理 404 错误。因为 Spring MVC 默认 404 不抛出错误。
 * 
 * @author miansen.wang
 * @date 2018年10月31日 下午4:03:45
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * 错误页面统一处理
	 * @param request
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.error(e.getMessage());
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
