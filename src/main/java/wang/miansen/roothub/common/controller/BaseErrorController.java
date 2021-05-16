package wang.miansen.roothub.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.enums.BaseResultCodeEnum;

/**
 * @author miansen.wang
 * @date 2020-01-21
 */
//@Controller
public class BaseErrorController implements ErrorController {

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@RequestMapping(value = "/error", produces = "text/html")
	public ModelAndView errorHtml(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", BaseResultCodeEnum.INTERNAL_ERROR.getMessage());
		mv.addObject("errorCode", getStatus(request).value());
		mv.setViewName("/default/front/error/error");
		return mv;
	}

	@RequestMapping(value = "/error")
	@ResponseBody
	public ResponseEntity<Result<String>> error(HttpServletRequest request) {
		HttpStatus status = getStatus(request);
		return new ResponseEntity<Result<String>>(new Result<>(BaseResultCodeEnum.INTERNAL_ERROR.getCode(),
				BaseResultCodeEnum.INTERNAL_ERROR.getMessage()), status);
	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode != null) {
			try {
				return HttpStatus.valueOf(statusCode);
			} catch (Exception ex) {
			}
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

}
