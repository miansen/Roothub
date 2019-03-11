package cn.roothub.test.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.entity.SystemConfig;
import cn.roothub.service.SystemConfigService;
import cn.roothub.test.base.BaseTest;
import cn.roothub.util.JsonUtil;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-07
 */
public class SystemConfigServiceTest extends BaseTest{

	@Autowired
	private SystemConfigService systemConfigService;
	
	@Test
	public void test01() {
		Map<String, Object> map = systemConfigService.getAllMap();
		System.out.println(JsonUtil.objectToJson(map));
	}
	
	@Test
	public void test02() {
		List<SystemConfig> redisConfigs = systemConfigService.getBatchKeys(
				Arrays.asList(
						"redis_host",
						"redis_port",
						"redis_password",
						"redis_timeout",
						"redis_max_idle",
						"redis_max_total",
						"redis_database",
						"redis_ssl")
				);
		redisConfigs.forEach(System.out::println);
	}
	
	@Test
	public void test03() {
		Map<String, Object> map = systemConfigService.getAllMap();
		System.out.println(map);
	}
}
