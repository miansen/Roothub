package wang.miansen.roothub.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author miansen.wang
 * @date 2018年10月31日 下午5:29:58
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ApiException extends RuntimeException {

	private int code;
	private String message;

	public ApiException(String message) {
		this.code = 201;
		this.message = message;
	}

	public ApiException(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
