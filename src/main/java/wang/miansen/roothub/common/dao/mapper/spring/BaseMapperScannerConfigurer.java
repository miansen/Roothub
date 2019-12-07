package wang.miansen.roothub.common.dao.mapper.spring;

import wang.miansen.roothub.common.dao.mapper.register.BaseMapperRegistry;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.SqlSessionFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 与 spring 集成
 * @Author: miansen.wang
 * @Date: 2019/11/19 21:59
 */
@Configuration
public class BaseMapperScannerConfigurer implements InitializingBean {

    /**
     * 之所以要注入 MapperScannerConfigurer，原因有两个：
     * <ul>
     *     <li>通过它的 sqlSessionFactoryBeanName 属性获取到 SqlSessionFactory</li>
     *     <li>要等 MapperScannerConfigurer 初始化完毕之后，也就是说等它把所有 DAO 层的接口的方法都注册了之后，
     *     BaseMapperScannerConfigurer 再给各个 DAO 层接口注册从 BaseMapper 继承来的通用的增删改查方法</li>
     * </ul>
     */
    //@Autowired
    //private MapperScannerConfigurer mapperScannerConfigurer;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void afterPropertiesSet() throws Exception {
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        MapperRegistry mapperRegistry = configuration.getMapperRegistry();
        List<Class<?>> mappers = new ArrayList<>(mapperRegistry.getMappers());
        BaseMapperRegistry baseMapperRegistry = new BaseMapperRegistry(configuration);
        baseMapperRegistry.addMappers(mappers);
    }
}
