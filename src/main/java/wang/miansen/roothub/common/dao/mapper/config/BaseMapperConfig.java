package wang.miansen.roothub.common.dao.mapper.config;

import wang.miansen.roothub.common.dao.mapper.register.BaseMapperRegistry;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: miansen.wang
 * @Date: 2019/8/29 22:24
 */
// @Configuration
// @DependsOn("uploadConfig")
public class BaseMapperConfig {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Autowired
    private MapperScannerConfigurer mapperScannerConfigurer;

    @PostConstruct
    public void Inject () {
        sqlSessionFactoryList.forEach(sqlSessionFactory -> {
            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
            MapperRegistry mapperRegistry = configuration.getMapperRegistry();
            List<Class<?>> mappers = new ArrayList<>(mapperRegistry.getMappers());
            BaseMapperRegistry mybatisMapperRegistry = new BaseMapperRegistry(configuration);
            mybatisMapperRegistry.addMappers(mappers);
        });
    }
}
