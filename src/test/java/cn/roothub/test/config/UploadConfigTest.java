package cn.roothub.test.config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.config.UploadConfig;
import cn.roothub.test.base.BaseTest;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-15
 */
public class UploadConfigTest extends BaseTest{

	@Autowired
	private UploadConfig uploadConfig;
	
	@Test
	public void test01() {
		String[] resourceLocations = uploadConfig.getResourceLocations();
		System.out.println(resourceLocations);
	}
}
