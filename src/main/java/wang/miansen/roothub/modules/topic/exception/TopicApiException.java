package wang.miansen.roothub.modules.topic.exception;

import wang.miansen.roothub.common.exception.BaseApiException;
import wang.miansen.roothub.modules.topic.enums.TopicErrorCodeEnum;

/**
 * @author miansen.wang
 * @date 2020-01-20
 */
public class TopicApiException extends BaseApiException {

	private static final long serialVersionUID = 7898949975288079587L;

	public TopicApiException(TopicErrorCodeEnum topicErrorCodeEnum) {
		super(topicErrorCodeEnum.getHttpCode(), topicErrorCodeEnum.getErrorCode(), topicErrorCodeEnum.getMessage());
	}

	public TopicApiException(int httpCode, String errorCode, String message) {
		super(httpCode, errorCode, message);
	}
	
}
