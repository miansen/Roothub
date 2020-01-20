package wang.miansen.roothub.common.beans;

/**
 * 封装JSON对象，所有的返回结果都使用它
 * @author sen
 * 2018年5月16日
 * 下午7:14:57
 * @param <T>
 * TODO
 */
public class Result<T> {

	private int code;
	private boolean success;//是否成功标志
	private T data;//成功时返回的对象
	private String msg;//错误信息
	private String errorCode;
	public Result() {
		
	}

	// 成功时的构造器
	public Result(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	public Result(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	// 成功时的构造器
	public Result(int code, boolean success, T data) {
		this.code = code;
		this.success = success;
		this.data = data;
	}

	// 失败时的构造器
	public Result(String errorCode, String msg) {
		this.errorCode = errorCode;
		this.msg = msg;
	}
	
	// 失败时的构造器
	public Result(int code, boolean success, String msg) {
		this.code = code;
		this.success = success;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/*@Override
	public String toString() {
		return "Result [success=" + success + ", data=" + data + ", msg=" + msg + "]";
	}*/
	
}
