package wang.miansen.roothub.common.exception;

/**
 * 重复注册/修改异常
 * @author sen
 * 2018年5月6日
 * 下午10:15:07
 * TODO
 */
public class RepeatUserException extends RuntimeException{

	public RepeatUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatUserException(String message) {
		super(message);
	}

}
