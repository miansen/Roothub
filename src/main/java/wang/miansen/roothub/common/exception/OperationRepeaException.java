package wang.miansen.roothub.common.exception;

/**
 * 重复异常
 * @author sen
 * 2018年5月9日
 * 下午6:14:55
 * TODO
 */
public class OperationRepeaException extends RuntimeException{

	public OperationRepeaException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperationRepeaException(String message) {
		super(message);
	}

}
