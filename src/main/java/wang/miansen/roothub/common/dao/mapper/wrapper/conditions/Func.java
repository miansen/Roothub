package wang.miansen.roothub.common.dao.mapper.wrapper.conditions;

/**
 * @param <R> 返回结果
 * @param <K> 数据库字段
 * @param <V> 字段对应的值
 *
 * @Author: miansen.wang
 * @Date: 2019/9/12 16:44
 */
public interface Func<R, K, V> {

    /**
     * 字段 IS NULL
     * <p>例: isNull("userName") -> userName is null
     * @param column
     * @return
     */
    R isNull(K column);

    /**
     * 字段 IS NOT NULL
     * <p>例: isNotNull("userName") -> userName is not null
     * @param column
     * @return
     */
    R isNotNull(K column);

    /**
     * 字段 IN (values.get(0), values.get(1), ...)
     * <p>例: in("age",{1,2,3}) -> age in (1,2,3)
     * @param column
     * @param values
     * @return
     */
    R in (K column, V... values);

    /**
     * 字段 NOT IN (values.get(0), values.get(1), ...)
     * <p>例: notIn("age",{1,2,3}) -> age not in (1,2,3)
     * @param column
     * @param values
     * @return
     */
    R notIn (K column, V... values);

    /**
     * LIKE '%值%'
     * <p>例: like("userName", "王") -> user_name like '%王%'
     * @param column
     * @param value
     * @return
     */
    R like(K column, V value);

    /**
     * NOT LIKE '%值%'
     * <p>例: notLike("userName", "王") -> user_name not like '%王%'
     * @param column
     * @param value
     * @return
     */
    R notLike(K column, V value);

    /**
     * LIKE '%值'
     * <p>例: likeLeft("userName", "王") -> user_name like '%王'
     * @param column
     * @param value
     * @return
     */
    R likeLeft(K column, V value);

    /**
     * LIKE '值%'
     * <p>例: likeRight("userName", "王") -> user_name like '王%'
     * @param column
     * @param value
     * @return
     */
    R likeRight(K column, V value);

    /**
     * 排序：ORDER BY 字段, ... ASC
     * <p>例: orderByAsc("id", "userName", "age") -> order by id ASC,user_name ASC,age ASC
     * @param columns 数据库表字段
     * @return R
     */
    default R orderByAsc(K... columns) {
        return orderBy(true, columns);
    }

    /**
     * 排序：ORDER BY 字段, ... DESC
     * <p>例: orderByDesc("id", "userName", "age") -> order by id DESC,user_name DESC,age DESC
     * @param columns 数据库表字段
     * @return R
     */
    default R orderByDesc(K... columns) {
        return orderBy(false, columns);
    }

    /**
     * 排序：ORDER BY 字段
     * @param isAsc 是否升序
     * @param columns 数据库表字段
     * @return R
     */
    R orderBy(boolean isAsc, K... columns);

    /**
     * 分组：GROUP BY 字段
     * <p>例: groupBy("id", "userName", "age") -> group by id,user_name,age
     * @param columns
     * @return
     */
    R groupBy(K... columns);
}
