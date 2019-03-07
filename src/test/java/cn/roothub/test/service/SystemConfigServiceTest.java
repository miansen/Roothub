package cn.roothub.test.service;

import java.util.Map;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
}
