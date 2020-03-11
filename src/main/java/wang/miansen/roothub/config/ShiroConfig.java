package wang.miansen.roothub.config;

import java.util.LinkedHashMap;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-01
 */
public class ShiroConfig {

	/**
	 * 定义Shiro拦截规则
	 * authc:所有url都必须认证通过才可以访问
	 * anon:所有url都可以匿名访问
	 * @return
	 */
	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		
		// shiro的拦截规则是从上至下的，匹配到第一个就不会往下匹配了，所以这里使用有顺序的LinkedHashMap
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		
		// 配置静态资源，可以匿名访问
		map.put("/resources/**", "anon");
		
		// 配置退出过滤器，其中的具体的退出代码Shiro已经替我们实现了
		map.put("/admin/logout", "logout");
		
		// 所有url都必须认证通过才可以访问
		map.put("/admin/**", "authc");
		
		return map;
	}
}
