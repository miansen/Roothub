package wang.miansen.roothub.common.dao.mapper.register;

import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.common.dao.mapper.builder.BaseMapperBuilder;

import org.apache.ibatis.session.Configuration;

import java.util.List;

/**
 * Mapper 注册与绑定类，主要的作用是为 BaseMapper 的导出接口注入通用的增删改查方法
 * @Author: miansen.wang
 * @Date: 2019/8/29 22:36
 */
public class BaseMapperRegistry {

    /**
     * Mybatis 的配置信息，存储了所有 Mapper 注册与绑定的信息
     */
    private Configuration configuration;

    public BaseMapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 注册 Mapper
     * @param mapper
     */
    public void addMapper(Class<?> mapper) {
        // mapper 必须实现 BaseDao 接口，才能注入。
        if (!BaseDao.class.isAssignableFrom(mapper)){
            return;
        }
        BaseMapperBuilder myBatisMapperAnnotationBuilder = new BaseMapperBuilder(configuration, mapper);
        myBatisMapperAnnotationBuilder.parse();
    }

    /**
     * 注册 Mapper
     * @param mappers
     */
    public void addMappers(List<Class<?>> mappers) {
        mappers.forEach(this::addMapper);
    }

}
