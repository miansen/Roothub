package wang.miansen.roothub.common.exception;

/**
 * 发布话题系统异常
 * @author sen
 * 2018年5月8日
 * 上午12:33:53
 * TODO
 */
public class RootTopicException extends RuntimeException{

	public RootTopicException(String message, Throwable cause) {
		super(message, cause);
	}

	public RootTopicException(String message) {
		super(message);
	}

}
