package cn.roothub.exception;

/**
 * 注册失败异常
 * @author sen
 * 2018年5月6日
 * 下午10:12:08
 * TODO
 */
public class NoRootUserException extends RuntimeException{

	public NoRootUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoRootUserException(String message) {
		super(message);
	}

}
