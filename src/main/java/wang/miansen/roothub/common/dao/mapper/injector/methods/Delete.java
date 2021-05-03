package wang.miansen.roothub.common.dao.mapper.injector.methods;

import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;

import wang.miansen.roothub.common.dao.mapper.enums.SqlMethod;
import wang.miansen.roothub.common.dao.mapper.metadata.TableInfo;

/**
 * 删除满足条件的数据
 *
 * @author miansen.wang
 * @date 2019-11-19 15:51
 */
public class Delete extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> entityClass, TableInfo tableInfo) {
        SqlMethod delete = SqlMethod.DELETE;
        String sqlScript = String.format(delete.getSql(), tableInfo.getTableName(), getWrapperScript());
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sqlScript, entityClass);
        return this.addMappedStatement(mapperClass, delete.getMethod(), sqlSource, SqlCommandType.DELETE, String.class, null,
            Integer.class, new NoKeyGenerator(), tableInfo.getPrimaryKeyPropertyName(), tableInfo.getPrimaryKeyColumnName());
    }
}
