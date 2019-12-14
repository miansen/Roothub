package wang.miansen.roothub.common.dao.jdbc.spring;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import wang.miansen.roothub.common.dao.jdbc.datasource.DriverManagerDataSource;
import wang.miansen.roothub.common.dao.jdbc.init.DataSourceInitializer;
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
public class DataSourceConfiguration implements FactoryBean<DataSource>, ApplicationContextAware, StringPool {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);

	/**
	 * 数据库基本配置
	 */
	private DataSourceProperties dataSourceProperties;

	/**
	 * 数据源初始化器
	 */
	private DataSourceInitializer dataSourceInitializer;

	/**
	 * 数据源
	 */
	private DataSource dataSource;

	/**
	 * 上下文容器，主要的作用是防止重复初始化数据源。
	 */
	private ApplicationContext applicationContext;

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
	 * 最终注入 IOC 容器的 bean 的类型，取决于 {@code dataSourceClassName}。
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
		// 只存在父容器时才初始化数据源，防止重复初始化。
		if (this.applicationContext.getParent() == null) {
			DataSourceInitializer initializer = getDataSourceInitializer();
			if (initializer != null) {
				boolean schemaCreated = initializer.createSchema();
				if (schemaCreated) {
					initializer.initSchema();
				}
			}
		}
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

	public DataSourceInitializer getDataSourceInitializer() {
		if (this.dataSourceInitializer == null) {
			this.dataSourceInitializer = new DataSourceInitializer(this.dataSource, this.dataSourceProperties);
		}
		return this.dataSourceInitializer;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
