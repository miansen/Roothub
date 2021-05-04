package wang.miansen.roothub.common.dao.mapper.metadata;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.Data;

import wang.miansen.roothub.common.dao.mapper.enums.IdType;
import wang.miansen.roothub.common.dao.mapper.util.SqlScriptUtils;
import wang.miansen.roothub.common.dao.mapper.util.StringPool;
import wang.miansen.roothub.common.dao.mapper.util.StringUtils;

/**
 * 这个类存储了数据库对应的实体类所有的信息， 包括表主键 ID 类型、表名称、表字段信息列表等等信息，这些信息通过反射获取。
 *
 * @author miansen.wang
 * @date 2019-8-26 23:15
 */
@Data
public class TableInfo implements StringPool {

    /**
     * 实体类对应的表名
     */
    private String tableName;

    /**
     * 实体类 class
     */
    private Class<?> entityClass;

    /**
     * 数据库表的主键字段名
     */
    private String primaryKeyColumnName;

    /**
     * 实体类主键属性名
     */
    private String primaryKeyPropertyName;

    /**
     * 主键 EL 表达式：StringPool.ENTITY_DOT.primaryKeyPropertyName
     */
    private String primaryKeyPropertyEl;

    /**
     * 主键属性 Class
     */
    private Class<?> primaryKeyClass;

    /**
     * 主键类型
     */
    private IdType idType = IdType.NONE;

    /**
     * 所有的字段信息
     */
    private List<TableFieldInfo> tableFieldInfos;

    /**
     * 构造函数
     *
     * @param entityClass 实体类 class
     */
    public TableInfo(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * 获取主键 Segment
     *
     * @return #{别名.primaryKeyPropertyName}
     */
    public String getPrimaryKeyPropertySegment() {
        return SqlScriptUtils.safeParam(primaryKeyPropertyEl);
    }

    /**
     * 获取查询 select sql 片段
     * <p>select (字段) from table where ...</p>
     * <p>位于 "字段" 部位</p>
     *
     * @param queryWrapper 是否使用 queryWrapper 查询
     * @return sql 脚本片段
     */
    public String getSelectColumnSegment(boolean queryWrapper) {
        String selectColumnSegment = tableFieldInfos.stream().filter(TableFieldInfo::isSelect)
            .map(TableFieldInfo::getColumnName).collect(Collectors.joining(COMMA));
        selectColumnSegment = primaryKeyColumnName + COMMA + selectColumnSegment;
        return queryWrapper ? SqlScriptUtils
            .convertChoose(String.format("%s != null and %s != null", WRAPPER, WRAPPER_DOT_SELECT),
                SqlScriptUtils.safeParam(WRAPPER_DOT_SELECT), selectColumnSegment) : selectColumnSegment;
    }

    /**
     * 获取 insert 字段 sql 脚本片段
     * <p>insert into table (字段) values (值);</p>
     * <p>位于 "字段" 部位</p>
     *
     * @return insert sql 脚本片段
     */
    public String getInsertColumnSegment() {
        String insertColumnSegment = tableFieldInfos.stream().map(TableFieldInfo::getInsertColumnSegment)
            .collect(Collectors.joining(NEWLINE));
        // 处理主键字段
        if (StringUtils.notBlank(primaryKeyColumnName)) {
            if (idType != IdType.AUTO) {
                // 如果主键不是自增长类型的，那么 insert 时要加上主键字段。
                insertColumnSegment = primaryKeyColumnName + COMMA + insertColumnSegment;
            }
        }
        return SqlScriptUtils.convertTrim(insertColumnSegment, LEFT_BRACKET, RIGHT_BRACKET, COMMA, COMMA);
    }

    /**
     * 获取 insert 字段 sql 脚本片段，转换成 if 标签，过滤为空的字段。
     * <p>insert into table (字段) values (值);</p>
     * <p>位于 "字段" 部位</p>
     *
     * @return insert sql 脚本片段
     */
    public String getInsertNotNullColumnSegment() {
        String insertNotNullColumnSegment = tableFieldInfos.stream().map(TableFieldInfo::getInsertNotNullColumnSegment)
            .collect(Collectors.joining(NEWLINE));
        // 处理主键字段
        if (StringUtils.notBlank(primaryKeyColumnName)) {
            // 如果主键不是自增长类型的，那么 insert 时要加上主键字段。
            if (idType != IdType.AUTO) {
                // 主键是基本类型
                if (primaryKeyClass.isPrimitive()) {
                    insertNotNullColumnSegment = primaryKeyColumnName + insertNotNullColumnSegment + NEWLINE;
                    // 主键是 CharSequence 类型
                } else if (StringUtils.isCharSequence(this.primaryKeyClass)) {
                    insertNotNullColumnSegment =
                        SqlScriptUtils
                            .convertIf(String.format("%s != null and %s != ''", primaryKeyPropertyEl, primaryKeyPropertyEl),
                                primaryKeyColumnName)
                            + COMMA + NEWLINE + insertNotNullColumnSegment + NEWLINE;
                } else {
                    insertNotNullColumnSegment =
                        SqlScriptUtils.convertIf(String.format("%s != null", primaryKeyPropertyEl), primaryKeyColumnName)
                            + COMMA
                            + NEWLINE + insertNotNullColumnSegment + NEWLINE;
                }
            }
        }
        return SqlScriptUtils.convertTrim(insertNotNullColumnSegment, LEFT_BRACKET, RIGHT_BRACKET, COMMA, COMMA);
    }

    /**
     * 获取 insert 插入值 sql 脚本片段
     * <p>insert into table (字段) values (值);</p>
     * <p>位于 "值" 部位</p>
     *
     * @return insert sql 脚本片段
     */
    public String getInsertValueSegment() {
        String insertValueSegment = tableFieldInfos.stream().map(TableFieldInfo::getInsertValueSegment)
            .collect(Collectors.joining(NEWLINE));
        // 处理主键字段
        if (StringUtils.notBlank(primaryKeyColumnName)) {
            if (idType != IdType.AUTO) {
                // 如果主键不是自增长类型的，那么 insert 时要加上主键字段。
                insertValueSegment = SqlScriptUtils.safeParam(ENTITY_DOT + primaryKeyPropertyName) + insertValueSegment;
            }
        }
        return SqlScriptUtils.convertTrim(insertValueSegment, LEFT_BRACKET, RIGHT_BRACKET, COMMA, COMMA);
    }

    /**
     * 获取 insert 插入值 sql 脚本片段，转换成 if 标签，过滤为空的字段。
     * <p>insert into table (字段) values (值);</p>
     * <p>位于 "值" 部位</p>
     *
     * @return insert sql 脚本片段
     */
    public String getInsertNotNullValueSegment() {
        String insertNotNullValueSegment = tableFieldInfos.stream().map(TableFieldInfo::getInsertNotNullValueSegment)
            .collect(Collectors.joining(NEWLINE));
        // 处理主键字段
        if (StringUtils.notBlank(primaryKeyColumnName)) {
            // 如果主键不是自增长类型的，那么 insert 时要加上主键字段。
            if (idType != IdType.AUTO) {
                // 主键是基本类型
                if (primaryKeyClass.isPrimitive()) {
                    insertNotNullValueSegment = primaryKeyColumnName + insertNotNullValueSegment + NEWLINE;
                    // 主键是 CharSequence 类型
                } else if (StringUtils.isCharSequence(this.primaryKeyClass)) {
                    insertNotNullValueSegment = SqlScriptUtils
                        .convertIf(String.format("%s != null and %s != ''", primaryKeyPropertyEl, primaryKeyPropertyEl),
                            SqlScriptUtils.safeParam(primaryKeyPropertyEl) + COMMA)
                        + NEWLINE + insertNotNullValueSegment + NEWLINE;
                } else {
                    insertNotNullValueSegment =
                        SqlScriptUtils.convertIf(String.format("%s != null", primaryKeyPropertyEl),
                            SqlScriptUtils.safeParam(primaryKeyPropertyEl) + COMMA)
                            + NEWLINE + insertNotNullValueSegment + NEWLINE;
                }
            }
        }
        return SqlScriptUtils.convertTrim(insertNotNullValueSegment, LEFT_BRACKET, RIGHT_BRACKET, COMMA, COMMA);
    }

    /**
     * 获取更新的 set 表达式
     * <p>update table set (表达式) where...</p>
     * <p>位于 "表达式" 部位</p>
     *
     * @return set 表达式
     */
    public String getSetSegment() {
        String allSetSegment = tableFieldInfos.stream().filter(Objects::nonNull)
            .map(TableFieldInfo::getSetSegment).collect(Collectors.joining(NEWLINE));
        return SqlScriptUtils.convertSet(allSetSegment);
    }
}
