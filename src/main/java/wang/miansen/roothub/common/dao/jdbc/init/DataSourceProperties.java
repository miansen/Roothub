package wang.miansen.roothub.common.dao.jdbc.init;

import java.util.List;

import javax.sql.DataSource;

import wang.miansen.roothub.common.dao.jdbc.builder.DataSourceBuilder;

/**
 * 数据库基本配置
 * @author: miansen.wang
 * @date: 2019-11-30
 */
public class DataSourceProperties {

	/**
	 * JDBC 数据库连接池 Class
	 */
	private Class<? extends DataSource> type;

	/**
	 * JDBC 数据库连接池的完全限定名
	 */
	private String dataSourceClassName;

	/**
	 * JDBC 驱动程序的完全限定名
	 */
	private String driverClassName;

	/**
	 * 数据库的 JDBC URL
	 */
	private String jdbcUrl;

	/**
	 * 数据库 URL（不包含数据库名）
	 */
	private String dbUrl;

	/**
	 * 数据库名
	 */
	private String database;

	/**
	 * 数据库的登录用户名
	 */
	private String username;

	/**
	 * 数据库的登录密码
	 */
	private String password;

	/**
	 * DDL 脚本路径
	 */
	private List<String> schema;

	/**
	 * DML 脚本路径
	 */
	private List<String> data;

	/**
	 * 执行 SQL 脚本发生错误时是否继续而不引发异常
	 */
	private boolean continueOnError = false;
	
	/**
	 * SQL 脚本语句分隔符
	 */
	private String separator = ";";
	
	/**
	 * 数据源初始化模式
	 */
	private DataSourceInitializationMode initializationMode = DataSourceInitializationMode.ALWAYS;

	/**
	 * 使用此实例的属性初始化 {@link DataSourceBuilder}
	 * @return
	 */
	public DataSourceBuilder<?> initializeDataSourceBuilder() {
		return DataSourceBuilder.create(Thread.currentThread().getContextClassLoader()).type(getType())
				.driverClassName(getDriverClassName()).url(getJdbcUrl()).username(getUsername())
				.password(getPassword());
	}

	public Class<? extends DataSource> getType() {
		return type;
	}

	public void setType(Class<? extends DataSource> type) {
		this.type = type;
	}

	public String getDataSourceClassName() {
		return dataSourceClassName;
	}

	public void setDataSourceClassName(String dataSourceClassName) {
		this.dataSourceClassName = dataSourceClassName;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getDbUrl() {
		String[] var0 = getJdbcUrl().split("//");
		String dbName = var0[0];
		String[] var1 = var0[1].split("/|\\?");
		dbUrl = dbName + var1[0];
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDatabase() {
		String[] var0 = getJdbcUrl().split("//");
		String[] var1 = var0[1].split("/|\\?");
		database = var1[1];
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getSchema() {
		return schema;
	}

	public void setSchema(List<String> schema) {
		this.schema = schema;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	public boolean getContinueOnError() {
		return continueOnError;
	}

	public void setContinueOnError(boolean continueOnError) {
		this.continueOnError = continueOnError;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public DataSourceInitializationMode getInitializationMode() {
		return initializationMode;
	}

	public void setInitializationMode(DataSourceInitializationMode initializationMode) {
		this.initializationMode = initializationMode;
	}

}
