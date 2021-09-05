package wang.miansen.roothub.test.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import wang.miansen.roothub.security.dao.SecRoleDAO;
import wang.miansen.roothub.security.entity.SecRoleDO;
import wang.miansen.roothub.test.BaseTest;

/**
 * RoleDAO 单元测试
 *
 * @author miansen.wang
 * @date 2020-12-27 10:31
 */
public class RoleDAOTest extends BaseTest {

    @Autowired
    private SecRoleDAO roleDAO;

    @Test
    public void test01() {
        List<SecRoleDO> list = roleDAO.selectList(null);
        logger.info(list.toString());
    }

}
