package cn.roothub.bbs.common.dao.jdbc;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-11-30
 */
public class DataSourceInitializer {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceInitializer.class);
	
	/**
	 * 数据源
	 */
	private DataSource dataSource;
	
	/**
	 * 数据库基本配置
	 */
	private DataSourceProperties dataSourceProperties;

	public DataSourceInitializer(DataSource dataSource, DataSourceProperties dataSourceProperties) {
		this.dataSource = dataSource;
		this.dataSourceProperties = dataSourceProperties;
	}
	
}
