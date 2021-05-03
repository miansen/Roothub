package wang.miansen.roothub.common.dao.mapper.metadata;

import java.lang.reflect.Field;

import lombok.Data;

import wang.miansen.roothub.common.dao.mapper.util.SqlScriptUtils;
import wang.miansen.roothub.common.dao.mapper.util.StringPool;
import wang.miansen.roothub.common.dao.mapper.util.StringUtils;

/**
 * 此类存储了数据库表字段信息
 *
 * @author miansen.wang
 * @date 2019-9-1 15:28
 */
@Data
public class TableFieldInfo implements StringPool {

    /**
     * 数据库字段名
     */
    private final String columnName;

    /**
     * 实体类属性名
     */
    private final String propertyName;

    /**
     * 实体类属性类型
     */
    private final Class<?> propertyClass;

    /**
     * 实体类类型
     */
    private final Class<?> entityClass;

    /**
     * EL 属性表达式：别名.propertyName
     */
    private final String el;

    /**
     * 是否查询该字段，true: 查询，false: 不查询。默认是 true。
     */
    private boolean isSelect = true;

    /**
     * 该字段是否为基本数据类型
     */
    private final boolean isPrimitive;

    /**
     * 该字段是否为 CharSequence 类型
     */
    private final boolean isCharSequence;

    /**
     * 构造函数
     *
     * @param field 字段对象
     */
    public TableFieldInfo(Field field) {
        this.columnName = StringUtils.camelToUnderline(field.getName());
        this.propertyName = field.getName();
        this.propertyClass = field.getType();
        this.entityClass = field.getDeclaringClass();
        this.isPrimitive = this.propertyClass.isPrimitive();
        this.isCharSequence = StringUtils.isCharSequence(this.propertyClass);
        this.el = ENTITY_DOT + this.propertyName;
    }

    /**
     * 获取 insert 字段 sql 脚本片段
     * <p>insert into table (字段) values (值);</p>
     * <p>位于 "字段" 部位</p>
     *
     * @return insert sql 脚本片段
     */
    public String getInsertColumnSegment() {
        return columnName + COMMA;
    }

    /**
     * 获取 insert 字段 sql 脚本片段，转换成 if 标签，过滤为空的字段。
     * <p>insert into table (字段) values (值);</p>
     * <p>位于 "字段" 部位</p>
     *
     * @return insert sql 脚本片段
     */
    public String getInsertNotNullColumnSegment() {
        if (isPrimitive) {
            return columnName;
        } else if (isCharSequence) {
            return SqlScriptUtils.convertIf(String.format("%s != null and %s != ''", el, el), getInsertColumnSegment());
        } else {
            return SqlScriptUtils.convertIf(String.format("%s != null", el), getInsertColumnSegment());
        }
    }

    /**
     * 获取 insert 插入值 sql 脚本片段
     * <p>insert into table (字段) values (值);</p>
     * <p>位于 "值" 部位</p>
     *
     * @return insert sql 脚本片段
     */
    public String getInsertValueSegment() {
        return SqlScriptUtils.safeParam(el) + COMMA;
    }

    /**
     * 获取 insert 插入值 sql 脚本片段，转换成 if 标签，过滤为空的字段。
     * <p>insert into table (字段) values (值);</p>
     * <p>位于 "值" 部位</p>
     *
     * @return insert sql 脚本片段
     */
    public String getInsertNotNullValueSegment() {
        if (isPrimitive) {
            return columnName;
        } else if (isCharSequence) {
            return SqlScriptUtils.convertIf(String.format("%s != null and %s != ''", el, el), getInsertValueSegment());
        } else {
            return SqlScriptUtils.convertIf(String.format("%s != null", el), getInsertValueSegment());
        }
    }

    /**
     * 获取这个字段对象的更新的表达式（排除 null），例如：
     * <code>
     * <pre>
     * 		<if test="entity.name != null">
     * 			name = #{entity.name},
     * 		<if>
     * 	</pre>
     * <code>
     */
    public String getSetSegment() {
        return SqlScriptUtils.convertIf(el + " != null",
            columnName + EQUALS + SqlScriptUtils.safeParam(el) + COMMA);
    }
}
