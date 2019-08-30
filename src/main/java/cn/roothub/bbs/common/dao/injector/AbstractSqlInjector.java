package cn.roothub.bbs.common.dao.injector;

import cn.roothub.bbs.common.dao.injector.methods.AbstractMethod;
import org.apache.ibatis.builder.MapperBuilderAssistant;

import java.util.List;

/**
 * @Author: miansen.wang
 * @Date: 2019/8/30
 */
public abstract class AbstractSqlInjector implements ISqlInjector{

    /**
     * 根据 mapperClass 注入 SQL，需要检查 SQL 是否已注入(已经注入过不再注入)
     * @param builderAssistant
     * @param mapperClass
     */
    @Override
    public void inspectInject(MapperBuilderAssistant builderAssistant, Class<?> mapperClass) {
        List<AbstractMethod> methodList = this.getMethodList();
        methodList.forEach(m -> m.inject(builderAssistant,mapperClass,null));
    }

    /**
     * 获取注入的方法
     * @return 注入的方法集合
     */
    public abstract List<AbstractMethod> getMethodList();
}
