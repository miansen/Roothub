package wang.miansen.roothub.common.dao.mapper.builder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.dao.mapper.metadata.TableFieldInfo;
import wang.miansen.roothub.common.dao.mapper.metadata.TableInfo;
import wang.miansen.roothub.common.dao.mapper.util.CollectionUtils;
import wang.miansen.roothub.common.dao.mapper.util.ReflectionUtils;
import wang.miansen.roothub.common.dao.mapper.util.StringPool;
import wang.miansen.roothub.common.dao.mapper.util.StringUtils;

/**
 * TableInfo 构建器
 *
 * @author miansen.wang
 * @date 2019-8-31 11:56
 */
public class TableInfoBuilder {

    private static final Logger log = LoggerFactory.getLogger(TableInfoBuilder.class);

    /**
     * TableInfo 缓存池
     */
    private static final Map<Class<?>, TableInfo> TABLE_INFO_CACHE = new ConcurrentHashMap<>();

    /**
     * Field 缓存池
     */
    private static final Map<Class<?>, List<Field>> FIELD_CACHE = new ConcurrentHashMap<>();

    /**
     * 获取 TableInfo
     *
     * @param entityClass entityClass
     * @return TableInfo
     */
    public static TableInfo getTableInfo(Class<?> entityClass) {
        if (entityClass == null) {
            return null;
        } else {
            return TABLE_INFO_CACHE.get(entityClass);
        }
    }

    /**
     * 初始化 TableInfo
     *
     * @param builderAssistant builderAssistant
     * @param entityClass 实体类 Class
     * @return TableInfo
     */
    public static synchronized TableInfo initTableInfo(MapperBuilderAssistant builderAssistant, Class<?> entityClass) {
        TableInfo tableInfo = TABLE_INFO_CACHE.get(entityClass);
        if (tableInfo == null) {
            tableInfo = new TableInfo(entityClass);
            // 初始化表名
            initTableName(entityClass, tableInfo);
            // 初始化字段
            initTableFieldInfos(entityClass, tableInfo);
            TABLE_INFO_CACHE.put(entityClass, tableInfo);
        }
        return tableInfo;
    }

    /**
     * 初始化 TableName
     *
     * @param entityClass 实体类 Class
     * @param tableInfo tableInfo
     */
    public static void initTableName(Class<?> entityClass, TableInfo tableInfo) {
        String tableName = entityClass.getSimpleName();
        // 获取类上的 TableName 注解
        Table tableNameAnnotation = entityClass.getAnnotation(Table.class);
        if (tableNameAnnotation != null && !"".equals(tableNameAnnotation.value())) {
            tableName = tableNameAnnotation.value();
        } else {
            tableName = StringUtils.camelToUnderline(tableName);
        }
        tableInfo.setTableName(tableName);
    }

    /**
     * 初始化主键
     *
     * @param tableInfo tableInfo
     * @param field field
     */
    public static void initTableId(TableInfo tableInfo, Field field) {
        String primaryKeyColumnName;
        Id fieldAnnotation = field.getAnnotation(Id.class);
        if (fieldAnnotation != null) {
            if (!"".equals(fieldAnnotation.value())) {
                primaryKeyColumnName = fieldAnnotation.value();
            } else {
                primaryKeyColumnName = StringUtils.camelToUnderline(field.getName());
            }
            log.info("Find table primary key: \"{}\" in Class: \"{}\".", primaryKeyColumnName,
                field.getDeclaringClass().getName());
            tableInfo.setPrimaryKeyColumnName(primaryKeyColumnName);
            tableInfo.setPrimaryKeyPropertyName(field.getName());
            tableInfo.setPrimaryKeyClass(field.getDeclaringClass());
            tableInfo.setPrimaryKeyPropertyEl(StringPool.ENTITY_DOT + field.getName());
            tableInfo.setIdType(fieldAnnotation.type());
        }
    }

    /**
     * 根据给定的实体类 Class 对象，通过反射获取实体类的信息，最后初始化 TableFieldInfo 和 主键。
     *
     * @param entityClass 实体类的 Class 对象
     * @param tableInfo 存储数据库表对应的实体类所有的信息
     */
    public static void initTableFieldInfos(Class<?> entityClass, TableInfo tableInfo) {
        List<Field> fields = FIELD_CACHE.get(entityClass);
        if (CollectionUtils.isEmpty(fields)) {
            fields = ReflectionUtils.getFieldList(entityClass);
            FIELD_CACHE.put(entityClass, fields);
        }
        List<TableFieldInfo> tableFieldInfos = new ArrayList<>(fields.size());
        // 标记是否读取到主键
        boolean isReadPK = false;
        for (Field field : fields) {
            Id fieldAnnotation = field.getAnnotation(Id.class);
            if (fieldAnnotation != null) {
                // 初始化主键
                initTableId(tableInfo, field);
                isReadPK = true;
                continue;
            }
            tableFieldInfos.add(new TableFieldInfo(field));
        }
        // 初始化 TableFieldInfo
        tableInfo.setTableFieldInfos(tableFieldInfos);
        if (!isReadPK) {
            log.warn("Can not find table primary key in Class: \"{}\".", entityClass.getName());
        }
    }
}
