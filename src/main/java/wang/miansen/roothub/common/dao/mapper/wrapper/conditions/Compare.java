package wang.miansen.roothub.common.dao.mapper.wrapper.conditions;

import java.io.Serializable;
import java.util.Map;

/**
 * 比较条件接口
 *
 * <p>定义了以下方法：
 * <p>等于（=）{@link #eq(Object, Object)} {@link #eq(Map)}
 * <p>不等于（<>）{@link #ne(Object, Object)} {@link #ne(Map)}
 * <p>大于（>）{@link #gt(Object, Object)} {@link #gt(Map)}
 * <p>大于等于（>=）{@link #ge(Object, Object)} {@link #ge(Map)}
 * <p>小于（<）{@link #lt(Object, Object)} {@link #lt(Map)}
 * <p>小于等于（<=）{@link #le(Object, Object)} {@link #le(Map)}
 * <p>BETWEEN {@link #between(Object, Object, Object)}
 * <p>NOT BETWEEN {@link #notBetween(Object, Object, Object)}
 *
 * @param <R> 返回结果
 * @param <K> 数据库字段
 * @param <V> 字段对应的值
 *
 * @Author: miansen.wang
 * @Date: 2019/9/12 10:19
 */
public interface Compare<R, K, V> extends Serializable{

    /**
     * 等于（=）
     *
     * <p>例：eq("userName", "张三") -> user_name = '张三'
     * <p>例：eq("userName", null) -> user_name is null
     *
     * @param column 数据库表的字段名
     * @param value 字段对应的值
     * @return
     */
    R eq(K column, V value);

    /**
     * 等于（=）
     *
     * <p>例：eq({id:1,userName:"张三",age:15}) -> id = 1 and user_name = '张三' and age = 15
     * <p>例：eq({id:2,userName:"李四",age:null}) -> id = 2 and user_name = '李四' and age is null
     *
     * @param params
     * @return
     */
    R eq(Map<K, V> params);

    /**
     * 不等于（<>）
     *
     * <p>例：ne("userName", "张三") -> user_name <> '张三'
     * <p>例：ne("userName", null) -> user_name is not null
     *
     * @param column 数据库表的字段名
     * @param value 字段对应的值
     * @return
     */
    R ne(K column, V value);

    /**
     * 不等于（<>）
     *
     * <p>例：ne({id:1,userName:"张三",age:15}) -> id <> 1 and user_name <> '张三' and age <> 15
     * <p>例：ne({id:2,userName:"李四",age:null}) -> id <> 2 and user_name <> '李四' and age is not null
     *
     * @param params
     * @return
     */
    R ne(Map<K, V> params);

    /**
     * 大于（>）
     *
     * <p>例：gt("age", 18) -> age > 18
     * <p>例：gt("age", null) -> 无效
     *
     * @param column
     * @param value
     * @return
     */
    R gt (K column, V value);

    /**
     * 大于（>）
     *
     * <p>例：gt({id:1,userName:"张三",age:15}) -> id > 1 and user_name > '张三' and age > 15
     * <p>例：gt({id:2,userName:"李四",age:null}) -> id > 2 and user_name > '李四'
     *
     * @param params
     * @return
     */
    R gt(Map<K, V> params);

    /**
     * 大于等于（>=）
     *
     * <p>例：ge("age", 18) -> age >= 18
     * <p>例：ge("age", null) -> 无效
     *
     * @param column
     * @param value
     * @return
     */
    R ge(K column, V value);

    /**
     * 大于等于（>=）
     *
     * <p>例：ge({id:1,userName:"张三",age:15}) -> id >= 1 and user_name >= '张三' and age >= 15
     * <p>例：ge({id:2,userName:"李四",age:null}) -> id >= 2 and user_name >= '李四'
     *
     * @param params
     * @return
     */
    R ge(Map<K, V> params);

    /**
     * 小于（<）
     *
     * <p>例：lt("age", 18) -> age < 18
     * <p>例：lt("age", null) -> 无效
     *
     * @param column
     * @param value
     * @return
     */
    R lt(K column, V value);

    /**
     * 小于（<）
     *
     * <p>例：lt({id:1,userName:"张三",age:15}) -> id < 1 and user_name < '张三' and age < 15
     * <p>例：lt({id:2,userName:"李四",age:null}) -> id < 2 and user_name < '李四'
     * @param params
     * @return
     */
    R lt(Map<K, V> params);

    /**
     * 小于等于（<=）
     *
     * <p>例：le("age", 18) -> age <= 18
     * <p>例：le("age", null) -> 无效
     *
     * @param column
     * @param value
     * @return
     */
    R le(K column, V value);

    /**
     * 小于等于（<=）
     *
     * <p>例：le({id:1,userName:"张三",age:15}) -> id <= 1 and user_name <= '张三' and age <= 15
     * <p>例：le({id:2,userName:"李四",age:null}) -> id <= 2 and user_name <= '李四'
     *
     * @param params
     * @return
     */
    R le(Map<K, V> params);

    /**
     * BETWEEN
     *
     * <p>例: between("age", 18, 30) -> age between 18 and 30
     * <p>例: between("age", 18, null) -> 无效
     * <p>例: between("age", null, 30) -> 无效
     * <p>例: between("age", null, null) -> 无效
     *
     * @param column
     * @param value1
     * @param value2
     * @return R
     */
    R between(K column, V value1, V value2);

    /**
     * NOT BETWEEN
     *
     * <p>例: notBetween("age", 18, 30) -> age not between 18 and 30
     * <p>例: notBetween("age", 18, null) -> 无效
     * <p>例: notBetween("age", null, 30) -> 无效
     * <p>例: notBetween("age", null, null) -> 无效
     *
     * @param column
     * @param value1
     * @param value2
     * @return R
     */
    R notBetween(K column, V value1, V value2);
}
