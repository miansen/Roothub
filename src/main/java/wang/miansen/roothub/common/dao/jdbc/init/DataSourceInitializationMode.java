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
	 * 仅在创建数据库的时候初始化数据源。
	 * <p><b>注意：这个模式不支持 H2Database。</b>
	 */
	ONLY_CREATE_DATABASE,
	
	/**
	 * 不要初始化数据源。
	 */
	NEVER
}
