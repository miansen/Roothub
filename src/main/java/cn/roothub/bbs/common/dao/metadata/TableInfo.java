package cn.roothub.bbs.common.dao.metadata;

import cn.roothub.bbs.common.dao.enums.IdType;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static cn.roothub.bbs.common.dao.util.StringPool.NEWLINE;
import static cn.roothub.bbs.common.dao.util.StringPool.QUOTE;
import static cn.roothub.bbs.common.dao.util.StringPool.RIGHT_CHEV;

/**
 * TableInfo 存储了数据库对应的 model 所有的信息，
 * 包括表主键 ID 类型、表名称、表字段信息列表等等信息，这些信息通过反射获取。
 * @Author: miansen.wang
 * @Date: 2019/8/26 23:15
 */
public class TableInfo {

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
     * 获取查询的字段
     * <p>select (字段) from table where ...
     * @return sql 脚本片段
     */
    public String getSelectColumns() {
        String selectColumns = tableFieldInfoList.stream().filter(TableFieldInfo::isSelect)
                .map(TableFieldInfo::getColumn).collect(Collectors.joining(","));
        return "<choose>" + "\n"
                + "<when test=\"" + "wrapper != null and wrapper.selectColumns != null" + QUOTE + RIGHT_CHEV + NEWLINE
                + "${wrapper.selectColumns}" + NEWLINE + "</when>" + NEWLINE
                + "<otherwise>" + selectColumns + "</otherwise>" + NEWLINE
                + "</choose>";
    }

    /**
     * 获取插入的字段
     * <p>insert into table (字段) values (值);
     * @return sql 脚本片段
     */
    public String getInsertColumns() {
        String insertColumns = tableFieldInfoList.stream().filter(Objects::nonNull)
                .map(TableFieldInfo::getColumn).collect(Collectors.joining(","));
        return insertColumns;
    }

    /**
     * 获取插入的值
     * <p>insert into table (字段) values (值);
     * @return sql 脚本片段
     */
    public String getInsertValues() {
        String insertValues = tableFieldInfoList.stream().filter(Objects::nonNull)
                .map(TableFieldInfo::getInsertProperty).collect(Collectors.joining("\n"));
        return insertValues;
    }

}
