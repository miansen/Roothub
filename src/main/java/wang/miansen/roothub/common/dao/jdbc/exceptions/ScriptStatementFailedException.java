package wang.miansen.roothub.common.dao.jdbc.exceptions;

import wang.miansen.roothub.core.io.Resource;
import wang.miansen.roothub.common.dao.jdbc.util.ScriptUtils;

/**
 * 如果 SQL 脚本中的语句在目标数据库执行时失败，则由 {@link ScriptUtils} 抛出此异常。
 * @author: miansen.wang
 * @date: 2019-12-07
 */
@SuppressWarnings("serial")
public class ScriptStatementFailedException extends ScriptException {

	/**
	 * 创建新的实例 {@code ScriptStatementFailedException}
	 * @param statement 失败的实际SQL语句
	 * @param stmtNumber SQL 脚本中的语句编号（即资源中存在的第 n 条语句）
	 * @param resource 读取 SQL 脚本的资源
	 * @param cause 导致失败的根本原因
	 */
	public ScriptStatementFailedException(String statement, int stmtNumber, Resource resource, Throwable cause) {
		super(buildErrorMessage(statement, stmtNumber, resource), cause);
	}
	
	/**
	 * 根据提供的参数为 SQL 脚本执行失败生成错误消息。
	 * @param statement 失败的实际SQL语句
	 * @param stmtNumber SQL 脚本中的语句编号（即资源中存在的第 n 条语句）
	 * @param resource 读取 SQL 脚本的资源
	 * @return 适用于异常的详细消息或日志记录的错误消息
	 */
	public static String buildErrorMessage(String statement, int stmtNumber, Resource resource) {
		return String.format("Failed to execute SQL script statement #%s of %s: %s", stmtNumber, resource, statement);
	}

}
