package wang.miansen.roothub.common.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import wang.miansen.roothub.core.io.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}
