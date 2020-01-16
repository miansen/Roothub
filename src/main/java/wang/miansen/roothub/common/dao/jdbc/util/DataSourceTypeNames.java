package wang.miansen.roothub.common.dao.jdbc.util;

/**
 * 可以支持的 JDBC 数据库连接池的完全限定名
 * 
 * @author miansen.wang
 * @date 2019-12-01
 * @since 3.0
 */
public interface DataSourceTypeNames {

	String C3P0 = "com.mchange.v2.c3p0.ComboPooledDataSource";
	
	String HIKARI = "com.zaxxer.hikari.HikariDataSource";
	
	String TOMCAT = "org.apache.tomcat.jdbc.pool.DataSource";
	
	String DBCP2 = "org.apache.commons.dbcp2.BasicDataSource";
	
	String DRIVERMANAGER = "wang.miansen.roothub.common.dao.jdbc.datasource.DriverManagerDataSource";
}
