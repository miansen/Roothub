package wang.miansen.roothub.common.dao.mapper.injector.methods;

import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;

import wang.miansen.roothub.common.dao.mapper.enums.SqlMethod;
import wang.miansen.roothub.common.dao.mapper.metadata.TableInfo;

/**
 * 根据主键 ID 集合，批量查询多条数据。
 *
 * @author miansen.wang
 * @date 2019-10-16 22:14
 */
public class SelectBatchIds extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> entityClass, TableInfo tableInfo) {
        SqlMethod selectBatchByIds = SqlMethod.SELECT_BATCH_BY_IDS;
        String sqlScript = String.format(selectBatchByIds.getSql(), tableInfo.getSelectColumnSegment(false),
            tableInfo.getTableName(), tableInfo.getPrimaryKeyColumnName(), getIdsScript());
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sqlScript, entityClass);
        return this.addMappedStatement(mapperClass, selectBatchByIds.getMethod(), sqlSource, SqlCommandType.SELECT, String.class, null,
            entityClass, new NoKeyGenerator(), tableInfo.getPrimaryKeyPropertyName(), tableInfo.getPrimaryKeyColumnName());
    }
}
