package wang.miansen.roothub.common.dao.jdbc.spring;

import org.springframework.context.ApplicationEvent;

/**
 * 初始化数据源的监听事件
 * @author: miansen.wang
 * @date: 2019-12-14
 */
@SuppressWarnings("serial")
public class DataSourceSchemaCreatedEvent extends ApplicationEvent {

	/**
	 * 创建一个新的实例 {@code DataSourceSchemaCreatedEvent}
	 * @param source 最初发生事件的对象
	 */
	public DataSourceSchemaCreatedEvent(Object source) {
		super(source);
	}

}
