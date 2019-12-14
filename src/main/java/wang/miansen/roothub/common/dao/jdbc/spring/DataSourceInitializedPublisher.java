package wang.miansen.roothub.common.dao.jdbc.spring;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import wang.miansen.roothub.common.dao.jdbc.init.DataSourceInitializer;
import wang.miansen.roothub.common.dao.jdbc.init.DataSourceProperties;

/**
 * 发布初始化数据源事件
 * @author: miansen.wang
 * @date: 2019-12-14
 */
public class DataSourceInitializedPublisher implements BeanPostProcessor, ApplicationContextAware {

	/**
	 * 上下文容器，可以使用该对象来发布事件。
	 */
	private ApplicationContext applicationContext;

	/**
	 * 数据源
	 */
	private DataSource dataSource;

	/**
	 * 数据库基本配置
	 */
	private DataSourceProperties dataSourceProperties;

	/**
	 * 数据源初始化器
	 */
	private DataSourceInitializer dataSourceInitializer;
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// 当事件监听器初始化好之后发布初始化数据源的事件。
		if (bean instanceof DataSourceInitializerListener) {
			// 只存在父容器时才发布，防止重复初始化。
			if (this.applicationContext.getParent() == null) {
				DataSourceInitializer initializer = getDataSourceInitializer();
				this.applicationContext.publishEvent(new DataSourceSchemaCreatedEvent(initializer));
			}
		}
		return bean;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 实例化 DataSourceInitializer
	 * @return DataSourceInitializer
	 */
	public DataSourceInitializer getDataSourceInitializer() {
		if (this.dataSourceInitializer == null) {
			this.dataSourceInitializer = new DataSourceInitializer(this.dataSource, this.dataSourceProperties);
		}
		return this.dataSourceInitializer;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setDataSourceProperties(DataSourceProperties dataSourceProperties) {
		this.dataSourceProperties = dataSourceProperties;
	}

}
