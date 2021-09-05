package wang.miansen.roothub.test.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import wang.miansen.roothub.security.bo.SecPermissionBO;
import wang.miansen.roothub.security.service.SecPermissionService;
import wang.miansen.roothub.test.BaseTest;

/**
 * TODO 添加类的描述
 *
 * @author miansen.wang
 * @date 2020-12-27 15:41
 */
public class PermissionServiceTest extends BaseTest {

    @Autowired
    private SecPermissionService permissionService;

    @Test
    public void listByResourceIdTest() {
        List<SecPermissionBO> list = permissionService.listByResourceId(1L);
        logger.info(list.toString());
    }
}
