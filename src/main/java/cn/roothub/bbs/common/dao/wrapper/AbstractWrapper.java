package cn.roothub.bbs.common.dao.wrapper;

import cn.roothub.bbs.common.dao.enums.SqlKeyword;
import cn.roothub.bbs.common.dao.wrapper.conditions.Compare;
import cn.roothub.bbs.common.dao.wrapper.conditions.Func;
import cn.roothub.bbs.common.dao.wrapper.conditions.Join;
import cn.roothub.bbs.common.dao.wrapper.segments.AbstractSqlSegmentList;
import cn.roothub.bbs.common.dao.wrapper.segments.SimpleSqlSegmentList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @param <T> 数据库表映射实体类
 * @param <R> 返回结果
 * @param <K> 数据库字段
 * @param <V> 字段对应的值
 *
 * @Author: miansen.wang
 * @Date: 2019/9/12 10:04
 */
public abstract class AbstractWrapper<T , R extends AbstractWrapper<T, R, K, V>, K, V> implements Compare<R, K, V>, Join<R>, Func<R, K, V>, ISqlSegment{

    /**
     * 数据库表映射实体类
     */
    protected T model;

    /**
     * 实体类型
     */
    protected Class<T> modelClass;

    /**
     * 占位符
     */
    protected final R typedThis = (R) this;

    /**
     * K: 实体类属性名
     * V: 实体类属性值
     */
    protected Map<String, Object> paramNameValuePairs = new HashMap<>(16);

    /**
     * 自增长值，作为 paramNameValuePairs 的 key
     */
    protected AtomicInteger paramNameSeq = new AtomicInteger(0);

    private AbstractSqlSegmentList segmentList = new SimpleSqlSegmentList();

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public Class<T> getModelClass() {
        return modelClass;
    }

    public void setModelClass(Class<T> modelClass) {
        this.modelClass = modelClass;
    }

    @Override
    public R eq(K column, V value) {
        return this.addCondition(column, SqlKeyword.EQ, value);
    }

    @Override
    public R eq(Map<K, V> params) {
        return null;
    }

    @Override
    public R ne(K column, V value) {
        return this.addCondition(column, SqlKeyword.NE, value);
    }

    @Override
    public R ne(Map<K, V> params) {
        return null;
    }

    @Override
    public R gt(K column, V value) {
        return null;
    }

    @Override
    public R gt(Map<K, V> params) {
        return null;
    }

    @Override
    public R ge(K column, V value) {
        return null;
    }

    @Override
    public R ge(Map<K, V> params) {
        return null;
    }

    @Override
    public R lt(K column, V value) {
        return null;
    }

    @Override
    public R lt(Map<K, V> params) {
        return null;
    }

    @Override
    public R le(K column, V value) {
        return null;
    }

    @Override
    public R le(Map<K, V> params) {
        return null;
    }

    @Override
    public R between(K column, V value1, V value2) {
        return null;
    }

    @Override
    public R notBetween(K column, V value1, V value2) {
        return null;
    }

    @Override
    public R isNull(K column) {
        return null;
    }

    @Override
    public R isNotNull(K column) {
        return null;
    }

    @Override
    public R in(K column, V... values) {
        return null;
    }

    @Override
    public R notIn(K column, V... values) {
        return null;
    }

    @Override
    public R like(K column, V value) {
        return null;
    }

    @Override
    public R notLike(K column, V value) {
        return null;
    }

    @Override
    public R likeLeft(K column, V value) {
        return null;
    }

    @Override
    public R likeRight(K column, V value) {
        return null;
    }

    @Override
    public R orderByAsc(K... columns) {
        return null;
    }

    @Override
    public R orderByDesc(K... columns) {
        return null;
    }

    @Override
    public R groupBy(K... columns) {
        return null;
    }

    @Override
    public R or() {
        return null;
    }

    @Override
    public R and() {
        return null;
    }

    @Override
    public R exists(String existsSql) {
        return null;
    }

    @Override
    public R notExists(String existsSql) {
        return null;
    }

    @Override
    public R having() {
        return null;
    }

    @Override
    public R having(String havingSql) {
        return null;
    }

    protected R addCondition(K column, SqlKeyword sqlKeyword, V value) {
        List<ISqlSegment> list = Arrays.asList(() -> {
            return this.columnToString(column);
        }, sqlKeyword, () -> {
            return this.formatSqlVal(value);
        });
        segmentList.addAll(list);
        return this.typedThis;
    }

    protected final String formatSqlVal(V value) {
        if (value instanceof Object) {
            String genParamName = "BASEDAOVALUE" + this.paramNameSeq.incrementAndGet();
            this.paramNameValuePairs.put(genParamName, value);
            return String.format("#{%s.paramNameValuePairs.%s}", "wrapper", genParamName);
        } else {
            throw new RuntimeException("not support this value !");
        }
    }

    protected String columnToString(K column) {
        if (column instanceof String) {
            return (String) column;
        } else {
            throw new RuntimeException("not support this column !");
        }
    }

    @Override
    public String getSqlSegment() {
        return segmentList.getSqlSegment();
    }
}
