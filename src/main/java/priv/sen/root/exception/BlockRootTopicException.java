package priv.sen.root.exception;

/**
 * 用户禁用状态发布话题异常
 * @author sen
 * 2018年5月9日
 * 下午5:30:48
 * TODO
 */
public class BlockRootTopicException extends RuntimeException{

	public BlockRootTopicException(String message, Throwable cause) {
		super(message, cause);
	}

	public BlockRootTopicException(String message) {
		super(message);
	}

}
