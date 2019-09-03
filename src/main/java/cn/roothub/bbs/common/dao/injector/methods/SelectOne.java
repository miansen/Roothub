package cn.roothub.bbs.common.dao.injector.methods;

import cn.roothub.bbs.common.dao.metadata.TableInfo;
import org.apache.ibatis.mapping.*;


/**
 * @Author: miansen.wang
 * @Date: 2019/8/29 22:01
 */
public class SelectOne extends AbstractMethod{

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sqlScript = String.format("<script>\nSELECT %s FROM %s\n</script>", initSqlSelectColumns(tableInfo), tableInfo.getTableName());
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sqlScript, modelClass);
        return this.builderAssistant.addMappedStatement("SelectOne",
                    sqlSource, StatementType.PREPARED, SqlCommandType.SELECT, (Integer)null, (Integer)null, (String)null,
                null, null, modelClass, (ResultSetType)null, true, true, false,
                null, null, tableInfo.getPrimaryKeyColumn(), this.configuration.getDatabaseId(), this.languageDriver,
                (String)null);
    }
}
