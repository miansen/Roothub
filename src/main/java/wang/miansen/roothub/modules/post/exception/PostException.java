package wang.miansen.roothub.modules.post.exception;

import wang.miansen.roothub.common.exception.BaseException;
import wang.miansen.roothub.modules.post.enums.PostErrorCodeEnum;

/**
 * @author miansen.wang
 * @date 2020-01-20
 */
public class PostException extends BaseException {
	
	private static final long serialVersionUID = 6328048966475096368L;
	
	public PostException(PostErrorCodeEnum topicErrorCodeEnum) {
		super(topicErrorCodeEnum.getHttpCode(), topicErrorCodeEnum.getErrorCode(), topicErrorCodeEnum.getMessage());
	}

	public PostException(int httpCode, String errorCode, String message) {
		super(httpCode, errorCode, message);
	}
	
}
