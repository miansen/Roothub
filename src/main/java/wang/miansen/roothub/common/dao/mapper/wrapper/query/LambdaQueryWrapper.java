package wang.miansen.roothub.common.dao.mapper.wrapper.query;

import wang.miansen.roothub.common.dao.mapper.builder.TableInfoBuilder;
import wang.miansen.roothub.common.dao.mapper.exceptions.BaseMapperException;
import wang.miansen.roothub.common.dao.mapper.metadata.TableFieldInfo;
import wang.miansen.roothub.common.dao.mapper.util.ArrayUtils;
import wang.miansen.roothub.common.dao.mapper.util.StringPool;
import wang.miansen.roothub.common.dao.mapper.wrapper.AbstractLambdaWrapper;
import wang.miansen.roothub.common.dao.mapper.wrapper.SFunction;
import wang.miansen.roothub.common.dao.mapper.wrapper.segments.SqlSegmentBuilder;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Lambda 查询条件包装器
 *
 * @param <T> 实体类的类型
 * @Author: miansen.wang
 * @Date: 2019/10/8 21:50
 */
public class LambdaQueryWrapper<T> extends AbstractLambdaWrapper<T, LambdaQueryWrapper<T>> implements
    Query<T, LambdaQueryWrapper<T>, SFunction<T, ?>> {

    private String selectColumns;

    public LambdaQueryWrapper() {

    }

    public LambdaQueryWrapper(Class<T> modelClass) {
        super.modelClass = modelClass;
    }

    public LambdaQueryWrapper(SFunction<T, ?>... columns) {
        this.select(columns);
    }

    public LambdaQueryWrapper(T model, Class<T> modelClass, String selectColumns, Map<String, Object> paramNameValuePairs,
        AtomicInteger paramNameSeq, SqlSegmentBuilder sqlSegmentBuilder) {
        super.model = model;
        super.modelClass = modelClass;
        super.paramNameValuePairs = paramNameValuePairs;
        super.paramNameSeq = paramNameSeq;
        super.sqlSegmentBuilder = sqlSegmentBuilder;
        this.selectColumns = selectColumns;
    }

    public LambdaQueryWrapper(Class<T> modelClass, SFunction<T, ?>... columns) {
        super.modelClass = modelClass;
        this.select(columns);
    }

    @Override
    public LambdaQueryWrapper<T> select(SFunction<T, ?>... columns) {
        if (ArrayUtils.isNotEmpty(columns)) {
            this.selectColumns = columnsToString(columns);
        }
        return typedThis;
    }

    @Override
    public LambdaQueryWrapper<T> select(Predicate<TableFieldInfo> predicate) {
        if (super.modelClass == null) {
            throw new BaseMapperException("modelClass must be not null,please set modelClass before use this method!");
        }
        return select(super.modelClass, predicate);
    }

    @Override
    public LambdaQueryWrapper<T> select(Class<T> modelClass, Predicate<TableFieldInfo> predicate) {
        super.modelClass = modelClass;
        this.selectColumns = TableInfoBuilder.getTableInfo(modelClass).getTableFieldInfoList().stream().filter(predicate)
            .map(TableFieldInfo::getColumn).collect(Collectors.joining(StringPool.SPACE_COMMA_SPACE));
        return super.typedThis;
    }

    public String getSelectColumns() {
        return selectColumns;
    }
}
