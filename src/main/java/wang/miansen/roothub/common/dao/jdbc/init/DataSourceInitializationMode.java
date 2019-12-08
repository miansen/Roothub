package wang.miansen.roothub.common.dao.jdbc.init;

/**
 * 数据源初始化模式
 * @author: miansen.wang
 * @date: 2019-12-08
 */
public enum DataSourceInitializationMode {

	/**
	 * 总是初始化数据源。
	 */
	ALWAYS,
	
	/**
	 * 仅初始化嵌入的数据源。
	 */
	EMBEDDED,
	
	/**
	 * 不要初始化数据源。
	 */
	NEVER
}
