package wang.miansen.roothub.boot;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.h2.server.web.WebServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ModelAndView;

import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.enums.BaseResultCodeEnum;

/**
 * 以 SpringBoot 的方式启动项目
 * 
 * @author miansen.wang
 * @date 2020-01-15
 * @since 3.0
 */
@Controller
@SpringBootApplication
@ImportResource({"classpath*:spring/spring-*.xml"})
public class RoothubApplication implements ErrorController {

	private static final Logger logger = LoggerFactory.getLogger(RoothubApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RoothubApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setForceEncoding(true);
		characterEncodingFilter.setEncoding("UTF-8");
		registrationBean.setFilter(characterEncodingFilter);
		return registrationBean;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@RequestMapping(value = "/error", produces = "text/html")
	public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorCode", getStatus(request).value());
        mv.addObject("errorMsg", getStatus(request).getReasonPhrase());
        mv.setViewName("/error");
		return mv;
	}

	@RequestMapping(value = "/error")
	@ResponseBody
	public ResponseEntity<Result<String>> error(HttpServletRequest request, HttpServletResponse response) {
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
			    logger.warn("Exception in RoothubApplication#getStatus", ex);
			}
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	/**
	 * 注册 H2 数据库访问 Servlet
	 * @return
	 */
	@Bean
    public ServletRegistrationBean createH2WebServlet(){
        ServletRegistrationBean servlet = new ServletRegistrationBean(new WebServlet(),"/h2-console/*");
        return servlet;
    }

}
