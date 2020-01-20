package wang.miansen.roothub.modules.topic.exception;

import wang.miansen.roothub.common.exception.BaseException;
import wang.miansen.roothub.modules.topic.enums.TopicErrorCodeEnum;

/**
 * @author miansen.wang
 * @date 2020-01-20
 */
public class TopicException extends BaseException {
	
	private static final long serialVersionUID = 6328048966475096368L;
	
	public TopicException(TopicErrorCodeEnum topicErrorCodeEnum) {
		super(topicErrorCodeEnum.getHttpCode(), topicErrorCodeEnum.getErrorCode(), topicErrorCodeEnum.getMessage());
	}

	public TopicException(int httpCode, String errorCode, String message) {
		super(httpCode, errorCode, message);
	}
	
}
