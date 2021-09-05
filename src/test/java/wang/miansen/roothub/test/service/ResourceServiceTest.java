package wang.miansen.roothub.test.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import wang.miansen.roothub.security.bo.SecResourceBO;
import wang.miansen.roothub.security.service.SecResourceService;
import wang.miansen.roothub.test.BaseTest;

/**
 * TODO 添加类的描述
 *
 * @author miansen.wang
 * @date 2020-12-27 18:23
 */
public class ResourceServiceTest extends BaseTest {

    @Autowired
    private SecResourceService resourceService;

    @Test
    public void listTest() {
        List<SecResourceBO> list = resourceService.list();
        logger.debug(list.toString());
    }
}
