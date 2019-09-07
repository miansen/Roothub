package cn.roothub.bbs.common.dao.injector.methods;

import cn.roothub.bbs.common.dao.enums.SqlMethod;
import cn.roothub.bbs.common.dao.metadata.TableInfo;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.*;

/**
 * @Author: miansen.wang
 * @Date: 2019/8/29 22:01
 */
public class SelectOne extends AbstractMethod{

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod selectOne = SqlMethod.SELECT_ONE;
        String sqlScript = String.format(selectOne.getSql(), initSqlSelectColumns(tableInfo), tableInfo.getTableName(), "${whereBlock}");
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sqlScript, modelClass);
        return this.addMappedStatement(mapperClass, selectOne.getMethod(), sqlSource, SqlCommandType.SELECT, String.class, null, modelClass, new NoKeyGenerator(), null, null);
    }
}
