package cn.roothub.test.service;

import org.junit.Test;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-05
 */
public class MyTest {

	@Test
	public void test01() {
		String a = new String("张三");
		String b = new String("张三");
		
		System.out.println(a == b);
		System.out.println(a.equals(b));
		
		String a2 = "张三";
		String b2 = "张三";
		
		System.out.println(a2 == b2);
		System.out.println(a2.equals(b2));
		
		System.out.println(a == a2);
		System.out.println(a.equals(a2));
	}
}
