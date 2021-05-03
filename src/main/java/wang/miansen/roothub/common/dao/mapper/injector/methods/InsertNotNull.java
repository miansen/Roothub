package wang.miansen.roothub.common.dao.mapper.injector.methods;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;

import wang.miansen.roothub.common.dao.mapper.enums.SqlMethod;
import wang.miansen.roothub.common.dao.mapper.metadata.TableInfo;
import wang.miansen.roothub.common.dao.mapper.util.StringPool;

/**
 * 插入一条数据，过滤 null 字段和空字符串。
 *
 * @author miansen.wang
 * @date 2021-05-03 17:37
 */
public class InsertNotNull extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> entityClass, TableInfo tableInfo) {
        SqlMethod insertNotNull = SqlMethod.INSERT_NOT_NULL;
        String sqlScript = String.format(insertNotNull.getSql(), tableInfo.getTableName(),
            StringPool.NEWLINE + tableInfo.getInsertNotNullColumnSegment() + StringPool.NEWLINE,
            StringPool.NEWLINE + tableInfo.getInsertNotNullValueSegment());
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sqlScript, entityClass);
        return this.addMappedStatement(mapperClass, insertNotNull.getMethod(), sqlSource, SqlCommandType.INSERT, String.class, null,
                Integer.class, getKeyGenerator(tableInfo), tableInfo.getPrimaryKeyPropertyName(),
                tableInfo.getPrimaryKeyColumnName());
    }
}
