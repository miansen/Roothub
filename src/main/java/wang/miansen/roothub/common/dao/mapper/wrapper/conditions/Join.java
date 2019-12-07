package wang.miansen.roothub.common.dao.mapper.wrapper.conditions;

/**
 * 拼接条件接口
 *
 * @param <R> 返回结果
 *
 * @Author: miansen.wang
 * @Date: 2019/9/12 16:24
 */
public interface Join<R> {

    /**
     * 主动调用 {@code or} 表示紧接着下一个方法不是用 {@code and} 连接！（不调用 or 则默认使用 and 连接）
     * <p>例：eq("id",1).or().eq("userName","张三") -> id = 1 or user_name = '张三'
     * @return
     */
    R or();

    /**
     * 主动调用 {@code and} 表示紧接着下一个方法用 {@code and} 连接！
     * <p>虽然默认使用的是 and 连接，但是主动调用 {@code and} 的话，代码可读性更强
     * <p>例：eq("id",1).and().eq("userName","张三") -> id = 1 and user_name = '张三'
     * @return
     */
    R and();

    /**
     * 拼接 EXISTS ( sql语句 )
     * <p>例: exists("select 1 from table where user_name = '张三'") -> exists (select 1 from table where user_name = '张三')
     * <p>注意：existsSql 里面的字段为数据库的字段名（不是实体类的字段名！）
     * <p>注意：此方法有 SQL 注入风险
     * @param existsSql
     * @return
     */
    R exists(String existsSql);

    /**
     * 拼接 NOT EXISTS ( sql语句 )
     * <p>例: notExists("select 1 from table where user_name = '张三'") -> not exists (select 1 from table where user_name = '张三')
     * <p>注意：existsSql 里面的字段为数据库的字段名（不是实体类的字段名！）
     * <p>注意：此方法有 SQL 注入风险
     * @param existsSql
     * @return
     */
    R notExists(String existsSql);

    /**
     * 拼接 HAVING
     * <p>例：having().eq("userName","张三").gt("age", 18) -> having user_name = '张三' and age > 18
     * @return
     */
    R having();

    /**
     * 拼接 HAVING ( sql语句 )
     * <p>例：having("count(1) > 1 and SUM(age) > 18").eq("userName","张三").gt("age", 18) -> having count(1) > 1 and SUM(age) > 18 and user_name = '张三' and age > 18
     * <p>注意：havingSql 里面的字段为数据库的字段名（不是实体类的字段名！）
     * <p>注意：此方法有 SQL 注入风险
     * @param havingSql
     * @return
     */
    R having(String havingSql);
}
