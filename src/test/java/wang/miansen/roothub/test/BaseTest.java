package wang.miansen.roothub.test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * 配置 spring 和 junit 整合，junit 启动时加载 spring IOC 容器 spring-test，junit。
 * <p><其他测试类只需继承该类即可进行单元测试。/p>
 *
 * @author miansen.wang
 * @date 2020-12-27 10:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
@Slf4j
public class BaseTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
