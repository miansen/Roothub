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

    /**
     * SQL 片段容器
     */
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
        return addCondition(column, SqlKeyword.EQ, value);
    }

    @Override
    public R eq(Map<K, V> params) {
         params.forEach((k, v) -> addCondition(k, SqlKeyword.EQ, v));
         return typedThis;
    }

    @Override
    public R ne(K column, V value) {
        return addCondition(column, SqlKeyword.NE, value);
    }

    @Override
    public R ne(Map<K, V> params) {
        params.forEach((k, v) -> addCondition(k, SqlKeyword.NE, v));
        return typedThis;
    }

    @Override
    public R gt(K column, V value) {
        return addCondition(column, SqlKeyword.GT, value);
    }

    @Override
    public R gt(Map<K, V> params) {
        params.forEach((k, v) -> addCondition(k, SqlKeyword.GT, v));
        return typedThis;
    }

    @Override
    public R ge(K column, V value) {
        return addCondition(column, SqlKeyword.GE, value);
    }

    @Override
    public R ge(Map<K, V> params) {
        params.forEach(((k, v) -> addCondition(k, SqlKeyword.GE, v)));
        return typedThis;
    }

    @Override
    public R lt(K column, V value) {
        return addCondition(column, SqlKeyword.LT, value);
    }

    @Override
    public R lt(Map<K, V> params) {
        params.forEach((k, v) -> addCondition(k, SqlKeyword.LT, v));
        return typedThis;
    }

    @Override
    public R le(K column, V value) {
        return addCondition(column, SqlKeyword.LE, value);
    }

    @Override
    public R le(Map<K, V> params) {
        params.forEach((k, v) -> addCondition(k, SqlKeyword.LE, v));
        return typedThis;
    }

    @Override
    public R between(K column, V value1, V value2) {
        return addCondition(() -> columnToString(column),
                SqlKeyword.BETWEEN,
                () -> formatSqlValue(value1),
                SqlKeyword.AND,
                () -> formatSqlValue(value2));
    }

    @Override
    public R notBetween(K column, V value1, V value2) {
        return addCondition(() -> columnToString(column),
                SqlKeyword.NOT,
                SqlKeyword.BETWEEN,
                () -> formatSqlValue(value1),
                SqlKeyword.AND,
                () -> formatSqlValue(value2));
    }

    @Override
    public R isNull(K column) {
        return addCondition(() -> columnToString(column), SqlKeyword.IS_NULL);
    }

    @Override
    public R isNotNull(K column) {
        return addCondition(() -> columnToString(column), SqlKeyword.IS_NOT_NULL);
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

    /**
     * 添加 SQL 片段到容器中
     * @param column 数据库表字段名称
     * @param sqlKeyword 连接符
     * @param value 字段对应的值
     * @return this
     */
    protected R addCondition(K column, SqlKeyword sqlKeyword, V value) {
        return addCondition(() -> columnToString(column), sqlKeyword, () -> formatSqlValue(value));
    }

    /**
     * 添加 SQL 片段到容器中
     * @param sqlSegments SQL 片段
     * @return this
     */
    protected R addCondition(ISqlSegment...sqlSegments) {
        segmentList.addAll(Arrays.asList(sqlSegments));
        return typedThis;
    }

    /**
     * 缓存 value，并且格式化成 mybatis 的参数格式：#{%s}
     * @param value 数据库表字段对应的值
     * @return String
     */
    protected final String formatSqlValue(V value) {
        if (value instanceof Object) {
            String genParamName = "BASEDAOVALUE" + this.paramNameSeq.incrementAndGet();
            this.paramNameValuePairs.put(genParamName, value);
            return String.format("#{%s.paramNameValuePairs.%s}", "wrapper", genParamName);
        } else {
            throw new RuntimeException("not support this value !");
        }
    }

    /**
     * 将 column 转化成 String
     * @param column 数据库表字段名称
     * @return String
     */
    protected String columnToString(K column) {
        if (column instanceof String) {
            return (String) column;
        } else {
            throw new RuntimeException("not support this column !");
        }
    }

    /**
     * 获取拼接好的 SQL 语句
     * @return
     */
    @Override
    public String getSqlSegment() {
        return segmentList.getSqlSegment();
    }
}
