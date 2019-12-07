package wang.miansen.roothub.common.dao.jdbc.datasource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: miansen.wang
 * @Date: 2019/11/21 15:44
 */
public abstract class AbstractDriverBasedDataSource extends AbstractDataSource {

    private String url;

    private String username;

    private String password;

    private Properties connectionProperties;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = (url != null ? url.trim() : null);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Properties getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(Properties connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getConnectionFromDriver(getUsername(), getPassword());
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getConnectionFromDriver(username, password);
    }

    protected Connection getConnectionFromDriver(String username, String password) throws SQLException {
        Properties mergedProps = new Properties();
        Properties connProps = getConnectionProperties();
        if (connProps != null) {
            mergedProps.putAll(connProps);
        }
        if (username != null) {
            mergedProps.setProperty("user", username);
        }
        if (password != null) {
            mergedProps.setProperty("password", password);
        }
        return getConnectionFromDriver(mergedProps);
    }

    /**
     * 使用给定的属性获取连接。
     * <p>由子类实现</p>
     * @param props
     * @return
     * @throws SQLException
     */
    protected  abstract Connection getConnectionFromDriver(Properties props) throws SQLException;
}
