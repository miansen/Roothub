package cn.roothub.bbs.common.dao.metadata;

import cn.roothub.bbs.common.util.StringUtil;

import java.lang.reflect.Field;

/**
 * TableFieldInfo 存储了数据库表的所有字段
 * @Author: miansen.wang
 * @Date: 2019/9/1 15:28
 */
public class TableFieldInfo {

    /**
     * 字段名
     */
    private final String column;

    /**
     * 属性名
     */
    private final String property;

    /**
     * 属性类型
     */
    private final Class<?> propertyType;

    /**
     * model 类型
     */
    private final Class<?> modelClass;

    /**
     * 是否查询该字段
     */
    private boolean select = true;

    public TableFieldInfo(Field field) {
        this.column = StringUtil.camelToUnderline(field.getName());
        this.property = field.getName();
        this.propertyType = field.getType();
        this.modelClass = field.getDeclaringClass();
    }
}
