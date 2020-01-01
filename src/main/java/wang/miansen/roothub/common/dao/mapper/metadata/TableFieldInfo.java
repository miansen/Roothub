package wang.miansen.roothub.common.dao.mapper.metadata;

import wang.miansen.roothub.common.dao.mapper.util.SqlScriptUtils;
import wang.miansen.roothub.common.dao.mapper.util.StringPool;
import wang.miansen.roothub.common.util.StringUtils;

import java.lang.reflect.Field;

/**
 * TableFieldInfo 存储了数据库表的字段信息
 * @Author: miansen.wang
 * @Date: 2019/9/1 15:28
 */
public class TableFieldInfo implements StringPool {

	/**
	 * 数据库字段名
	 */
	private final String column;

	/**
	 * 实体类属性名
	 */
	private final String property;

	/**
	 * 属性类型
	 */
	private final Class<?> propertyType;

	/**
	 * 实体类型
	 */
	private final Class<?> modelClass;

	/**
	 * 是否查询该字段
	 */
	private boolean select = true;

	public TableFieldInfo(Field field) {
		this.column = StringUtils.camelToUnderline(field.getName());
		this.property = field.getName();
		this.propertyType = field.getType();
		this.modelClass = field.getDeclaringClass();
	}

	public String getColumn() {
		return column;
	}

	public String getProperty() {
		return property;
	}

	public Class<?> getPropertyType() {
		return propertyType;
	}

	public Class<?> getModelClass() {
		return modelClass;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	/**
	 * 获取插入的值的表达式
	 * @return #{别名.property}
	 */
	public String getInsertValueSegment() {
		return SqlScriptUtils.safeParam(ENTITY_DOT + property);
	}

	/**
	 * 获取更新的表达式
	 * @return column = #{别名.property}
	 */
	public String getSetSegment() {
		return column + EQUALS + SqlScriptUtils.safeParam(ENTITY_DOT + property);
	}
}
