package wang.miansen.roothub.common.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wang.miansen.roothub.modules.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import wang.miansen.roothub.common.controller.BaseController;

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
		
		//抛出异常，交给全局异常处理（这样会输出错误信息）
		//ApiAssert.notNull(user, "页面受到了保护，登录后才能访问~点击去<a href='/login'>登录</a>");
		
		//抛出异常，交给全局异常处理（这样会跳转到错误页面）
		/*if(user == null) {
			throw new RuntimeException("页面受到了保护，登录后才能访问~点击去<a href='/login'>登录</a>");
		}*/
		
		//自定义逻辑（跳转到登录页面）
		if(user == null) {
			request.setAttribute("message", "页面受到了保护，登录后才能访问");
			request.getRequestDispatcher("/login").forward(request, response);
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
