package cn.roothub.test.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.config.service.RedisService;
import cn.roothub.test.base.BaseTest;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-10
 */
public class RedisServiceTest extends BaseTest {

	@Autowired
	private RedisService redisService;
	
	@Test
	public void test01() {
		boolean b = redisService.redisConfig();
		System.out.println(b);
	}
}
