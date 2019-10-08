package cn.roothub.bbs.common.dao.wrapper.query;

import cn.roothub.bbs.common.dao.wrapper.AbstractWrapper;

import java.util.function.Predicate;

/**
 * @Author: miansen.wang
 * @Date: 2019/10/8 21:50
 */
public class LambdaQueryWrapper<T> extends AbstractWrapper implements Query{

    private String selectColumns;

    public LambdaQueryWrapper() {

    }

    public LambdaQueryWrapper(Class<T> modelClass) {
        super.modelClass = modelClass;
    }

    public LambdaQueryWrapper(String... columns) {
        this.select(columns);
    }

    public LambdaQueryWrapper(Class<T> modelClass, String... columns) {
        super.modelClass = modelClass;
        this.select(columns);
    }

    @Override
    public Object select(Object[] columns) {
        return null;
    }

    @Override
    public Object select(Predicate predicate) {
        return null;
    }

    @Override
    public Object select(Class modelClass, Predicate predicate) {
        return null;
    }

    @Override
    protected String columnToString(Object column) {
        return super.columnToString(column);
    }

    @Override
    protected String formatSqlValue(Object value) {
        return super.formatSqlValue(value);
    }

    public String getSelectColumns() {
        return selectColumns;
    }
}
