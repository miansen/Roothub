package wang.miansen.roothub.common.dao.jdbc.init;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wang.miansen.roothub.common.dao.jdbc.exceptions.ScriptException;
import wang.miansen.roothub.common.dao.jdbc.exceptions.UncategorizedScriptException;
import wang.miansen.roothub.core.io.Resource;
import wang.miansen.roothub.core.io.DefaultResourceLoader;
import wang.miansen.roothub.core.io.ResourceLoader;

/**
 * 数据源初始化器
 * @author: miansen.wang
 * @date: 2019-11-30
 */
public class DataSourceInitializer {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceInitializer.class);

	/**
	 * 数据源
	 */
	private final DataSource dataSource;

	/**
	 * 数据库基本配置
	 */
	private final DataSourceProperties dataSourceProperties;

	/**
	 * 资源加载器
	 */
	private final ResourceLoader resourceLoader;

	/**
	 * 使用 {@link DataSource} 和 {@link DataSourceProperties} 创建实例。
	 * @param dataSource 数据源
	 * @param dataSourceProperties 数据库基本配置
	 */
	public DataSourceInitializer(DataSource dataSource, DataSourceProperties dataSourceProperties) {
		this(dataSource, dataSourceProperties, null);
	}

	/**
	 * 使用 {@link DataSource} 、 {@link DataSourceProperties} 和 {@link ResourceLoader} 创建实例。
	 * <p>如果 {@link ResourceLoader} 为 null，则指定 {@link DefaultResourceLoader} 为缺省值。
	 * @param dataSource 数据源
	 * @param dataSourceProperties 数据库基本配置
	 * @param resourceLoader 资源加载器（可以为 null）
	 */
	public DataSourceInitializer(DataSource dataSource, DataSourceProperties dataSourceProperties,
			ResourceLoader resourceLoader) {
		this.dataSource = dataSource;
		this.dataSourceProperties = dataSourceProperties;
		this.resourceLoader = (resourceLoader != null) ? resourceLoader : new DefaultResourceLoader();
	}

	/**
	 * 创建数据库
	 * @return boolean
	 */
	public boolean createSchema() {
		List<Resource> scripts = getScripts(this.dataSourceProperties.getSchema());
		if (!scripts.isEmpty()) {
			if (!isEnabled()) {
				logger.debug("Initialization disabled (not running DDL scripts)");
				return false;
			}
			runScripts(scripts);
		}
		return !scripts.isEmpty();
	}

	/**
	 * 初始化数据库
	 */
	public void initSchema() {
		List<Resource> scripts = getScripts(this.dataSourceProperties.getData());
		if (!scripts.isEmpty()) {
			if (!isEnabled()) {
				logger.debug("Initialization disabled (not running data scripts)");
				return;
			}
			runScripts(scripts);
		}
	}

	/**
	 * 运行给定的 SQL 脚本。
	 * @param scripts SQL 脚本
	 */
	private void runScripts(List<Resource> scripts) {
		if (scripts.isEmpty()) {
			return;
		}
		DatabasePopulator populator = new DatabasePopulator();
		populator.setScripts(scripts);
		populator.setSeparator(this.dataSourceProperties.getSeparator());
		populator.setContinueOnError(this.dataSourceProperties.getContinueOnError());
		Connection connection = null;
		try {
			try {
				connection = this.dataSource.getConnection();
				populator.populate(connection);
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (ScriptException e) {
			throw e;
		} catch (SQLException e) {
			throw new UncategorizedScriptException("Failed to execute database script", e);
		}
	}

	/**
	 * 获取 DDL 或者 DML 的脚本资源
	 * @param locations 脚本路径
	 * @return List
	 */
	private List<Resource> getScripts(List<String> locations) {
		List<Resource> resources = new ArrayList<>();
		for (String location : locations) {
			Resource resource = this.resourceLoader.getResource(location);
			if (resource.exists()) {
				resources.add(resource);
			}
		}
		return resources;
	}

	/**
	 * 是否要初始化数据源
	 * @return boolean
	 */
	private boolean isEnabled() {
		DataSourceInitializationMode mode = this.dataSourceProperties.getInitializationMode();
		if (mode == DataSourceInitializationMode.NEVER) {
			return false;
		}
		return true;
	}

}
