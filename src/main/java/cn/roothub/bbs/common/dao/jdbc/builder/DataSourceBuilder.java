package cn.roothub.bbs.common.dao.jdbc.builder;

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

    private DataSourceBuilder(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public static DataSourceBuilder<?> create(ClassLoader classLoader) {
        return new DataSourceBuilder(classLoader);
    }

    public T build() {
        Class<? extends DataSource> type = this.getType();
        try {
            DataSource dataSource = (DataSource) type.newInstance();
            return dataSource;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
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
