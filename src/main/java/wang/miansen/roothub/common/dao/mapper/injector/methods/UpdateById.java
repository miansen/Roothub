package wang.miansen.roothub.common.dao.mapper.injector.methods;

import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;

import wang.miansen.roothub.common.dao.mapper.enums.SqlMethod;
import wang.miansen.roothub.common.dao.mapper.metadata.TableInfo;

/**
 * 根据主键 ID 更新一条数据
 *
 * @author miansen.wang
 * @date 2019-11-2 23:47
 */
public class UpdateById extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> entityClass, TableInfo tableInfo) {
        SqlMethod updateById = SqlMethod.UPDATE_BY_ID;
        String sqlScript = String.format(updateById.getSql(), tableInfo.getTableName(), tableInfo.getSetSegment(),
            tableInfo.getPrimaryKeyColumnName(), tableInfo.getPrimaryKeyPropertySegment());
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sqlScript, entityClass);
        return this.addMappedStatement(mapperClass, updateById.getMethod(), sqlSource, SqlCommandType.UPDATE, String.class, null,
                Integer.class, new NoKeyGenerator(), tableInfo.getPrimaryKeyPropertyName(),
                tableInfo.getPrimaryKeyColumnName());
    }
}
