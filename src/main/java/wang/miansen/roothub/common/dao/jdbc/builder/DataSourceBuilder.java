package wang.miansen.roothub.common.dao.jdbc.builder;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

/**
 * @Author: miansen.wang
 * @Date: 2019/11/21 21:21
 */
public final class DataSourceBuilder<T extends DataSource> {

    private static final String[] DATA_SOURCE_TYPE_NAMES = new String[]{"com.zaxxer.hikari.HikariDataSource",
            "org.apache.tomcat.jdbc.pool.DataSource", "org.apache.commons.dbcp2.BasicDataSource"};

    private Class<? extends DataSource> type;

    private ClassLoader classLoader;
    
    private Map<String, String> properties = new HashMap<>();
 
    private DataSourceBuilder(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public static DataSourceBuilder<?> create(ClassLoader classLoader) {
        return new DataSourceBuilder<>(classLoader);
    }

    public T build() {
        Class<? extends DataSource> type = this.getType();
        if (type.isInterface()) {
			throw new RuntimeException("Specified class is an interface");
		}
        try {
            return (T) type.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
	public <D extends DataSource> DataSourceBuilder<D> type(Class<D> type) {
    	this.type = type;
    	return (DataSourceBuilder<D>) this;
    }
    
    public DataSourceBuilder<T> url(String url) {
    	this.properties.put("url", url);
    	return this;
    }
    
    public DataSourceBuilder<T> driverClassName(String driverClassName) {
    	this.properties.put("driverClassName", driverClassName);
    	return this;
    }
    
    public DataSourceBuilder<T> username(String username) {
    	this.properties.put("username", username);
    	return this;
    }
    
    public DataSourceBuilder<T> password(String password) {
    	this.properties.put("password", password);
    	return this;
    }
    
    public static Class<? extends DataSource> findType(ClassLoader classLoader) {
        return null;
    }

    private Class<? extends DataSource> getType() {
        Class<? extends DataSource> type = this.type != null ? this.type : findType(this.classLoader);
        if (type != null) {
            return type;
        } else {
            throw new IllegalStateException("No supported DataSource type found");
        }
    }
}
