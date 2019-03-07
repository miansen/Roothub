package cn.roothub.test.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import cn.roothub.dao.SystemConfigDao;
import cn.roothub.entity.SystemConfig;
import cn.roothub.test.base.BaseTest;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-07
 */
public class SystemConfigDaoTest extends BaseTest{

	@Autowired
	private SystemConfigDao systemConfigDao;
	
	@Test
	public void test01() {
		List<SystemConfig> list = systemConfigDao.selectByPid(0);
		list.forEach(System.out::println);
	}
}
