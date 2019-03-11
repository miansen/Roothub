package cn.roothub.test.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.config.service.RedisService;
import cn.roothub.test.base.BaseTest;
import redis.clients.jedis.Jedis;

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
	
	@Test
	public void test02() {
		Jedis jedis = RedisTool.getJedis();
	}
	
	/**
	 * 测试RedisService.setString
	 */
	@Test
	public void test03() {
		System.out.println(redisService);
		redisService.setString("redisServiceTest", "succes02");
	}
	
	/**
	 * 测试RedisService.getString
	 */
	@Test
	public void test04() {
		String string = redisService.getString("redisServiceTest");
		System.out.println(string);
	}
}
