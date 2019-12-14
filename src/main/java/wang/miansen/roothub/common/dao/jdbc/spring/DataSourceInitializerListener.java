package wang.miansen.roothub.common.dao.jdbc.spring;

import org.springframework.context.ApplicationListener;

import wang.miansen.roothub.common.dao.jdbc.init.DataSourceInitializer;

/**
 * 数据源初始化监听器
 * @author: miansen.wang
 * @date: 2019-12-14
 */
public class DataSourceInitializerListener implements ApplicationListener<DataSourceSchemaCreatedEvent> {

	/**
	 * 执行监听方法，当 {@code initializer} 不为 null 时初始化数据源。
	 */
	@Override
	public void onApplicationEvent(DataSourceSchemaCreatedEvent event) {
		DataSourceInitializer initializer = null;
		Object source = event.getSource();
		if (source instanceof DataSourceInitializer) {
			initializer = (DataSourceInitializer) source;
		}
		if (initializer != null) {
			boolean schemaCreated = initializer.createSchema();
			if (schemaCreated) {
				initializer.initSchema();
			}
		}
	}

}
