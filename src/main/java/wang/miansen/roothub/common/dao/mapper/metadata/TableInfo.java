package wang.miansen.roothub.common.dao.mapper.metadata;

import wang.miansen.roothub.common.dao.mapper.enums.IdType;
import wang.miansen.roothub.common.dao.mapper.util.SqlScriptUtils;
import wang.miansen.roothub.common.dao.mapper.util.StringPool;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * TableInfo 存储了数据库对应的 model 所有的信息，
 * 包括表主键 ID 类型、表名称、表字段信息列表等等信息，这些信息通过反射获取。
 * @Author: miansen.wang
 * @Date: 2019/8/26 23:15
 */
public class TableInfo implements StringPool {

    /**
     * 实体类对应的表名
     */
    private String tableName;

    /**
     * 实体类 class
     */
    private Class<?> modelClass;

    /**
     * 数据库表的主键字段
     */
    private String keyColumn;

    /**
     * 实体类主键属性
     */
    private String keyProperty;

    /**
     * 主键类型
     */
    private IdType idType = IdType.NONE;

    /**
     * 所有的字段信息
     */
    private List<TableFieldInfo> tableFieldInfoList;

    public TableInfo(Class<?> modelClass) {
        this.modelClass = modelClass;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Class<?> getModelClass() {
        return modelClass;
    }

    public void setModelClass(Class<?> modelClass) {
        this.modelClass = modelClass;
    }

    public String getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(String keyColumn) {
        this.keyColumn = keyColumn;
    }

    public String getKeyProperty() {
        return keyProperty;
    }

    public void setKeyProperty(String keyProperty) {
        this.keyProperty = keyProperty;
    }

    public IdType getIdType() {
        return idType;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    public List<TableFieldInfo> getTableFieldInfoList() {
        return tableFieldInfoList;
    }

    public void setTableFieldInfoList(List<TableFieldInfo> tableFieldInfoList) {
        this.tableFieldInfoList = tableFieldInfoList;
    }

    /**
     * 获取主键 Segment
     * @return #{别名.keyProperty}
     */
    public String getKeyPropertySegment() {
        return SqlScriptUtils.safeParam(ENTITY_DOT + keyProperty);
    }

    /**
     * 获取查询字段
     * <p>select (字段) from table where ...</p>
     * <p>位于 "字段" 部位</p>
     * @param queryWrapper 是否使用 queryWrapper 查询
     * @return sql 脚本片段
     */
    public String getSelectColumnSegments(boolean queryWrapper) {
        String selectColumns = tableFieldInfoList.stream().filter(TableFieldInfo::isSelect)
                .map(TableFieldInfo::getColumn).collect(Collectors.joining(COMMA));
        return queryWrapper ? SqlScriptUtils.convertChoose("wrapper != null and wrapper.selectColumns != null",
                "${wrapper.selectColumns}", selectColumns) : selectColumns;
    }

    /**
     * 获取插入的字段
     * <p>insert into table (字段) values (值);</p>
     * <p>位于 "字段" 部位</p>
     * @return sql 脚本片段
     */
    public String getInsertColumnSegments() {
        String insertColumns = tableFieldInfoList.stream().filter(Objects::nonNull)
                .map(TableFieldInfo::getColumn).collect(Collectors.joining(COMMA));
        return SqlScriptUtils.convertTrim(insertColumns, LEFT_BRACKET, RIGHT_BRACKET, COMMA, COMMA);
    }

    /**
     * 获取插入的值
     * <p>insert into table (字段) values (值);</p>
     * <p>位于 "值" 部位</p>
     * @return sql 脚本片段
     */
    public String getInsertValueSegments() {
        String insertValues = tableFieldInfoList.stream().filter(Objects::nonNull)
                .map(TableFieldInfo::getInsertValueSegment).collect(Collectors.joining(COMMA));
        return SqlScriptUtils.convertTrim(insertValues, LEFT_BRACKET, RIGHT_BRACKET, COMMA, COMMA);
    }

    /**
     * 获取更新的 set 表达式
     * <p>update table set (表达式) where...</p>
     * <p>位于 "表达式" 部位</p>
     * @return
     */
    public String getSetSegments() {
        String allSetSegment = tableFieldInfoList.stream().filter(Objects::nonNull)
                .map(TableFieldInfo::getSetSegment).collect(Collectors.joining(COMMA));
        return SqlScriptUtils.convertSet(allSetSegment);
    }
}
