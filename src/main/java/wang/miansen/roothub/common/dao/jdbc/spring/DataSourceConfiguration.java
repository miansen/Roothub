package wang.miansen.roothub.common.dao.jdbc.spring;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.FactoryBean;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import wang.miansen.roothub.common.dao.jdbc.datasource.DriverManagerDataSource;
import wang.miansen.roothub.common.dao.jdbc.init.DataSourceProperties;
import wang.miansen.roothub.common.dao.jdbc.util.StringPool;

/**
 * <p>与 Spring 集成，根据 {@link DataSourceProperties} 的 {@code dataSourceClassName} 属性创建数据源并注入到 IOC 容器。</p>
 * <p>如果 {@code dataSourceClassName} 属性为空，则会将 {@link DriverManagerDataSource} 作为缺省的数据源 。</p>
 * 
 * @author: miansen.wang
 * @date: 2019-12-01
 * @since 3.0.0
 */
public class DataSourceConfiguration implements FactoryBean<DataSource>, StringPool {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);

	/**
	 * 数据库基本配置
	 */
	private DataSourceProperties dataSourceProperties;

	/**
	 * 数据源
	 */
	private DataSource dataSource;

	/**
	 * 创建数据源
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T createDataSource(Class<? extends DataSource> type) {
		return (T) dataSourceProperties.initializeDataSourceBuilder().type(type).build();

	}

	/**
	 * 将数据源注入 IOC 容器，最终注入的 bean 的类型，取决于 {@code dataSourceClassName}。
	 * <p>注入的同时会发布初始化数据源的事件。
	 */
	@Override
	public DataSource getObject() throws Exception {
		DataSource dataSource = null;
		final String dataSourceClassName = dataSourceProperties.getDataSourceClassName();
		switch (dataSourceClassName) {
		case C3P0:
			dataSource = new C3p0().dataSource(dataSourceProperties);
			break;
		case HIKARI:
			break;
		case TOMCAT:
			break;
		case DBCP2:
			break;
		default:
			dataSource = new DriverManager().dataSource(dataSourceProperties);
			break;
		}
		this.dataSource = dataSource;
		return dataSource;
	}

	@Override
	public Class<DataSource> getObjectType() {
		return DataSource.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	/**
	 * C3p0 数据源配置
	 */
	private class C3p0 {

		public ComboPooledDataSource dataSource(DataSourceProperties dataSourceProperties) {
			ComboPooledDataSource dataSource = createDataSource(ComboPooledDataSource.class);
			try {
				dataSource.setDriverClass(dataSourceProperties.getDriverClassName());
				dataSource.setJdbcUrl(dataSourceProperties.getJdbcUrl());
				dataSource.setUser(dataSourceProperties.getUsername());
				dataSource.setPassword(dataSourceProperties.getPassword());
				dataSource.setMaxPoolSize(30);
				dataSource.setMinPoolSize(10);
				dataSource.setAutoCommitOnClose(false);
				dataSource.setCheckoutTimeout(10000);
				dataSource.setAcquireRetryAttempts(2);
			} catch (PropertyVetoException e) {
				logger.debug("initialization ComboPooledDataSource error: ", e.getMessage());
			}
			return dataSource;
		}

	}

	/**
	 * DriverManager 数据源配置
	 */
	private class DriverManager {
		public DriverManagerDataSource dataSource(DataSourceProperties dataSourceProperties) {
			DriverManagerDataSource dataSource = createDataSource(DriverManagerDataSource.class);
			dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
			dataSource.setUrl(dataSourceProperties.getJdbcUrl());
			dataSource.setUsername(dataSourceProperties.getUsername());
			dataSource.setPassword(dataSourceProperties.getPassword());
			return dataSource;
		}
	}

	public void setDataSourceProperties(DataSourceProperties dataSourceProperties) {
		this.dataSourceProperties = dataSourceProperties;
	}

}
