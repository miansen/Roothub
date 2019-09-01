package cn.roothub.bbs.common.dao.metadata;

import java.util.List;

/**
 * TableInfo 存储了数据库对应的 model 所有的信息，
 * 包括表主键 ID 类型、表名称、表字段信息列表等等信息，这些信息通过反射获取。
 * @Author: miansen.wang
 * @Date: 2019/8/26 23:15
 */
public class TableInfo {

    /**
     * model 对应的表名
     */
    private String tableName;

    /**
     * model class
     */
    private Class<?> modelClass;

    /**
     * 主键字段
     */
    private String primaryKeyColumn;

    /**
     * 所有字段
     */
    private String[] columns;

    private List<TableFieldInfo> tableFieldInfoList;

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

    public String getPrimaryKeyColumn() {
        return primaryKeyColumn;
    }

    public void setPrimaryKeyColumn(String primaryKeyColumn) {
        this.primaryKeyColumn = primaryKeyColumn;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public List<TableFieldInfo> getTableFieldInfoList() {
        return tableFieldInfoList;
    }

    public void setTableFieldInfoList(List<TableFieldInfo> tableFieldInfoList) {
        this.tableFieldInfoList = tableFieldInfoList;
    }
}
