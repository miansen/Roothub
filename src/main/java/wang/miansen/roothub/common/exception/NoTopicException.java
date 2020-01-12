package wang.miansen.roothub.common.exception;

/**
 * 发布话题失败异常
 * @author sen
 * 2018年5月8日
 * 上午12:17:54
 * TODO
 */
public class NoTopicException extends RuntimeException{

	public NoTopicException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoTopicException(String message) {
		super(message);
	}

}
