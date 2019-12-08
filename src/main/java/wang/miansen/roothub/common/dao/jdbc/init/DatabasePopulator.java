package wang.miansen.roothub.common.dao.jdbc.init;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wang.miansen.roothub.common.dao.jdbc.exceptions.ScriptException;
import wang.miansen.roothub.common.dao.jdbc.util.ScriptUtils;
import wang.miansen.roothub.common.util.Assert;
import wang.miansen.roothub.core.io.Resource;

/**
 * 使用外部资源中定义的 SQL 脚本填充，初始化或清理数据库。
 * @author: miansen.wang
 * @date: 2019-12-07
 */
public class DatabasePopulator {

	/**
	 * {@code DDL} 或者 {@code DML} 脚本资源
	 */
	private List<Resource> scripts = new ArrayList<>();

	/**
	 * SQL 脚本语句分隔符，默认为 {@code ";"}。如果未指定，则以 {@code "\n"} 作为最后手段。
	 */
	private String separator = ScriptUtils.DEFAULT_STATEMENT_SEPARATOR;

	/**
	 * SQL 脚本中单行注释的前缀，默认为 {@code "--"}。
	 */
	private String commentPrefix = ScriptUtils.DEFAULT_COMMENT_PREFIX;

	/**
	 * SQL 脚本中块注释的开始分隔符，默认为 {@code "/*"}。
	 */
	private String blockCommentStartDelimiter = ScriptUtils.DEFAULT_BLOCK_COMMENT_START_DELIMITER;

	/**
	 * SQL 脚本中块注释的结束分隔符，默认为  <code>"*&#47;"</code>。
	 */
	private String blockCommentEndDelimiter = ScriptUtils.DEFAULT_BLOCK_COMMENT_END_DELIMITER;

	/**
	 * 执行 SQL 脚本发生错误时是否继续而不引发异常
	 */
	private boolean continueOnError = true;

	/**
	 * 执行 DROP 语句发生错误时是否继续而不引发异常
	 */
	private boolean ignoreFailedDrops = false;

	/**
	 * 使用默认配置创建新的实例
	 */
	public DatabasePopulator() {

	}

	/**
	 * 使用指定的 SQL 脚本资源创建新的实例
	 * @param scripts 要执行的初始化或清理数据库的脚本（不允许为 null）
	 */
	public DatabasePopulator(Resource... scripts) {
		setScripts(scripts);
	}

	/**
	 * 使用指定的 SQL 脚本资源创建新的实例
	 * @param scripts 要执行的初始化或清理数据库的脚本（不允许为 null）
	 */
	public DatabasePopulator(List<Resource> scripts) {
		setScripts(scripts);
	}
	
	/**
	 * 使用指定的值创建新的实例
	 * @param scripts 要执行的初始化或清理数据库的脚本（不允许为 null）
	 * @param continueOnError 执行 SQL 脚本发生错误时是否继续而不引发异常
	 * @param ignoreFailedDrops 执行 DROP 语句发生错误时是否继续而不引发异常
	 */
	public DatabasePopulator(List<Resource> scripts, boolean continueOnError, boolean ignoreFailedDrops) {
		this.scripts = scripts;
		this.continueOnError = continueOnError;
		this.ignoreFailedDrops = ignoreFailedDrops;
	}

	/**
	 * 使用提供的 JDBC 连接填充、初始化或清理数据库。
	 * <p>如果遇到错误，可能会抛出 ScriptException。
	 * @param connection 用于填充、初始化或清理数据库的 JDBC 连接。请确保已配置并可用，不允许为 null。
	 * @throws ScriptException 与 SQL 脚本处理相关的异常
	 */
	public void populate(Connection connection) throws ScriptException {
		Assert.notNull(connection, "Connection must not be null");
		for (Resource script : this.scripts) {
			ScriptUtils.executeSqlScript(connection, script, this.continueOnError, this.ignoreFailedDrops,
					this.separator, this.commentPrefix, this.blockCommentStartDelimiter, this.blockCommentEndDelimiter);
		}
	}

	/**
	 * 设置要执行的脚本资源以初始化或清理数据库，替换之前添加的所有脚本。
	 * @param scripts SQL 脚本资源
	 */
	public void setScripts(Resource... scripts) {
		this.scripts = new ArrayList<>(Arrays.asList(scripts));
	}
	
	/**
	 * 设置要执行的脚本资源以初始化或清理数据库，替换之前添加的所有脚本。
	 * @param scripts SQL 脚本资源
	 */
	public void setScripts(List<Resource> scripts) {
		this.scripts = scripts;
	}

	/**
	 * 设置 SQL 脚本的语句分隔符，默认为 {@code ";"}。
	 * @param separator SQL 脚本语句分隔符
	 */
	public void setSeparator(String separator) {
		this.separator = separator;
	}

	/**
	 * SQL 脚本中单行注释的前缀，默认为 {@code "--"}。
	 * @param commentPrefix SQL 脚本语句注释前缀
	 */
	public void setCommentPrefix(String commentPrefix) {
		this.commentPrefix = commentPrefix;
	}

	/**
	 * SQL 脚本中块注释的开始分隔符，默认为 {@code "/*"}。
	 * @param blockCommentStartDelimiter SQL 脚本中块注释开始分隔符
	 */
	public void setBlockCommentStartDelimiter(String blockCommentStartDelimiter) {
		this.blockCommentStartDelimiter = blockCommentStartDelimiter;
	}

	/**
	 * SQL 脚本中块注释的结束分隔符，默认为  <code>"*&#47;"</code>。
	 * @param blockCommentEndDelimiter SQL 脚本中块注释结束分隔符
	 */
	public void setBlockCommentEndDelimiter(String blockCommentEndDelimiter) {
		this.blockCommentEndDelimiter = blockCommentEndDelimiter;
	}

	/**
	 * 执行 SQL 失败时是否引发异常的标志。默认为 {@code false}。
	 * @param continueOnError {@code true} 忽略失败的 SQL 语句，继续往下执行。
	 */
	public void setContinueOnError(boolean continueOnError) {
		this.continueOnError = continueOnError;
	}

	/**
	 * 执行 DROP 语句失败时是否引发异常的标志。默认为 {@code false}。
	 * @param ignoreFailedDrops {@code true} 忽略失败的 DROP 语句，继续往下执行。
	 */
	public void setIgnoreFailedDrops(boolean ignoreFailedDrops) {
		this.ignoreFailedDrops = ignoreFailedDrops;
	}

}
