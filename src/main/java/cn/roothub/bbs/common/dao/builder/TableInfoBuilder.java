package cn.roothub.bbs.common.dao.builder;

import cn.roothub.bbs.common.dao.annotation.TableName;
import cn.roothub.bbs.common.dao.metadata.TableFieldInfo;
import cn.roothub.bbs.common.dao.metadata.TableInfo;
import cn.roothub.bbs.common.util.StringUtil;

import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.springframework.aop.support.AopUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            tableInfo = new TableInfo();
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
        // 获取 Model 类上的 TableName 注解
        TableName tableNameAnnotation = modelClass.getAnnotation(TableName.class);
        if (tableNameAnnotation != null) {
            tableName = tableNameAnnotation.value();
        } else {
            tableName = StringUtil.camelToUnderline(tableName);
        }
        tableInfo.setTableName(tableName);
    }

    /**
     * 初始化 TableFieldInfo
     * @param modelClass
     * @param tableInfo
     */
    public static void initTableFields(Class<?> modelClass, TableInfo tableInfo) {
        List<Field> fieldList = getAllFields(modelClass);
        List<TableFieldInfo> tableFieldInfoList = new ArrayList<>();
        for (Iterator<Field>iterator = fieldList.iterator(); iterator.hasNext();) {
            Field field = iterator.next();
            tableFieldInfoList.add(new TableFieldInfo(field));
        }
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
