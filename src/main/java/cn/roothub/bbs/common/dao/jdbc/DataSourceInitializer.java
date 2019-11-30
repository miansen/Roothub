package cn.roothub.bbs.common.dao.jdbc;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-11-30
 */
public class DataSourceInitializer implements FactoryBean<DataSource> {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceInitializer.class);
	
	/**
	 * 数据源
	 */
	private DataSource dataSource;
	
	/**
	 * 数据库基本配置
	 */
	private DataSourceProperties dataSourceProperties;
	
	public DataSourceInitializer() {
		
	}

	public DataSourceInitializer(DataSource dataSource, DataSourceProperties dataSourceProperties) {
		this.dataSource = dataSource;
		this.dataSourceProperties = dataSourceProperties;
	}

	
	@Override
	public DataSource getObject() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Class<?> getObjectType() {
		return null;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
}
