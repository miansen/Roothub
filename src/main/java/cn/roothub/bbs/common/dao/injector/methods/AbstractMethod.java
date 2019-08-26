package cn.roothub.bbs.common.dao.injector.methods;

import cn.roothub.bbs.common.dao.metadata.TableInfo;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;

/**
 * @Author: miansen.wang
 * @Date: 2019/8/26 22:26
 */
public abstract class AbstractMethod {

    protected Configuration configuration;

    protected LanguageDriver languageDriver;

    protected MapperBuilderAssistant builderAssistant;

    public void inject(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass){
        this.configuration = builderAssistant.getConfiguration();
        this.languageDriver = this.configuration.getDefaultScriptingLanuageInstance();
        this.builderAssistant = builderAssistant;
        if (modelClass != null) {
            TableInfo tableInfo = new TableInfo();
            this.injectMappedStatement(mapperClass, modelClass, tableInfo);
        }
    }

    public abstract MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo);
}
