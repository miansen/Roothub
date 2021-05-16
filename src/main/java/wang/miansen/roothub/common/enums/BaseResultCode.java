package wang.miansen.roothub.common.enums;

/**
 * 该接口是接口返回码的基础接口，定义了常用的方法。
 * 建议各个业务模块的接口返回码类实现该接口。
 * 
 * @author miansen.wang
 * @date 2020-03-08
 * @since 3.0
 */
public interface BaseResultCode {

	/**
	 * 获取 HTTP 响应码
	 * <p>当系统出现异常或者错误时，业务层应该调用此方法，
	 * 获取 HTTP 的响应码，并显示地调用 {@code response} 对象的 {@code setStatus()} 方法，
	 * 设置服务器响应的 {@code Status Code}，以便客户端能正确的处理。
	 * 
	 * @return int
	 */
	int getHttpCode();
	
	/**
	 * 获取服务器响应码
	 * <p>这是系统自定义的响应码。当系统出现异常或者错误时，业务层应该调用此方法，
	 * 获取到服务器响应码，然后通过 JSON 或者 HTML 页面的形式输出给客户端，以便客户端能正确的处理。
	 * 
	 * @return String
	 */
	String getCode();
	
	/**
	 * 服务器响应的详细信息
	 * <p>这是服务器响应的详细信息。当系统出现异常或者错误时，业务层应该调用此方法，
	 * 获取到错误的详细信息，然后通过 JSON 或者 HTML 页面的形式输出给客户端，以便客户端能正确的处理。
	 * 
	 * @return String
	 */
	String getMessage();
}
