package wang.miansen.roothub.common.dao.mapper.builder;

import wang.miansen.roothub.common.dao.mapper.injector.DefaultSqlInjector;
import wang.miansen.roothub.common.dao.mapper.injector.ISqlInjector;

import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder;
import org.apache.ibatis.session.Configuration;

/**
 * BaseMapper 构建器，它的主要职责是：
 * <p>1.初始化 MapperBuilderAssistant
 * <p>2.调用 ISqlInjector.inspectInject() 方法注入常用的增删改查方法
 * @Author: miansen.wang
 * @Date: 2019/8/31 16:31
 */
public class BaseMapperBuilder extends MapperAnnotationBuilder{

    /**
     * Mybatis 的配置信息，存储了所有 Mapper 注册与绑定的信息
     */
    private final Configuration configuration;

    /**
     * Mapper 构建助手，将 Mapper 节点信息封装成 MappedStatement 添加到 Configuration 的 mappedStatements 中
     */
    private final MapperBuilderAssistant assistant;

    /**
     *  Mapper 接口
     */
    private final Class<?> mapperClass;

    /**
     * 初始化各个属性
     * @param configuration
     * @param mapperClass
     */
    public BaseMapperBuilder(Configuration configuration, Class<?> mapperClass) {
        super(configuration, mapperClass);
        String resource = mapperClass.getName().replace('.', '/') + ".java (best guess)";
        this.configuration = configuration;
        this.assistant = new MapperBuilderAssistant(configuration, resource);
        this.mapperClass = mapperClass;
        // 设置当前 Mapper 的全局命名空间
        this.assistant.setCurrentNamespace(mapperClass.getName());
    }

    /**
     * 注入常用的增删改查方法
     */
    @Override
    public void parse() {
        ISqlInjector sqlInjector = new DefaultSqlInjector();
        sqlInjector.inspectInject(this.assistant,mapperClass);
    }
}
