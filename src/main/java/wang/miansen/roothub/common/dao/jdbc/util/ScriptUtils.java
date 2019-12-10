package wang.miansen.roothub.common.dao.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wang.miansen.roothub.common.dao.jdbc.exceptions.CannotReadScriptException;
import wang.miansen.roothub.common.dao.jdbc.exceptions.ScriptException;
import wang.miansen.roothub.common.dao.jdbc.exceptions.ScriptParseException;
import wang.miansen.roothub.common.dao.jdbc.exceptions.ScriptStatementFailedException;
import wang.miansen.roothub.common.dao.jdbc.exceptions.UncategorizedScriptException;
import wang.miansen.roothub.common.util.Assert;
import wang.miansen.roothub.core.io.Resource;

/**
 * 跟 SQL 脚本相关的通用方法
 * @author: miansen.wang
 * @date: 2019-12-07
 */
public abstract class ScriptUtils {

	private static final Logger logger = LoggerFactory.getLogger(ScriptUtils.class);

	/**
	 * SQL 脚本中的默认语句分隔符：{@code ";"}。
	 */
	public static final String DEFAULT_STATEMENT_SEPARATOR = ";";

	/**
	 * SQL 脚本中单行注释的默认前缀：{@code "--"}。
	 */
	public static final String DEFAULT_COMMENT_PREFIX = "--";

	/**
	 * SQL 脚本中块注释的默认开始分隔符：{@code "/*"}。
	 */
	public static final String DEFAULT_BLOCK_COMMENT_START_DELIMITER = "/*";

	/**
	 * SQL 脚本中块注释的默认结束分隔符：<code>"*&#47;"</code>。
	 */
	public static final String DEFAULT_BLOCK_COMMENT_END_DELIMITER = "*/";

	/**
	 * 执行给定的 SQL 脚本。
	 * <p>在执行所提供脚本中的个别语句之前，将删除语句分隔符和注释。
	 * <p><b>注意：此方法不会关闭 JDBC connection。</b>
	 * @param connection 用于填充、初始化或清理数据库的 JDBC 连接。请确保已配置并可用，不允许为 null。
	 * @param resource 读取 SQL 脚本的资源
	 * @param continueOnError 执行 SQL 脚本发生错误时是否继续而不引发异常
	 * @param ignoreFailedDrops 执行 DROP 语句发生错误时是否继续而不引发异常
	 * @param separator SQL 脚本语句分隔符
	 * @param commentPrefix SQL 脚本中单行注释的前缀
	 * @param blockCommentStartDelimiter SQL 脚本中块注释的开始分隔符
	 * @param blockCommentEndDelimiter SQL 脚本中块注释的结束分隔符
	 * @throws ScriptException 执行 SQL 脚本时发生错误则抛出此异常
	 */
	public static void executeSqlScript(Connection connection, Resource resource, boolean continueOnError,
			boolean ignoreFailedDrops, String separator, String commentPrefix, String blockCommentStartDelimiter,
			String blockCommentEndDelimiter) throws ScriptException {
		try {
			logger.debug("Executing SQL script from " + resource);
			long startTime = System.currentTimeMillis();
			String script;
			try {
				script = readScript(resource, commentPrefix, separator);
			} catch (IOException e) {
				throw new CannotReadScriptException(resource, e);
			}
			if (separator == null) {
				separator = DEFAULT_STATEMENT_SEPARATOR;
			}
			if (commentPrefix == null) {
				commentPrefix = DEFAULT_COMMENT_PREFIX;
			}
			if (blockCommentStartDelimiter == null) {
				blockCommentStartDelimiter = DEFAULT_BLOCK_COMMENT_START_DELIMITER;
			}
			if (blockCommentEndDelimiter == null) {
				blockCommentEndDelimiter = DEFAULT_BLOCK_COMMENT_END_DELIMITER;
			}
			List<String> statements = new LinkedList<>();
			splitSqlScript(resource, script, separator, commentPrefix, blockCommentStartDelimiter,
					blockCommentEndDelimiter, statements);
			int stmtNumber = 0;
			Statement stmt = connection.createStatement();
			try {
				for (String statement : statements) {
					stmtNumber++;
					try {
						stmt.execute(statement);
						int rowsAffected = stmt.getUpdateCount();
						logger.debug(rowsAffected + " returned as update count for SQL: " + statement);
						SQLWarning warnings = stmt.getWarnings();
						while (warnings != null) {
							logger.debug("SQLWarning ignored: SQL state '" + warnings.getSQLState() + "', error code '"
									+ warnings.getErrorCode() + "', message [" + warnings.getMessage() + "]");
							warnings = warnings.getNextWarning();
						}
					} catch (SQLException e) {
						boolean dropStatement = statement.startsWith("DROP") || statement.startsWith("drop");
						// 在循环里面捕捉异常，然后根据给定的参数决定是继续往下执行还是抛出异常。
						if (continueOnError || (dropStatement && ignoreFailedDrops)) {
							logger.debug(String.format("Failed to execute SQL script statement #%s of %s: %s",
									stmtNumber, resource, stmt), e);
						} else {
							throw new ScriptStatementFailedException(statement, stmtNumber, resource, e);
						}
					}
				}
			} finally {
				try {
					// 循环无论是正常结束还是被异常中断，最终都必须关闭 JDBC 连接。
					stmt.close();
				} catch (Exception e) {
					logger.debug("Could not close JDBC Statement", e);
				}
			}
			long elapsedTime = System.currentTimeMillis() - startTime;
			logger.debug("Executed SQL script from " + resource + " in " + elapsedTime + " ms.");
		} catch (Exception e) {
			// 最后在捕捉一下异常，如果异常的类型是 ScriptException，则转换后继续抛出。
			if (e instanceof ScriptException) {
				throw (ScriptException) e;
			}
			// 如果是无法确定的异常，则抛出 UncategorizedScriptException。
			throw new UncategorizedScriptException("Failed to execute database script from resource [" + resource + "]",
					e);
		}
	}

	/**
	 * 根据提供的分隔符 {@code separator} 将 SQL 脚本拆分为单独语句。每个单独的语句都将添加到提供的 {@code statements} 中。
	 * <p>忽略以 {@code commentPrefix} 开头并延伸到行结尾的任何文本。
	 * <p>忽略被 {@code blockCommentStartDelimiter} 和 {@code blockCommentEndDelimiter} 包含的任何文本。
	 * <p>此外，多个相邻的空白字符将折叠为一个空格。
	 * @param resource 读取 SQL 脚本的资源
	 * @param script SQL 脚本，不允许为 null 或者空
	 * @param separator SQL 脚本分隔符
	 * @param commentPrefix SQL 脚本行注释的前缀
	 * @param blockCommentStartDelimiter SQL 脚本中块注释开始分隔符
	 * @param blockCommentEndDelimiter SQL 脚本中块注释结束分隔符
	 * @param statements 包含单个 SQL 脚本语句的列表
	 */
	private static void splitSqlScript(Resource resource, String script, String separator, String commentPrefix,
			String blockCommentStartDelimiter, String blockCommentEndDelimiter, List<String> statements) {
		Assert.notEmpty(script, "'separator' must not be null");
		Assert.notEmpty(commentPrefix, "'commentPrefix' must not be null or empty");
		StringBuilder sb = new StringBuilder();
		boolean inSingleQuote = false;
		boolean inDoubleQuote = false;
		boolean inEscape = false;
		for (int i = 0; i < script.length(); i++) {
			char c = script.charAt(i);
			if (inEscape) {
				inEscape = false;
				sb.append(c);
				continue;
			}
			// 反斜杠及其后面的字符直接 append
			if (c == '\\') {
				inEscape = true;
				sb.append(c);
				continue;
			}
			// 是单引号吗？
			if (!inDoubleQuote && (c == '\'')) {
				inSingleQuote = !inSingleQuote;
			}
			// 是双引号吗？
			if (!inSingleQuote && (c == '"')) {
				inDoubleQuote = !inDoubleQuote;
			}
			// 单引号或者双引号结束了才进一步判断
			if (!inSingleQuote && !inDoubleQuote) {
				// 遇到了 SQL 脚本分隔符，说明找到了一个 SQL 脚本语句
				if (script.startsWith(separator, i)) {
					if (sb.length() > 0) {
						statements.add(sb.toString());
						// 清空重来
						sb = new StringBuilder();
					}
					// 偏移量增加，下次遍历的时候跳过分隔符。
					i += separator.length() - 1;
					continue;
				}
				// 遇到行注释符
				if (script.startsWith(commentPrefix, i)) {
					// 跳过从行注释开始到结束的任何内容
					int indexOfNextNewline = script.indexOf("\n", i);
					if (indexOfNextNewline > i) {
						// 偏移量增加，下次遍历的时候跳过注释的所有内容。
						i = indexOfNextNewline;
						continue;
					} else {
						// 如果没有换行，说明已经到末尾了，就此结束。
						break;
					}
				}
				// 遇到块注释符
				if (script.startsWith(blockCommentStartDelimiter, i)) {
					// 跳过任何块注释的内容
					int indexOfCommentEnd = script.indexOf(blockCommentEndDelimiter, i);
					if (indexOfCommentEnd > i) {
						// 偏移量增加，下次遍历的时候跳过注释块中的所有内容。
						i = indexOfCommentEnd + blockCommentStartDelimiter.length();
						continue;
					} else {
						throw new ScriptParseException(
								"Missing block comment end delimiter: " + blockCommentEndDelimiter, resource);
					}
				}
				// 多个相邻的空白字将折叠为一个空格
				if (c == ' ' || c == '\n' || c == '\t') {
					if (sb.length() > 0 && sb.charAt(sb.length() - 1) != ' ') {
						c = ' ';
					} else {
						continue;
					}
				}
			}
			// 单引号或者双引号没结束的话直接 append
			sb.append(c);
		}
		if (sb.length() > 0) {
			statements.add(sb.toString());
		}
	}

	/**
	 * 使用提供的 SQL 行注释前缀和语句分隔符从提供的资源中读取脚本，并生成包含行的字符串。
	 * <p>以注释前缀开头的行将从结果中排除。但是，其他任何地方（例如语句中）的行注释都将包含在结果中。
	 * @param resource 读取 SQL 脚本的资源
	 * @param separator SQL 脚本分隔符
	 * @param commentPrefix SQL 脚本行注释的前缀
	 * @return String 包含脚本行的字符串
	 * @throws IOException 遇到 I/O 错误抛出此异常
	 */
	private static String readScript(Resource resource, String separator, String commentPrefix) throws IOException {
		InputStream is = resource.getInputStream();
		InputStreamReader reader = new InputStreamReader(is);
		LineNumberReader lnr = new LineNumberReader(reader);
		String currentStatement = lnr.readLine();
		StringBuilder scriptBuilder = new StringBuilder();
		while (currentStatement != null) {
			// 忽略行注释
			if (commentPrefix != null && !currentStatement.startsWith(commentPrefix)) {
				if (scriptBuilder.length() > 0) {
					scriptBuilder.append('\n');
				}
				scriptBuilder.append(currentStatement);
			}
			currentStatement = lnr.readLine();
		}
		return scriptBuilder.toString();
	}

}
