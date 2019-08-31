package cn.roothub.bbs.common.dao.metadata;

/**
 * TableInfo 存储了数据库对应的 model 所有的信息，
 * 包括表主键 ID 类型、表名称、表字段信息列表等等信息，这些信息通过反射获取。
 * @Author: miansen.wang
 * @Date: 2019/8/26 23:15
 */
public class TableInfo {

    private String tableName;

    private Class<?> entityClass;

    private String primaryKeyColumn;

    private String[] columns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
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
}
