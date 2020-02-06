package wang.miansen.roothub.modules.post.exception;

import wang.miansen.roothub.common.exception.BaseApiException;
import wang.miansen.roothub.modules.post.enums.PostErrorCodeEnum;

/**
 * @author miansen.wang
 * @date 2020-01-20
 */
public class PostApiException extends BaseApiException {

	private static final long serialVersionUID = 7898949975288079587L;

	public PostApiException(PostErrorCodeEnum topicErrorCodeEnum) {
		super(topicErrorCodeEnum.getHttpCode(), topicErrorCodeEnum.getErrorCode(), topicErrorCodeEnum.getMessage());
	}

	public PostApiException(int httpCode, String errorCode, String message) {
		super(httpCode, errorCode, message);
	}
	
}
