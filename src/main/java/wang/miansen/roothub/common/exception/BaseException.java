package wang.miansen.roothub.common.exception;

import wang.miansen.roothub.common.enums.BaseErrorCodeEnum;

/**
 * @author miansen.wang
 * @date 2020-01-20
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 5413231160048032064L;

	private int httpCode;
	
	private String errorCode;
	
	private String message;
	
	public BaseException(int httpCode, String errorCode, String message) {
		this.httpCode = httpCode;
		this.errorCode = errorCode;
		this.message = message;
	}
	
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
