package cn.roothub.bbs.common.dao.injector.methods;

import cn.roothub.bbs.common.dao.metadata.TableFieldInfo;
import cn.roothub.bbs.common.dao.metadata.TableInfo;
import cn.roothub.bbs.common.dao.builder.TableInfoBuilder;
import static cn.roothub.bbs.common.dao.util.StringPool.NEWLINE;
import static cn.roothub.bbs.common.dao.util.StringPool.QUOTE;
import static cn.roothub.bbs.common.dao.util.StringPool.RIGHT_CHEV;

import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

/**
 * 此类提供了注入 SQL 的方法 inject()
 * 具体注入的逻辑在这个方法 injectMappedStatement() 里，由子类实现
 * @Author: miansen.wang
 * @Date: 2019/8/26 22:26
 */
public abstract class AbstractMethod {

    private Logger log = LoggerFactory.getLogger(AbstractMethod.class);

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
            TableInfo tableInfo = TableInfoBuilder.initTableInfo(builderAssistant, modelClass);
            this.injectMappedStatement(mapperClass, modelClass, tableInfo);
        }
    }

    /**
     * 初始化查询字段
     * @param tableInfo
     * @return
     */
    protected String initSqlSelectColumns (TableInfo tableInfo) {
        String selectColumns = tableInfo.getTableFieldInfoList().stream().filter(TableFieldInfo::isSelect)
                .map(TableFieldInfo::getColumn).collect(Collectors.joining(","));
        return "<choose>" + "\n"
                + "<when test=\"" + "wrapper != null and wrapper.selectColumns != null" + QUOTE + RIGHT_CHEV + NEWLINE
                + "${wrapper.selectColumns}" + NEWLINE + "</when>" + NEWLINE
                + "<otherwise>" + selectColumns + "</otherwise>" + NEWLINE
                + "</choose>";
    }

    /**
     * 注入 MappedStatement，具体的注入逻辑由子类实现
     * @param mapperClass
     * @param modelClass
     * @param tableInfo
     * @return
     */
    public abstract MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo);

    /**
     * 添加 MappedStatement
     * @param mapperClass
     * @param id
     * @param sqlSource
     * @param sqlCommandType
     * @param parameterClass
     * @param resultMap
     * @param resultType
     * @param keyGenerator
     * @param keyProperty
     * @param keyColumn
     * @return
     */
    protected MappedStatement addMappedStatement (Class<?> mapperClass, String id, SqlSource sqlSource, SqlCommandType sqlCommandType, Class<?> parameterClass, String resultMap, Class<?> resultType, KeyGenerator keyGenerator, String keyProperty, String keyColumn) {
        String statementName = mapperClass.getName() + "." + id;
        if (configuration.hasStatement(statementName)) {
            log.error(statementName + "Has been loaded by XML or SqlProvider, ignoring the injection of the SQL");
            return null;
        } else {
            boolean isSelect = false;
            if (sqlCommandType == SqlCommandType.SELECT) {
                isSelect = true;
            }
            return this.builderAssistant.addMappedStatement(id, sqlSource, StatementType.PREPARED, sqlCommandType, null, null, null, parameterClass, resultMap, resultType, null, !isSelect, isSelect, false, keyGenerator, keyProperty,keyColumn, this.configuration.getDatabaseId(), this.languageDriver, null);
        }
    }
}
