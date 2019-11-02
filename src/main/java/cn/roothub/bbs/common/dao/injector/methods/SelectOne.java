package cn.roothub.bbs.common.dao.injector.methods;

import cn.roothub.bbs.common.dao.enums.SqlMethod;
import cn.roothub.bbs.common.dao.metadata.TableInfo;
import cn.roothub.bbs.common.dao.util.SqlScriptUtils;

import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 查询满足条件的一条数据
 *
 * @Author: miansen.wang
 * @Date: 2019/8/29 22:01
 */
public class SelectOne extends AbstractMethod{

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod selectOne = SqlMethod.SELECT_ONE;
        String sqlScript = String.format(selectOne.getSql(), tableInfo.getSelectColumns(),
                tableInfo.getTableName(), SqlScriptUtils.convertWhere(SqlScriptUtils.convertIf("wrapper != null and wrapper.sqlSegment != null and wrapper.sqlSegment != ''", "${wrapper.sqlSegment}")));
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sqlScript, modelClass);
        return this.addMappedStatement(mapperClass, selectOne.getMethod(), sqlSource, SqlCommandType.SELECT, String.class, null, modelClass, new NoKeyGenerator(), null, tableInfo.getKeyColumn());
    }
}
