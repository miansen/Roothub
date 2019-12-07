package wang.miansen.roothub.common.dao.jdbc.exceptions;

/**
 * 如果无法确定比 {@link ScriptException} 更具体的内容时，可以抛出此异常。
 * <p>例如，来自 JDBC 的 java.SQL.SQLException
 * @author: miansen.wang
 * @date: 2019-12-07
 */
@SuppressWarnings("serial")
public class UncategorizedScriptException extends ScriptException {

	/**
	 * 创建新的实例
	 * @param message 详细信息
	 */
	public UncategorizedScriptException(String message) {
		super(message);
	}

	/**
	 * 创建新的实例
	 * @param message 详细信息
	 * @param cause 引发异常的根本原因
	 */
	public UncategorizedScriptException(String message, Throwable cause) {
		super(message, cause);
	}

}
