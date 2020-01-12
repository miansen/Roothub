package wang.miansen.roothub.common.exception;

/**
 * 系统异常
 * @author sen
 * 2018年5月9日
 * 下午6:15:57
 * TODO
 */
public class OperationSystemException extends RuntimeException{

	public OperationSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperationSystemException(String message) {
		super(message);
	}

}
