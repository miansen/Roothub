package wang.miansen.roothub.common.dao.jdbc.exceptions;

import wang.miansen.roothub.core.io.Resource;
import wang.miansen.roothub.common.dao.jdbc.util.ScriptUtils;

/**
 * 如果无法正确解析 SQL 脚本，则由 {@link ScriptUtils} 抛出此异常。
 * @author: miansen.wang
 * @date: 2019-12-07
 */
@SuppressWarnings("serial")
public class ScriptParseException extends ScriptException {

	/**
	 * 创建新的实例 {@code ScriptParseException}
	 * @param message 详细信息
	 * @param resource 读取 SQL 脚本的资源
	 */
	public ScriptParseException(String message, Resource resource) {
		super(buildMessage(message, resource));
	}

	/**
	 * 创建新的实例 {@code ScriptParseException}
	 * @param message 详细信息
	 * @param resource 读取 SQL 脚本的资源
	 * @param cause 无法正确解析 SQL 脚本的根本原因
	 */
	public ScriptParseException(String message, Resource resource, Throwable cause) {
		super(buildMessage(message, resource), cause);
	}

	/**
	 * 初始化异常信息
	 * @param message 异常信息
	 * @param resource 读取 SQL 脚本的资源
	 * @return String
	 */
	private static String buildMessage(String message, Resource resource) {
		return String.format("Failed to parse SQL script from resource [%s]: %s",
				(resource == null ? "<unknown>" : resource), message);
	}
}
