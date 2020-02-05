package wang.miansen.roothub.common.exception;

import wang.miansen.roothub.common.enums.BaseErrorCodeEnum;

/**
 * 该类是业务异常的基础父类，定义了 {@code httpCode}、{@code errorCode}、{@code message}
 * 这三属性以及构造方法，建议大部分的业务异常继承。
 * 
 * @author miansen.wang
 * @date 2020-01-20
 * @since 3.0
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 5413231160048032064L;

	/**
	 * http 错误码，可以通过调用 {@code response.setStatus()} 设置服务器响应的 {@code Status Code}。
	 */
	private int httpCode;
	
	/**
	 * 服务器错误码，可以通过 json 或者页面的形式返回给客户端，以便客户端能够清楚发生错误的原因。
	 */
	private String errorCode;
	
	/**
	 * 发生错误的详细信息，可以通过 json 或者页面的形式返回给客户端，以便客户端能够清楚发生错误的详细信息。
	 */
	private String message;
	
	/**
	 * 创建一个新的 {@code BaseException} 对象
	 * 
	 * @param httpCode http 错误码
	 * @param errorCode 服务器错误码
	 * @param message 发生错误的详细信息
	 */
	public BaseException(int httpCode, String errorCode, String message) {
		this.httpCode = httpCode;
		this.errorCode = errorCode;
		this.message = message;
	}
	
	/**
	 * 通过枚举 {@code BaseErrorCodeEnum} 创建一个新的 {@code BaseException} 对象
	 * 
	 * @param baseErrorCodeEnum 错误的枚举对象
	 */
	public BaseException(BaseErrorCodeEnum baseErrorCodeEnum) {
		this.httpCode = baseErrorCodeEnum.getHttpCode();
		this.errorCode = baseErrorCodeEnum.getErrorCode();
		this.message = baseErrorCodeEnum.getMessage();
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
