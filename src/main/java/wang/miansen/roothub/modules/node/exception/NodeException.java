package wang.miansen.roothub.modules.node.exception;

import wang.miansen.roothub.common.exception.BaseException;
import wang.miansen.roothub.modules.node.enums.NodeErrorCodeEnum;

/**
 * @author miansen.wang
 * @date 2020-02-16
 */
@SuppressWarnings("serial")
public class NodeException extends BaseException {

	/**
	 * @param httpCode
	 * @param errorCode
	 * @param message
	 */
	public NodeException(NodeErrorCodeEnum nodeErrorCodeEnum) {
		super(nodeErrorCodeEnum.getHttpCode(), nodeErrorCodeEnum.getErrorCode(), nodeErrorCodeEnum.getMessage());
	}

	/**
	 * @param httpCode
	 * @param errorCode
	 * @param message
	 */
	public NodeException(int httpCode, String errorCode, String message) {
		super(httpCode, errorCode, message);
	}

}
