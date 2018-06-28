package priv.sen.root.redis.test;

import java.nio.charset.Charset;

import org.junit.Test;

public class RedisUtilTest {

    private static final Charset UTF_8 = Charset.forName("utf-8");  

	
	@Test
	public void testString() throws Exception{
		System.out.println("测试String类型开始======");
		String key = "Program Name";
		String value = "Redis For Windows";
		String value1 = "Input Redis For bytes";
		RedisUtil.setString(key, value);
		String string = RedisUtil.getString(key);
		RedisUtil.setBytes(key.getBytes(UTF_8), value1.getBytes(UTF_8));
		byte[] bytes = RedisUtil.getBytes(key.getBytes(UTF_8));
		System.out.println("setString====="+string);
		System.out.println("bytes====="+bytes);
	}
}
