package cn.roothub.exception;

/**
 * 重复注册/修改异常
 * @author sen
 * 2018年5月6日
 * 下午10:15:07
 * TODO
 */
public class RepeatRootUserException extends RuntimeException{

	public RepeatRootUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatRootUserException(String message) {
		super(message);
	}

}
