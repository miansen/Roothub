package wang.miansen.roothub.test.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import wang.miansen.roothub.security.dao.SecResourceDAO;
import wang.miansen.roothub.security.entity.SecResourceDO;
import wang.miansen.roothub.test.BaseTest;

/**
 * TODO 添加类的描述
 *
 * @author miansen.wang
 * @date 2020-12-27 18:20
 */
public class ResourceDAOTest extends BaseTest {

    @Autowired
    private SecResourceDAO resourceDAO;

    @Test
    public void selectListTest() {
        List<SecResourceDO> list = resourceDAO.selectList(null);
        logger.info(list.toString());
    }
}
