package wang.miansen.roothub.common.dao.mapper.injector.methods;

import wang.miansen.roothub.common.dao.mapper.enums.SqlMethod;
import wang.miansen.roothub.common.dao.mapper.metadata.TableInfo;

import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 插入一条数据
 *
 * @Author: miansen.wang
 * @Date: 2019/10/26 17:34
 */
public class Insert extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod insert = SqlMethod.INSERT;
        String sqlScript = String.format(insert.getSql(), tableInfo.getTableName(), tableInfo.getInsertColumnSegments(), tableInfo.getInsertValueSegments());
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sqlScript, modelClass);
        return this.addMappedStatement(mapperClass, insert.getMethod(), sqlSource, SqlCommandType.INSERT, String.class, null, Integer.class, new NoKeyGenerator(), tableInfo.getKeyProperty(), tableInfo.getKeyColumn());
    }
}
