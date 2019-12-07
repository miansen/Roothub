package wang.miansen.roothub.common.dao.mapper.injector.methods;

import wang.miansen.roothub.common.dao.mapper.enums.SqlMethod;
import wang.miansen.roothub.common.dao.mapper.metadata.TableInfo;

import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据 ID 集合，批量查询多条数据
 *
 * @Author: miansen.wang
 * @Date: 2019/10/16 22:14
 */
public class SelectBatchIds extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod selectBatchByIds = SqlMethod.SELECT_BATCH_BY_IDS;
        String sqlScript = String.format(selectBatchByIds.getSql(), tableInfo.getSelectColumnSegments(false),
                tableInfo.getTableName(), tableInfo.getKeyColumn(), getIdsScript());
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sqlScript, modelClass);
        return this.addMappedStatement(mapperClass, selectBatchByIds.getMethod(), sqlSource, SqlCommandType.SELECT, String.class, null, modelClass, new NoKeyGenerator(), null, tableInfo.getKeyColumn());
    }
}
