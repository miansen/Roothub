package wang.miansen.roothub.common.exception;

/**
 * 用户禁用状态发布话题异常
 * @author sen
 * 2018年5月9日
 * 下午5:30:48
 * TODO
 */
public class BlockTopicException extends RuntimeException{

	public BlockTopicException(String message, Throwable cause) {
		super(message, cause);
	}

	public BlockTopicException(String message) {
		super(message);
	}

}
