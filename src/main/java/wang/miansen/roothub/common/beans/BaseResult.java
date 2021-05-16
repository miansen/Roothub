package wang.miansen.roothub.common.beans;

import java.io.Serializable;

import org.slf4j.MDC;

import wang.miansen.roothub.common.constant.BaseConstants;
import wang.miansen.roothub.common.enums.BaseResultCode;
import wang.miansen.roothub.common.enums.BaseResultCodeEnum;

/**
 * 此类是结果集基类，只定义了 {@code code} 和 {@code message} 字段。 如果想返回数据，可继承此类，扩展 {@code data} 字段。
 * <p>
 * 注意：子类应提供无参构造方法，在业务异常装换为结果集时需要使用无参构造方法来反射构造结果集对象。
 * </p>
 *
 * @author miansen.wang
 * @date 2021-05-11 21:07
 */
public class BaseResult implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6690841684199114471L;

    /**
     * 结果码
     */
    protected String code = BaseResultCodeEnum.SUCCESS.getCode();

    /**
     * 结果信息
     */
    protected String message = BaseResultCodeEnum.SUCCESS.getMessage();

    /**
     * traceId
     */
    protected String traceId = MDC.get(BaseConstants.TRACE_LOG_MDC_KEY);

    /**
     * 空参构造方法
     */
    public BaseResult() {
    }

    /**
     * 构造方法
     *
     * @param code 结果码
     * @param message 结果信息
     */
    public BaseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 构造方法
     *
     * @param baseResultCode 接口返回码
     */
    public BaseResult(BaseResultCode baseResultCode) {
        this(baseResultCode.getCode(), baseResultCode.getMessage());
    }

    /**
     * 成功
     *
     * @return BaseResult
     */
    public static BaseResult success() {
        return new BaseResult();
    }

    /**
     * 失败
     *
     * @param code 结果码
     * @param message 结果信息
     * @return BaseResult
     */
    public static BaseResult fail(String code, String message) {
        return new BaseResult(code, message);
    }

    /**
     * 失败
     *
     * @param baseResultCode 接口返回码
     * @return BaseResult
     */
    public static BaseResult fail(BaseResultCode baseResultCode) {
        return new BaseResult(baseResultCode);
    }

    /**
     * 获取结果码
     *
     * @return 结果码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置结果码
     *
     * @param code 结果码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取结果信息
     *
     * @return 结果信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置结果信息
     *
     * @param message 结果信息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取 traceId
     *
     * @return traceId
     */
    public String getTraceId() {
        return traceId;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
            "code='" + code + '\'' +
            ", message='" + message + '\'' +
            ", traceId='" + traceId + '\'' +
            '}';
    }
}
