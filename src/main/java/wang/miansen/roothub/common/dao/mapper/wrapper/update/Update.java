package wang.miansen.roothub.common.dao.mapper.wrapper.update;

import java.io.Serializable;

/**
 * 更新包装器的接口，主要的作用是设置和获取更新 SQL 的 SET 片段
 *
 * @param <R> 返回结果类型
 * @param <K> 输入参数类型
 * @param <V> 输入参数类型
 *
 * @Author: miansen.wang
 * @Date: 2019/10/17 23:08
 */
public interface Update<R, K, V> extends Serializable {

    /**
     * 设置更新 SQL 的 SET 片段
     * <p>如：set("id", 1) -> UPDATE table SET id = '1';
     *
     * @param column 数据库表的字段名
     * @param value 要更新的值
     * @return R
     */
    R set(K column, V value);

    /**
     * 获取更新 SQL 的 SET 片段
     * @return R
     */
    String getSqlSet();
}
