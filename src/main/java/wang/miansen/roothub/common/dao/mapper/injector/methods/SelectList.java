package wang.miansen.roothub.common.dao.mapper.injector.methods;

import wang.miansen.roothub.common.dao.mapper.enums.SqlMethod;
import wang.miansen.roothub.common.dao.mapper.metadata.TableInfo;

import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 查询满足条件的多条数据
 *
 * @Author: miansen.wang
 * @Date: 2019/10/16 23:00
 */
public class SelectList extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod selectList = SqlMethod.SELECT_LIST;
        String sqlScript = String.format(selectList.getSql(), tableInfo.getSelectColumnSegments(true),
                tableInfo.getTableName(), getWrapperScript());
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sqlScript, modelClass);
        return this.addMappedStatement(mapperClass, selectList.getMethod(), sqlSource, SqlCommandType.SELECT, String.class, null, modelClass, new NoKeyGenerator(), null, tableInfo.getKeyColumn());
    }
}
