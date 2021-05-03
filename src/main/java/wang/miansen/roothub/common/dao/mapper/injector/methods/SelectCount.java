package wang.miansen.roothub.common.dao.mapper.injector.methods;

import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;

import wang.miansen.roothub.common.dao.mapper.enums.SqlMethod;
import wang.miansen.roothub.common.dao.mapper.metadata.TableInfo;

/**
 * 查询满足条件的总记录数
 *
 * @author miansen.wang
 * @date 2019-10-16 22:50
 */
public class SelectCount extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod selectCount = SqlMethod.SELECT_COUNT;
        String sqlScript = String.format(selectCount.getSql(), tableInfo.getTableName(), getNormalSqlSegment());
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sqlScript, modelClass);
        return this.addMappedStatement(mapperClass, selectCount.getMethod(), sqlSource, SqlCommandType.SELECT, String.class, null,
                Integer.class, new NoKeyGenerator(),
                tableInfo.getPrimaryKeyPropertyName(), tableInfo.getPrimaryKeyColumnName());
    }
}
