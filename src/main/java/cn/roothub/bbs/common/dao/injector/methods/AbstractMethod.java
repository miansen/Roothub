package cn.roothub.bbs.common.dao.injector.methods;

import cn.roothub.bbs.common.dao.metadata.TableFieldInfo;
import cn.roothub.bbs.common.dao.metadata.TableInfo;
import cn.roothub.bbs.common.dao.builder.TableInfoBuilder;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;
import java.util.stream.Collectors;

/**
 * 此类提供了注入 SQL 的方法 inject()
 * 具体注入的逻辑在这个方法 injectMappedStatement() 里，由子类实现
 * @Author: miansen.wang
 * @Date: 2019/8/26 22:26
 */
public abstract class AbstractMethod {

    /**
     * Mybatis 的配置信息，存储了所有 Mapper 注册与绑定的信息
     */
    protected Configuration configuration;

    /**
     * Mybatis 的语言驱动，主要作用是解析 sql 包装为 SqlSource
     */
    protected LanguageDriver languageDriver;

    /**
     * Mapper 构建助手，将 Mapper 节点信息封装成 MappedStatement 添加到 Configuration 的 mappedStatements 中
     */
    protected MapperBuilderAssistant builderAssistant;

    public void inject(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass){
        this.configuration = builderAssistant.getConfiguration();
        // 使用默认的语言驱动 XMLLanguageDriver
        this.languageDriver = this.configuration.getDefaultScriptingLanuageInstance();
        this.builderAssistant = builderAssistant;
        if (modelClass != null) {
            TableInfo tableInfo = TableInfoBuilder.initTableInfo(builderAssistant,modelClass);
            this.injectMappedStatement(mapperClass, modelClass, tableInfo);
        }
    }

    /**
     * 初始化查询字段
     * @param tableInfo
     * @return
     */
    protected String initSqlSelectColumns (TableInfo tableInfo) {
        return tableInfo.getTableFieldInfoList().stream().filter(TableFieldInfo::isSelect)
                .map(TableFieldInfo::getColumn).collect(Collectors.joining(","));
    }

    public abstract MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo);
}
