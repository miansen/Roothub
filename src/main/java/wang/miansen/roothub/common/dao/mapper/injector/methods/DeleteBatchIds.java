package wang.miansen.roothub.common.dao.mapper.injector.methods;

import wang.miansen.roothub.common.dao.mapper.enums.SqlMethod;
import wang.miansen.roothub.common.dao.mapper.metadata.TableInfo;

import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据 ID 集合，批量删除多条数据
 * @Author: miansen.wang
 * @Date: 2019/11/19 15:34
 */
public class DeleteBatchIds extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod deleteBatchByIds = SqlMethod.DELETE_BATCH_BY_IDS;
        String sqlScript = String.format(deleteBatchByIds.getSql(), tableInfo.getTableName(), tableInfo.getKeyColumn(), getIdsScript());
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sqlScript, modelClass);
        return this.addMappedStatement(mapperClass, deleteBatchByIds.getMethod(), sqlSource, SqlCommandType.DELETE, String.class, null, Integer.class, new NoKeyGenerator(), tableInfo.getKeyProperty(), tableInfo.getKeyColumn());
    }
}
