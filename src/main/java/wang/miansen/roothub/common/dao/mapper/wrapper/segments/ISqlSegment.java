package wang.miansen.roothub.common.dao.mapper.wrapper.segments;

import java.io.Serializable;

/**
 * SQL 片段描述
 * <p>以空格分割 SQL 语句，得到的结果集就是 SQL 片段集合
 * <p>例：SELECT name FORM user WHERE id = 1;
 * <p>片段1：SELECT
 * <p>片段2：name
 * <p>片段3：FORM
 * <p>片段4：user
 * <p>片段5：WHERE
 * <p>片段6：id
 * <p>片段7：=
 * <p>片段8：1
 *
 * @Author: miansen.wang
 * @Date: 2019/9/13 18:44
 */
@FunctionalInterface
public interface ISqlSegment extends Serializable{

    String getSqlSegment();

}
