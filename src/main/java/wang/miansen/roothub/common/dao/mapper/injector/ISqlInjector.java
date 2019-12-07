package wang.miansen.roothub.common.dao.mapper.injector;

import org.apache.ibatis.builder.MapperBuilderAssistant;

/**
 * 自动注入器接口
 * @Author: miansen.wang
 * @Date: 2019/8/30
 */
public interface ISqlInjector {

    /**
     * 根据 mapperClass 注入 SQL，需要检查 SQL 是否已注入(已经注入过不再注入)
     * @param builderAssistant
     * @param mapperClass
     */
    void inspectInject(MapperBuilderAssistant builderAssistant, Class<?> mapperClass);
}
