package wang.miansen.roothub.common.beans;

import java.io.Serializable;

/**
 * 此类是结果集基类，只定义了 {@code code} 和 {@code message} 字段。
 * 如果想返回数据，可继承此类，扩展 {@code data} 字段。
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
    protected String code;

    /**
     * 结果信息
     */
    protected String message;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
            "code='" + code + '\'' +
            ", message='" + message + '\'' +
            '}';
    }
}
