package wang.miansen.roothub.common.dao.mapper.injector.methods;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;

import wang.miansen.roothub.common.dao.mapper.enums.SqlMethod;
import wang.miansen.roothub.common.dao.mapper.metadata.TableInfo;

/**
 * 插入一条数据
 *
 * @author miansen.wang
 * @date 2019-10-26 17:34
 */
public class Insert extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> entityClass, TableInfo tableInfo) {
        SqlMethod insert = SqlMethod.INSERT;
        String sqlScript = String.format(insert.getSql(), tableInfo.getTableName(), tableInfo.getInsertColumnSegment(),
            tableInfo.getInsertValueSegment());
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sqlScript, entityClass);
        // 这里的主键应该用 EL 表达式，因为 insert 方法使用了别名。否则获取主键时会报错，提示无法找到主键字段
        return this.addMappedStatement(mapperClass, insert.getMethod(), sqlSource, SqlCommandType.INSERT, String.class, null,
            Integer.class, getKeyGenerator(tableInfo), tableInfo.getPrimaryKeyPropertyEl(),
            tableInfo.getPrimaryKeyColumnName());
    }
}
