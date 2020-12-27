package wang.miansen.roothub.test.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import wang.miansen.roothub.rbac.dao.ResourceDAO;
import wang.miansen.roothub.rbac.entity.ResourceDO;
import wang.miansen.roothub.test.BaseTest;

/**
 * TODO 添加类的描述
 *
 * @author miansen.wang
 * @date 2020-12-27 18:20
 */
public class ResourceDAOTest extends BaseTest {

    @Autowired
    private ResourceDAO resourceDAO;

    @Test
    public void selectListTest() {
        List<ResourceDO> list = resourceDAO.selectList(null);
        logger.info(list.toString());
    }
}
