package wang.miansen.roothub.common.exception;

import wang.miansen.roothub.common.enums.BaseErrorCodeEnum;

/**
 * @author miansen.wang
 * @date 2020-01-20
 */
public class BaseApiException extends BaseException {

	private static final long serialVersionUID = -8502351057054395297L;

	public BaseApiException(BaseErrorCodeEnum baseErrorCodeEnum) {
		super(baseErrorCodeEnum);
	}

	public BaseApiException(int httpCode, String errorCode, String message) {
		super(httpCode, errorCode, message);
	}

}
