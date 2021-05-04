package wang.miansen.roothub.common.dao.mapper.wrapper.update;

import java.io.Serializable;

/**
 * 更新包装器的接口，主要的作用是设置和获取更新 SQL 的 SET 片段。
 *
 * @param <R> 返回结果的类型
 * @param <K> 第一个输入参数的类型
 * @param <V> 第二个输入参数的类型
 * @author miansen.wang
 * @date 2019-10-17 23:08
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
     * <p>这个方法应该要根据上面的 set 方法的结果，返回一个完整的 set sql。</p>
     * <p>如果 setSql 为空，则返回 null</p>
     * <p>如果 setSql 不为空，则返回完整的 set sql。例如一个完整的 update sel 是这样的：
     * update table set (表达式) where...，这个方法应该返回 "表达式" 部位。
     * 如：id = #{user.id},name = #{user.name},age = #{user.age}...</p>
     *
     * @return String
     */
    String getSqlSet();
}
