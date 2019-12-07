package wang.miansen.roothub.common.dao.jdbc.datasource;

import wang.miansen.roothub.common.dao.jdbc.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: miansen.wang
 * @Date: 2019/11/21 15:54
 */
public class DriverManagerDataSource extends AbstractDriverBasedDataSource {

    public void setDriverClassName(String driverClassName) {
        if (!StringUtils.hasText(driverClassName)) {
            throw new IllegalArgumentException("Property 'driverClassName' must not be empty");
        }
        String driverClassNameToUse = driverClassName.trim();
        try {
            Class.forName(driverClassNameToUse, true, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Could not load JDBC driver class [" + driverClassNameToUse + "]", e);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Loaded JDBC driver: " + driverClassNameToUse);
        }
    }

    @Override
    protected Connection getConnectionFromDriver(Properties props) throws SQLException {
        String url = getUrl();
        if (StringUtils.isEmpty(url)) {
            throw new IllegalStateException("'url' not set");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Creating new JDBC DriverManager Connection to [" + url + "]");
        }
        return getConnectionFromDriverManager(url, props);
    }

    protected Connection getConnectionFromDriverManager(String url, Properties props) throws SQLException {
        return DriverManager.getConnection(url, props);
    }
}
