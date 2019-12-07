package wang.miansen.roothub.common.dao.jdbc.exceptions;

/**
 * 与 SQL 脚本处理相关的异常
 * @author: miansen.wang
 * @date: 2019-12-07
 */
@SuppressWarnings("serial")
public class ScriptException extends RuntimeException {

	public ScriptException(String message) {
		super(message);
	}
	
	public ScriptException(String message, Throwable cause) {
		super(message, cause);
	}
}
