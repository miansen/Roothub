package wang.miansen.roothub.common.dao.mapper.wrapper.query;

import wang.miansen.roothub.common.dao.mapper.metadata.TableFieldInfo;
import java.io.Serializable;
import java.util.function.Predicate;

/**
 * 查询包装器的接口，主要的作用是自定义查询字段（默认是查询所有字段）
 *
 * <p>定义了设置查询字段的方法{@link #select(Object[])}
 * 和过滤查询字段的方法{@link #select(Predicate)} 及其重载方法{@link #select(Class, Predicate)}
 *
 * @param <T> 实体类型
 * @param <R> 返回结果类型
 * @param <K> 输入参数类型
 *
 * @Author: miansen.wang
 * @Date: 2019/9/10 22:43
 */
public interface Query<T, R, K> extends Serializable{

    /**
     * 设置查询字段
     * @param columns 字段数组
     * @return 包装器
     */
    R select(K... columns);

    /**
     * 过滤查询字段
     *
     * <p>注意：当查询包装器里面的 modelClass 字段初始化后才能用此方法，否则只能用下面的重载方法
     * @param predicate 过滤条件
     * @return 包装器
     */
    R select(Predicate<TableFieldInfo> predicate);

    /**
     * 过滤查询字段
     *
     * <p>当查询包装器里面的 modelClass 字段没有初始化时，可以使用此方法
     * @param modelClass 实体类型
     * @param predicate 过滤条件
     * @return 包装器
     */
    R select(Class<T> modelClass, Predicate<TableFieldInfo> predicate);
}
