package wang.miansen.roothub.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-03
 */
public class SimpleHashUtil {

	/**
	 * 加密
	 * @param hashAlgorithmName: 加密类型
	 * @param credentials: 加密对象
	 * @param salt: 盐值
	 * @param hashIterations: 加密次数
	 * @return
	 */
	public static SimpleHash simpleHash(String hashAlgorithmName,String credentials,Object salt,int hashIterations) {
		return new SimpleHash(hashAlgorithmName, credentials, ByteSource.Util.bytes(salt), hashIterations);
	}
	
	public static void main(String[] args) {
		// 初始化密码认证处理器
		PasswordEncoder passwordEncoder = new MyMessageDigestPasswordEncoder("md5");
		// 初始化认证服务
		UserDetailsService userDetailsService = new MyUserDetailsService();
		
		// 初始化认证提供者
		AuthenticationProvider provider = new MyAuthenticationProvider(userDetailsService, passwordEncoder);
		
		List<AuthenticationProvider> providers = new ArrayList<>();
		providers.add(provider);
		
		// 初始化认证管理器
		AuthenticationManager am = new MyAuthenticationManager(providers);
		MyUsernamePasswordAuthenticationFilter filter = new MyUsernamePasswordAuthenticationFilter("/login");
		filter.setAuthenticationManager(am);
		//filter.doFilter(req, res, chain);
		
	}
	
	/**
	 * 用于处理来自表单提交的认证拦截器，Spring Security 会默认提供
	 * <p> 父类已经实现了 doFilter() 方法，我们只需要实现 attemptAuthentication() 方法，
	 * 进行身份认证就可以了
	 * 
	 */
	static class MyUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

		protected MyUsernamePasswordAuthenticationFilter(String defaultFilterProcessesUrl) {
			super(defaultFilterProcessesUrl);
		}

		// 执行实际的身份认证
		@Override
		public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
				throws AuthenticationException, IOException, ServletException {
			
			// 客户端提交的用户名
			String username = request.getParameter("username");
			// 客户端提交的密码
			String password = request.getParameter("password");
			
			// 封装成一个未认证的 Authentication 对象
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
					username, password);
			// 调用认证管理器进行认证，返回一个已经认证的 Authentication 对象
			return this.getAuthenticationManager().authenticate(authRequest);
		}
		
	}
	
	/**
	 * 认证管理器，Spring Security 会默认提供
	 */
	static class MyAuthenticationManager implements AuthenticationManager {

		// 认证提供者（可以有多个）
		private List<AuthenticationProvider> providers = Collections.emptyList();
		
		public MyAuthenticationManager(List<AuthenticationProvider> providers) {
			this.providers = providers;
		}

		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException {
			Class<? extends Authentication> toTest = authentication.getClass();
			Authentication result = null;
			for (AuthenticationProvider provider : providers) {
				if (!provider.supports(toTest)) {
					continue;
				}
				// 调用认证提供者进行认证，如果 result 不为 null ,说明认证通过
				result = provider.authenticate(authentication);
				if (result != null) {
					break;
				}
			}
			if (result == null) {
				throw new ProviderNotFoundException("ProviderManager.providerNotFound");
			}
			return result;
		}

	}
	
	/**
	 * 认证提供者，对用户的信息进行认证，并返回一个完整的 Authentication 对象，Spring Security 会默认提供
	 * 
	 * <p>认证提供者需要实现 AuthenticationProvider 接口，该接口定义了一个认证的方法 authenticate()。
	 * <p>由于 Spring Security 提供了一个抽象类 AbstractUserDetailsAuthenticationProvider，
	 * 并且它已经实现了 authenticate() 方法，所以我们不需要自己实现，直接继承这个抽象类即可。
	 * 
	 */
	static class MyAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

		// 认证的 Service
		private UserDetailsService userDetailsService;
		
		// 密码认证处理器
		private PasswordEncoder passwordEncoder;
		
		public MyAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
			this.userDetailsService = userDetailsService;
			this.passwordEncoder = passwordEncoder;
		}

		/**
		 * 这个方法用于添加额外的检查功能，我们不需要添加，所以空着即可
		 */
		@Override
		protected void additionalAuthenticationChecks(UserDetails userDetails,
				UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
			
		}

		/**
		 * 这个方法很重要，用于认证用户提供的信息是否正确，
		 * 并且返回一个 UserDetails 对象，父类的 authenticate() 方法会用到这个对象
		 */
		@Override
		protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
				throws AuthenticationException {
			// 调用认证服务接口，加载 UserDetails 对象
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if (userDetails == null) {
	            throw new UsernameNotFoundException(username);
	        }
			// 判断用户名和密码是否正确，如果正确直接返回
			if (userDetails.getUsername().equals(authentication.getPrincipal().toString()) 
	                && passwordEncoder.isPasswordValid(userDetails.getPassword(), authentication.getCredentials().toString(), null)) {
	            return userDetails;
	        }
			throw new BadCredentialsException("username: " + username + ", credentials: " + authentication.getCredentials());
		}

	}
	
	/**
	 * 认证服务，一般需要我们提供
	 * 
	 * <p>认证服务需要实现 UserDetailsService 接口，该接口只定义了一个方法 loadUserByUsername()
	 * <p>这里就是我们的认证逻辑了，可以根据 username 去数据库查找用户的信息，角色以及权限，然后包装成 UserDetails 对象，
	 * 供后续使用
	 */
	static class MyUserDetailsService implements UserDetailsService {

		/**
		 * 这里应该查数据库来组装 UserDetails 对象，
		 * 为了演示方便我直接写死了
		 */
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			// 存放用户的角色或者权限
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			// 角色的名字以 "ROLE_" 开头
			SimpleGrantedAuthority authority1 = new SimpleGrantedAuthority("ROLE_ADMIN");
			// 权限的名字没有限制，起啥都行
			SimpleGrantedAuthority authority2 = new SimpleGrantedAuthority("read");
			
			// 添加角色
			authorities.add(authority1);
			// 添加权限
			authorities.add(authority2);
			
			// 构造 User 对象并返回，构造参数的含义注释都有写
			return new org.springframework.security.core.userdetails.User(username, "64c8b1e43d8ba3115ab40bcea57f010b", true, true, true,
					true, authorities);
			
		}
		
	}
	
	/**
	 * 密码认证处理器，如果你数据库里的密码是加密的话，还需要提供一个密码认证处理器
	 * 
	 * <p>构造方法接收一个 String 类型的参数，用来指定加密的类型
	 * <p>也就是说你数据库里的密码是用什么加密的，这里就传入什么，后面的认证提供者会用到
	 */
	static class MyMessageDigestPasswordEncoder extends MessageDigestPasswordEncoder {

		public MyMessageDigestPasswordEncoder(String algorithm) {
			super(algorithm);
		}

		/**
		 * @param encPass 数据库密码（通常是加密的）
		 * @param rawPass 前端传送过来的密码（通常是明文的）
		 * @param salt 盐值
		 */
		@Override
		public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
			// 此处应该根据你自己的加密规则来校验密码
			
			// 为了演示方便，我这里只简单的进行比较
			return Objects.equals(encPass, rawPass);
		}
		
	}
}
