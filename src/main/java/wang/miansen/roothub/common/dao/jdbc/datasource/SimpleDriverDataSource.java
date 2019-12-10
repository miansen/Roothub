package wang.miansen.roothub.common.dao.jdbc.datasource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import wang.miansen.roothub.common.util.Assert;

/**
 * 标准 JDBC {@link javax.sql.DataSource} 接口的简单实现，通过 {@code driver} 属性配置一个普通的 JDBC {@link java.sql.Driver}，
 * 并从 {@code getConnection} 方法调用返回一个新的 {@link java.sql.Connection}。
 * <p><b>注意：这个类不是一个真正的连接池。</b>它只是作为连接池的简单替代，实现相同的标准接口，但在每次调用时都创建新的连接。
 * @author: miansen.wang
 * @date: 2019-12-09
 * @since 3.0.0
 */
public class SimpleDriverDataSource extends AbstractDriverBasedDataSource {

	/**
	 * 标准 JDBC 驱动程序对象
	 */
	private Driver driver;

	/**
	 * 创建新的实例
	 */
	public SimpleDriverDataSource() {

	}

	/**
	 * 使用标准 JDBC 驱动程序对象创建新的实例
	 * @param driver 标准 JDBC 驱动程序对象
	 * @param url 访问数据库的 JDBC url
	 */
	public SimpleDriverDataSource(Driver driver, String url) {
		setDriver(driver);
		setUrl(url);
	}

	/**
	 * 使用标准 JDBC 驱动程序对象创建新的实例
	 * @param driver 标准 JDBC 驱动程序对象
	 * @param url 访问数据库的 JDBC url
	 * @param username 访问数据库的用户名
	 * @param password 访问数据库的密码
	 */
	public SimpleDriverDataSource(Driver driver, String url, String username, String password) {
		setDriver(driver);
		setUrl(url);
		setUsername(username);
		setPassword(password);
	}

	/**
	 * 使用标准 JDBC 驱动程序对象创建新的实例
	 * @param driver 标准 JDBC 驱动程序对象
	 * @param url 访问数据库的 JDBC url
	 * @param conProps JDBC 连接属性
	 */
	public SimpleDriverDataSource(Driver driver, String url, Properties conProps) {
		setDriver(driver);
		setUrl(url);
		setConnectionProperties(conProps);
	}

	@Override
	protected Connection getConnectionFromDriver(Properties props) throws SQLException {
		Driver driver = getDriver();
		String url = getUrl();
		Assert.notNull(driver, "Driver must not be null");
		logger.debug("Creating new JDBC Driver Connection to [" + url + "]");
		return driver.connect(url, props);
	}

	/**
	 * 返回 JDBC 驱动程序实例。
	 * @return Driver
	 */
	public Driver getDriver() {
		return driver;
	}

	/**
	 * 设置 JDBC 驱动程序实例。
	 * @param driver JDBC 驱动程序实例
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	/**
	 * 设置 JDBC 驱动程序实例。
	 * <p>通过反射创建一个实例，并将它的引用保存在 {@code driver} 中。
	 * @param driverClass JDBC 驱动程序 Class
	 * @throws Exception
	 */
	public void setDriver(Class<? extends Driver> driverClass) throws Exception {
		this.driver = driverClass.newInstance();
	}

}
