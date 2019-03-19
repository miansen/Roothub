package cn.roothub.test.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import cn.roothub.entity.AdminUser;
import cn.roothub.util.JsonUtil;

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
	
	@Test
	public void test02() {
		boolean a = false;
		System.out.println(a);
	}
	
	@Test
	public void test03() {
		// 每次new都会创建不同的对象
		Integer a = new Integer(123);
		Integer b = new Integer(123);
		System.out.println(a == b);
		System.out.println(a.equals(b));
		// valueOf会从缓存池中找出相同值的对象，所以c和d指向同一个对象
		Integer c = Integer.valueOf(123);
		Integer d = Integer.valueOf(123);
		System.out.println(c == d);
		System.out.println(c.equals(d));
		// 自动装箱调用的是valueOf方法，所以e和f也指向同一个对象
		Integer e = 123;
		Integer f = 123;
		
		System.out.println(e == f);
	}
	
	/**
	 * Java 的参数是以值传递的形式传入方法中，而不是引用传递。
	 */
	@Test
	public void test04() {
		AdminUser adminUser = new AdminUser();
		adminUser.setUsername("张三");
		System.out.println(adminUser);// cn.roothub.entity.AdminUser@53e25b76
		System.out.println(adminUser.getUsername());//张三
		func(adminUser);
		System.out.println(adminUser);//cn.roothub.entity.AdminUser@53e25b76
		System.out.println(adminUser.getUsername());//张三
	}
	
	public static void func(AdminUser adminUser) {
		adminUser = new AdminUser();
		adminUser.setUsername("李四");
		System.out.println(adminUser);//cn.roothub.entity.AdminUser@73a8dfcc
		System.out.println(adminUser.getUsername());//李四
	}
	
	@Test
	public void test05() {
		String str = "{abc:ab}";
		Map<String,Object> map = JsonUtil.jsonToObject(str, Map.class);
		System.out.println(map);
	}
	
	@Test
	public void test06() {
		List<Map<String,String>> list = new ArrayList<>();
		Map<String,String> map = new HashMap<>();
		Map<String,String> map2 = new HashMap<>();
		map.put("name", "pid");
		map.put("value", "1");
		map2.put("name", "base_url");
		map2.put("value", "localhost");
		list.add(map);
		list.add(map2);
		System.out.println(list);
		System.out.println(list.get(0));
		System.out.println(list.get(0).get("name"));
		System.out.println(list.get(1));
		System.out.println(list.get(1).get("name"));
	}
	
	@Test
	public void test() throws IOException {
		Path path = Paths.get("upload1646");
		//Files.createDirectories(path);
		boolean b = Files.exists(path);
		System.out.println("目录或者文件是否存在: "+b);
		System.out.println("对应路径：" + path.toString());
		System.out.println("父路径：" + path.getParent());
		System.out.println("根路径：" + path.getRoot());
		System.out.println("是否是绝对路径：" + path.isAbsolute());
		System.out.println("绝对路径：" + path.toAbsolutePath());
		System.out.println("路径数量：" + path.getNameCount());
		String property = System.getProperty("user.dir");
		System.out.println(property);
	}
	
	@Test
	public void test07() {
		System.out.println("java.home : "+System.getProperty("java.home"));
		System.out.println("java.class.version : "+System.getProperty("java.class.version"));
		System.out.println("java.class.path : "+System.getProperty("java.class.path"));
		System.out.println("user.dir: "+System.getProperty("user.dir"));
		//String filePath = request.getSession().getServletContext().getRealPath("/")+"/resources/images/1.jpg";
		
	}
}
