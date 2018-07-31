package cn.roothub.exception;

/**
 * 发布话题失败异常
 * @author sen
 * 2018年5月8日
 * 上午12:17:54
 * TODO
 */
public class NoRootTopicException extends RuntimeException{

	public NoRootTopicException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoRootTopicException(String message) {
		super(message);
	}

}
