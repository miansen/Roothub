package cn.roothub.bbs.common.dao.config;

import cn.roothub.bbs.common.dao.register.MybatisMapperRegistry;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: miansen.wang
 * @Date: 2019/8/29 22:24
 */
@Configuration
@DependsOn("uploadConfig")
public class BaseMapperConfig {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @PostConstruct
    public void Inject () {
        sqlSessionFactoryList.forEach(sqlSessionFactory -> {
            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
            MapperRegistry mapperRegistry = configuration.getMapperRegistry();
            List<Class<?>> mappers = new ArrayList<>(mapperRegistry.getMappers());
            MybatisMapperRegistry mybatisMapperRegistry = new MybatisMapperRegistry(configuration);
            mybatisMapperRegistry.addMappers(mappers);
        });
    }
}
