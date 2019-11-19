package cn.roothub.bbs.common.dao.spring;

import org.mybatis.spring.mapper.MapperScannerConfigurer;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 与 spring 集成
 * @Author: miansen.wang
 * @Date: 2019/11/19 21:59
 */
public class BaseMapperScannerConfigurer implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    /**
     * 之所以要注入 MapperScannerConfigurer，原因有两个：
     * <ul>
     *     <li>通过它的 sqlSessionFactoryBeanName 属性获取到 SqlSessionFactory</li>
     *     <li>要等 MapperScannerConfigurer 初始化完毕之后，也就是说等它把所有 DAO 层的接口的方法都注册了之后，
     *     BaseMapperScannerConfigurer 再给各个 DAO 层接口注册从 BaseMapper 继承来的通用的增删改查方法</li>
     * </ul>
     */
    private MapperScannerConfigurer mapperScannerConfigurer;

    private ApplicationContext applicationContext;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
