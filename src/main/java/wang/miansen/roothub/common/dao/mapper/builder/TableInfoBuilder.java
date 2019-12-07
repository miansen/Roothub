package wang.miansen.roothub.common.dao.mapper.builder;

import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.dao.mapper.metadata.TableFieldInfo;
import wang.miansen.roothub.common.dao.mapper.metadata.TableInfo;
import wang.miansen.roothub.common.util.StringUtils;

import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.springframework.aop.support.AopUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Objects;
import java.util.Collections;

/**
 * TableInfo 构建器
 * @Author: miansen.wang
 * @Date: 2019/8/31 11:56
 */
public class TableInfoBuilder {

    /**
     * TableInfo 缓存池
     */
    private static final Map<Class<?>, TableInfo> TABLE_INFO_CACHE = new ConcurrentHashMap();

    /**
     * Field 缓存池
     */
    private static final Map<Class<?>, List<Field>> FIELD_CACHE = new ConcurrentHashMap();

    public static TableInfo getTableInfo(Class<?> modelClass) {
        if (modelClass == null) {
            return null;
        } else {
            return TABLE_INFO_CACHE.get(modelClass);
        }
    }

    /**
     * 初始化 TableInfo
     * @param builderAssistant
     * @param modelClass
     * @return
     */
    public static synchronized TableInfo initTableInfo (MapperBuilderAssistant builderAssistant, Class<?> modelClass) {
        TableInfo tableInfo = TABLE_INFO_CACHE.get(modelClass);
        if (tableInfo != null) {
            return tableInfo;
        } else {
            tableInfo = new TableInfo(modelClass);
            initTableName(modelClass, tableInfo);
            initTableFields(modelClass, tableInfo);
            TABLE_INFO_CACHE.put(modelClass, tableInfo);
            return tableInfo;
        }
    }

    /**
     * 初始化 TableName
     * @param modelClass
     * @param tableInfo
     */
    public static void initTableName(Class<?> modelClass, TableInfo tableInfo) {
        String tableName = modelClass.getSimpleName();
        // 获取类上的 TableName 注解
        Table tableNameAnnotation = modelClass.getAnnotation(Table.class);
        if (tableNameAnnotation != null && !"".equals(tableNameAnnotation.value())) {
            tableName = tableNameAnnotation.value();
        } else {
            tableName = StringUtils.camelToUnderline(tableName);
        }
        tableInfo.setTableName(tableName);
    }

    /**
     * 初始化主键
     * @param field
     */
    public static void initTableId(TableInfo tableInfo, Field field) {
        // 获取字段上的 TableId 注解
        Id fieldAnnotation = field.getAnnotation(Id.class);
        if (fieldAnnotation != null) {
            String keyColumn = field.getName();
            if (!"".equals(fieldAnnotation.value())) {
                keyColumn = fieldAnnotation.value();
            } else {
                keyColumn = StringUtils.camelToUnderline(keyColumn);
            }
            tableInfo.setKeyColumn(keyColumn);
            tableInfo.setKeyProperty(field.getName());
            tableInfo.setIdType(fieldAnnotation.type());
        }
    }

    /**
     * 初始化 TableFieldInfo 和 主键
     * @param modelClass
     * @param tableInfo
     */
    public static void initTableFields(Class<?> modelClass, TableInfo tableInfo) {
        List<Field> fieldList = getAllFields(modelClass);
        List<TableFieldInfo> tableFieldInfoList = new ArrayList<>();
        for (Iterator<Field> iterator = fieldList.iterator(); iterator.hasNext();) {
            Field field = iterator.next();
            tableFieldInfoList.add(new TableFieldInfo(field));
            // 初始化主键
            initTableId(tableInfo, field);
        }
        // 初始化 TableFieldInfo
        tableInfo.setTableFieldInfoList(tableFieldInfoList);
    }

    /**
     * 获取 Model 所有的字段
     * @param modelClass
     * @return
     */
    public static List<Field> getAllFields(Class<?> modelClass) {
        // 如果 modelClass 是代理对象，则需要获取原来的 modelClass
        if (AopUtils.isAopProxy(modelClass)
                || AopUtils.isJdkDynamicProxy(modelClass)
                || AopUtils.isCglibProxy(modelClass)) {
            modelClass = AopUtils.getTargetClass(modelClass);
        }
        List<Field> fieldList = getFieldList(modelClass);
        return fieldList;
    }

    /**
     * 根据 modelClass 获取 Field
     * @param modelClass
     * @return
     */
    public static List<Field> getFieldList (Class<?> modelClass) {
        if (Objects.isNull(modelClass) || Object.class.equals(modelClass)) {
            return Collections.emptyList();
        } else {
            List<Field> fieldList = FIELD_CACHE.get(modelClass);
            if (CollectionUtils.isEmpty(fieldList)) {
                fieldList = Stream.of(modelClass.getDeclaredFields()).filter(field -> {
                    // 排除被 static 修饰的字段（Field 的 getModifiers() 方法返回 int 类型值表示该字段的修饰符）
                    return !Modifier.isStatic(field.getModifiers());
                }).filter(field -> {
                    // 排除被 Transient 修饰的字段
                    return !Modifier.isTransient(field.getModifiers());
                }).collect(Collectors.toCollection(LinkedList::new));
            }
            FIELD_CACHE.put(modelClass,fieldList);
            return fieldList;
        }
    }
}
