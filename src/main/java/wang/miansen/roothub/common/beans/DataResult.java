package wang.miansen.roothub.common.beans;

import wang.miansen.roothub.common.enums.BaseResultCode;

/**
 * 此类继承 {@link BaseResult} 并扩展 {@code data} 字段，用于返回数据结果集。
 *
 * @author miansen.wang
 * @date 2021-05-16 16:03
 */
public class DataResult<T> extends BaseResult {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6153140460127972300L;

    /**
     * 数据
     */
    private T data;

    /**
     * 空参构造方法
     */
    public DataResult() {
        super();
    }

    /**
     * 构造方法
     *
     * @param data 数据
     */
    public DataResult(T data) {
        super();
        this.data = data;
    }

    /**
     * 构造方法
     *
     * @param code 结果码
     * @param message 结果集
     * @param data 数据
     */
    public DataResult(String code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    /**
     * 构造方法
     *
     * @param baseResultCode 接口返回码
     * @param data 数据
     */
    public DataResult(BaseResultCode baseResultCode, T data) {
        super(baseResultCode);
        this.data = data;
    }

    /**
     * 成功
     *
     * @param data 数据
     * @param <T> 数据的类型
     * @return DataResult
     */
    public static <T> DataResult<T> success(T data) {
        return new DataResult<>(data);
    }

    /**
     * 失败
     *
     * @param code 结果码
     * @param message 结果信息
     * @return DataResult
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static DataResult<?> fail(String code, String message) {
        return new DataResult(code, message, null);
    }

    /**
     * 失败
     *
     * @param baseResultCode 接口返回码
     * @return DataResult
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static DataResult<?> fail(BaseResultCode baseResultCode) {
        return new DataResult(baseResultCode, null);
    }

    /**
     * 获取数据
     *
     * @return 数据
     */
    public T getData() {
        return data;
    }

    /**
     * 设置数据
     *
     * @param data 数据
     */
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataResult{" +
            "code='" + code + '\'' +
            ", message='" + message + '\'' +
            ", traceId='" + traceId + '\'' +
            ", data=" + data +
            '}';
    }
}
