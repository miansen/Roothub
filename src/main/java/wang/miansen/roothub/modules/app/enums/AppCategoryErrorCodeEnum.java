package wang.miansen.roothub.modules.app.enums;

import wang.miansen.roothub.common.enums.BaseErrorCode;

/**
 * 应用类别错误枚举类
 *
 * @author miansen.wang
 * @date 2020-03-08
 */
public enum AppCategoryErrorCodeEnum implements BaseErrorCode {

    /**
     * 应用类名的 ID 不能为空
     */
    ID_MISSING(400, "AppCategory.IdMissing", "应用类名的 ID 不能为空"),

    /**
     * "应用类名的名称不能为空
     */
    NAME_MISSING(400, "AppCategory.NameMissing", "应用类名的名称不能为空"),

    /**
     * 无效的ID
     */
    INVALIDATE_ID(400, "AppCategory.InvalidateName", "无效的ID"),

    /**
     * 无效的名称
     */
    INVALIDATE_NAME(400, "AppCategory.InvalidateName", "无效的名称");

    /**
     * http 错误码，可以通过调用 {@code response.setStatus()} 设置服务器响应的 {@code Status Code}。
     */
    private final int httpCode;

    /**
     * 服务器错误码，可以通过 json 或者页面的形式返回给客户端，以便客户端能够清楚发生错误的原因。
     */
    private final String errorCode;

    /**
     * 发生错误的详细信息，可以通过 json 或者页面的形式返回给客户端，以便客户端能够清楚发生错误的详细信息。
     */
    private final String message;

    AppCategoryErrorCodeEnum(int httpCode, String errorCode, String message) {
        this.httpCode = httpCode;
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public int getHttpCode() {
        return httpCode;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
