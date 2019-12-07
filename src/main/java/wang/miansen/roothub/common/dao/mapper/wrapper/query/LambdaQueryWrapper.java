package wang.miansen.roothub.common.dao.mapper.wrapper.query;

import wang.miansen.roothub.common.dao.mapper.metadata.TableFieldInfo;
import wang.miansen.roothub.common.dao.mapper.wrapper.AbstractWrapper;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Author: miansen.wang
 * @Date: 2019/10/8 21:50
 */
public class LambdaQueryWrapper<T> extends AbstractWrapper<T, LambdaQueryWrapper<T>, Function<T, ?>, Function<T, ?>> implements Query<T, LambdaQueryWrapper<T>, Function<T, ?>>{

    private String selectColumns;

    public LambdaQueryWrapper() {

    }

    public LambdaQueryWrapper(Class<T> modelClass) {
        super.modelClass = modelClass;
    }

    public LambdaQueryWrapper(Function<T, ?> columns) {
        this.select(columns);
    }

    public LambdaQueryWrapper(Class<T> modelClass, Function<T, ?> columns) {
        super.modelClass = modelClass;
        this.select(columns);
    }

    @Override
    protected String formatSqlValue(Function<T, ?> value) {
        return super.formatSqlValue(value);
    }

    @Override
    protected String columnToString(Function<T, ?> column) {
        return super.columnToString(column);
    }

    @Override
    public LambdaQueryWrapper<T> select(Function<T, ?>... columns) {
        return null;
    }

    @Override
    public LambdaQueryWrapper<T> select(Predicate<TableFieldInfo> predicate) {
        return null;
    }

    @Override
    public LambdaQueryWrapper<T> select(Class<T> modelClass, Predicate<TableFieldInfo> predicate) {
        return null;
    }

    public String getSelectColumns() {
        return selectColumns;
    }
}
