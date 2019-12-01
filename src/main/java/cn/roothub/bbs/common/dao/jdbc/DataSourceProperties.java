package cn.roothub.bbs.common.dao.jdbc;

import java.util.List;

import javax.sql.DataSource;

import cn.roothub.bbs.common.dao.jdbc.builder.DataSourceBuilder;

/**
 * <p>数据库基本配置</p>
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
	private String url;
	
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
	 * 使用此实例的属性初始化 {@link DataSourceBuilder}
	 * @return
	 */
	public DataSourceBuilder<?> initializeDataSourceBuilder() {
		return DataSourceBuilder.create(Thread.currentThread().getContextClassLoader())
				.type(getType()).driverClassName(getDriverClassName()).url(getUrl())
				.username(getUsername()).password(getPassword());
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	@Override
	public String toString() {
		return "DataSourceProperties [type=" + type + ", driverClassName=" + driverClassName + ", url=" + url
				+ ", username=" + username + ", password=" + password + ", schema=" + schema + ", data=" + data + "]";
	}
	
}
