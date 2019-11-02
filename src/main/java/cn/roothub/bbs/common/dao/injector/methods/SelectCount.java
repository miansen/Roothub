package cn.roothub.bbs.common.dao.injector.methods;

import cn.roothub.bbs.common.dao.enums.SqlMethod;
import cn.roothub.bbs.common.dao.metadata.TableInfo;
import cn.roothub.bbs.common.dao.util.SqlScriptUtils;

import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 查询满足条件的总记录数
 *
 * @Author: miansen.wang
 * @Date: 2019/10/16 22:50
 */
public class SelectCount extends AbstractMethod{

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod selectCount = SqlMethod.SELECT_COUNT;
        String sqlScript = String.format(selectCount.getSql(), tableInfo.getTableName(),
                SqlScriptUtils.convertWhere(SqlScriptUtils.convertIf("wrapper != null and wrapper.sqlSegment != null and wrapper.sqlSegment != ''", "${wrapper.sqlSegment}")));
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sqlScript, modelClass);
        return this.addMappedStatement(mapperClass, selectCount.getMethod(), sqlSource, SqlCommandType.SELECT, String.class, null, Integer.class, new NoKeyGenerator(), null, tableInfo.getKeyColumn());
    }
}
