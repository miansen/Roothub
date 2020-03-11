package wang.miansen.roothub.common.ui.exception;

import wang.miansen.roothub.common.exception.BaseException;

/**
 * @author miansen.wang
 * @date 2020-03-11
 */
@SuppressWarnings("serial")
public class BaseTagException extends BaseException {

	/**
	 * @param httpCode
	 * @param errorCode
	 * @param message
	 */
	public BaseTagException(int httpCode, String errorCode, String message) {
		super(httpCode, errorCode, message);
	}

}
