package cn.roothub.test.config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.config.properties.StorageProperties;
import cn.roothub.test.base.BaseTest;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-20
 */
public class StoragePropertiestTest extends BaseTest {

	@Autowired
	private StorageProperties storageProperties;
	
	@Test
	public void test01() {
		String localUploadTopicFiledir = storageProperties.getLocalUploadTopicFiledir();
		String localUploadNodeFiledir = storageProperties.getLocalUploadNodeFiledir();
		String localUploadTagFiledir = storageProperties.getLocalUploadTagFiledir();
		String localUploadUserFiledir = storageProperties.getLocalUploadUserFiledir();
		System.out.println(localUploadTopicFiledir);
		System.out.println(localUploadNodeFiledir);
		System.out.println(localUploadTagFiledir);
		System.out.println(localUploadUserFiledir);
	}
	
	@Test
	public void test02() {
		String str = "file:F:/upload/roothub/topic/";
		String substring = str.substring(5, str.length());
		System.out.println(substring);
		String replace = str.replace("file:", "");
		System.out.println(replace);
		System.out.println(substring.equals(replace));

	}
}
