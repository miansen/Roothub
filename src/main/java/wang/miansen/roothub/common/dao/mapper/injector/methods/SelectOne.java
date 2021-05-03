package wang.miansen.roothub.common.dao.mapper.injector.methods;

import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;

import wang.miansen.roothub.common.dao.mapper.enums.SqlMethod;
import wang.miansen.roothub.common.dao.mapper.metadata.TableInfo;

/**
 * 查询满足条件的一条数据
 *
 * @author miansen.wang
 * @date 2019/8/29 22:01
 */
public class SelectOne extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> entityClass, TableInfo tableInfo) {
        SqlMethod selectOne = SqlMethod.SELECT_ONE;
        String sqlScript = String.format(selectOne.getSql(), tableInfo.getSelectColumnSegment(true),
            tableInfo.getTableName(), getWrapperScript());
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sqlScript, entityClass);
        return this.addMappedStatement(mapperClass, selectOne.getMethod(), sqlSource, SqlCommandType.SELECT, String.class, null,
            entityClass, new NoKeyGenerator(), tableInfo.getPrimaryKeyPropertyName(), tableInfo.getPrimaryKeyColumnName());
    }
}
