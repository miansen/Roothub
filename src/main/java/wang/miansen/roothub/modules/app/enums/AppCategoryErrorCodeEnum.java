package wang.miansen.roothub.modules.app.enums;

import wang.miansen.roothub.common.enums.BaseErrorCode;

/**
 * 应用类别错误枚举类
 * 
 * @author miansen.wang
 * @date 2020-03-08
 */
public enum AppCategoryErrorCodeEnum implements BaseErrorCode {

	ID_MISSING(400, "AppCategory.IdMissing", "应用类名的 ID 不能为空"),

	NAME_MISSING(400, "AppCategory.NameMissing", "应用类名的名称不能为空"),
	
	INVALIDATE_ID(400, "AppCategory.InvalidateName", "无效的ID"),

	INVALIDATE_NAME(400, "AppCategory.InvalidateName", "无效的名称");

	private int httpCode;

	private String errorCode;

	private String message;

	private AppCategoryErrorCodeEnum(int httpCode, String errorCode, String message) {
		this.httpCode = httpCode;
		this.errorCode = errorCode;
		this.message = message;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}

}
