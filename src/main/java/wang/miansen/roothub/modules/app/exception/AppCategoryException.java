package wang.miansen.roothub.modules.app.exception;

import wang.miansen.roothub.common.exception.BaseException;
import wang.miansen.roothub.modules.app.enums.AppCategoryErrorCodeEnum;

/**
 * 应用类别异常类
 * 
 * @author miansen.wang
 * @date 2020-03-08
 */
public class AppCategoryException extends BaseException {

	private static final long serialVersionUID = 6328048966475096368L;

	public AppCategoryException(int httpCode, String errorCode, String message) {
		super(httpCode, errorCode, message);
	}

	public AppCategoryException(AppCategoryErrorCodeEnum appCategoryErrorCodeEnum) {
		super(appCategoryErrorCodeEnum);
	}

}
