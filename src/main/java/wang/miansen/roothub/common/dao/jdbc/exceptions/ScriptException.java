package wang.miansen.roothub.common.dao.jdbc.exceptions;

/**
 * 与 SQL 脚本处理相关的异常
 * @author: miansen.wang
 * @date: 2019-12-07
 */
@SuppressWarnings("serial")
public class ScriptException extends RuntimeException {

	/**
	 * 创建新的实例 {@code ScriptException}
	 * @param message 详细信息
	 */
	public ScriptException(String message) {
		super(message);
	}
	
	/**
	 * 创建新的实例 {@code ScriptException}
	 * @param message 详细信息
	 * @param cause 引发异常的根本原因
	 */
	public ScriptException(String message, Throwable cause) {
		super(message, cause);
	}
}
