package cn.roothub.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import cn.roothub.entity.User;
import cn.roothub.exception.ApiAssert;
import cn.roothub.web.front.BaseController;

/**
 * 全局拦截器
 * @author miansen.wang
 * @date 2018年10月31日 下午7:55:16
 */
public class GlobalInterceptorHandler implements HandlerInterceptor{
	
	@Autowired
	private BaseController baseController;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User user = baseController.getUser(request);
		//ApiAssert.notNull(user, "页面受到了保护，登录后才能访问~点击去<a href='/login'>登录</a>");
		if(user == null) {
			throw new RuntimeException("页面受到了保护，登录后才能访问~点击去<a href='/login'>登录</a>");
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
