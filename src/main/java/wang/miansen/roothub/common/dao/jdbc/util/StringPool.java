package wang.miansen.roothub.common.dao.jdbc.util;

/**
 * <p>数据源类型</p>
 * @author: miansen.wang
 * @date: 2019-12-01
 */
public interface StringPool {

	String C3P0 = "com.mchange.v2.c3p0.ComboPooledDataSource";
	
	String HIKARI = "com.zaxxer.hikari.HikariDataSource";
	
	String TOMCAT = "org.apache.tomcat.jdbc.pool.DataSource";
	
	String DBCP2 = "org.apache.commons.dbcp2.BasicDataSource";
	
	String DRIVERMANAGER = "wang.miansen.roothub.common.dao.jdbc.datasource.DriverManagerDataSource";
}
