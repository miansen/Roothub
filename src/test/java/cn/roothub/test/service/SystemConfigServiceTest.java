package cn.roothub.test.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.roothub.entity.SystemConfig;
import cn.roothub.entity.User;
import cn.roothub.service.SystemConfigService;
import cn.roothub.test.base.BaseTest;
import cn.roothub.test.service.SystemConfigServiceTest.MyTypeToKen;
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
	
	@Test
	public void test04() {
		List<SystemConfig> list = systemConfigService.edit(1);
		list.forEach(System.out::println);
	}
	
	@Test
	public void test05() {
		List<SystemConfig> list = systemConfigService.getAllList();
		String json = JsonUtil.objectToJson(list);
		
		System.out.println(json);
		System.out.println(list);
		//list.forEach(System.out::println);
		System.out.println(list.get(0).getDescription());
	}
	
	@Test
	public void test06() {
		SystemConfig systemConfig = new SystemConfig();
		systemConfig.setSystemConfigId(1);
		systemConfig.setKey("base_url");
		systemConfig.setValue("localhost");
		systemConfig.setDescription("URL");
		systemConfig.setType("text");
		
		String json = JsonUtil.objectToJson(systemConfig);
		
		System.out.println(json);
		System.out.println(JsonUtil.gson);
		
		SystemConfig systemConfig2 = new SystemConfig();
		systemConfig2.setSystemConfigId(1);
		systemConfig2.setKey("base_url");
		systemConfig2.setValue("localhost");
		systemConfig2.setDescription("URL");
		systemConfig2.setType("text");
		
		String json2 = JsonUtil.objectToJson(systemConfig2);
		
		System.out.println(json2);
		System.out.println(JsonUtil.gson);
	}
	
	@Test
	public void test07() {
		Gson gson = JsonUtil.gson;
		System.out.println(gson);
		Gson gson2 = JsonUtil.gson;
		System.out.println(gson2);
		System.out.println(gson == gson2);
	}
	
	@Test
	public void test08() {
		List<SystemConfig> list = new ArrayList<>();
		Type class1 = list.getClass();
		
		new TypeToken<List>() {}.getType();
		
		TypeToken<User> typeToken = new TypeToken<User>(){};
		
		Type type = new TypeToken<List<SystemConfig>>(){}.getType();
		System.out.println(type);
		
		TypeToken<?> typeToken2 = TypeToken.get(class1);
		System.out.println(typeToken2);
		
		SystemConfig systemConfig = new SystemConfig() {};
		System.out.println(systemConfig);
	}
	
	@Test
	public void test09() {
		MyTypeToKen myTypeToKen = new MyTypeToKen();
		Type type = myTypeToKen.getType();
		Class<? extends MyTypeToKen> class1 = myTypeToKen.getClass();
		System.out.println(type);
		System.out.println(class1);
		System.out.println(new ArrayList<SystemConfig>().getClass());
	}
	
	@Test
	public void test10() {
		List<SystemConfig> list = systemConfigService.getByPid(1);
		System.out.println(list);
		String json = JsonUtil.objectToJson(list);
		System.out.println(json);
		List<SystemConfig> object = JsonUtil.jsonToObject(json, List.class);
		System.out.println(object);
		//List<SystemConfig> jsonToList = JsonUtil.jsonToList(json, new ArrayList<SystemConfig>());
		//System.out.println(jsonToList);
		Object fromJson = new Gson().fromJson(json, new TypeToken<List<SystemConfig>>() {}.getType());
		System.out.println(fromJson);
		
	}
	
	@Test
	public void test11() {
		Class a = int.class;
		System.out.println(a);
		System.out.println(Integer.TYPE);
		System.out.println(Integer.class);
		System.out.println(int.class == Integer.TYPE);
		System.out.println(int.class == Integer.class);
		System.out.println(SystemConfig.class);
		
	}
	
	@Test
	public void test12() {
		List<SystemConfig> list = systemConfigService.getByPid(1);
		System.out.println(list);
		
		String json = JsonUtil.objectToJson(list);
		System.out.println(json);
		
		Object fromJson3 = new Gson().fromJson(json, new TypeToken<List<SystemConfig>>() {}.getType());
		System.out.println(fromJson3);
		
		Object fromJson2 = new Gson().fromJson(json, new TypeToken<List<SystemConfig>>() {}.getType());
		System.out.println(fromJson2);
		
		Type type = new TypeToken<List<SystemConfig>>() {}.getType();
		Object fromJson = new Gson().fromJson(json, type);
		System.out.println(fromJson);
		
		List<SystemConfig> object = JsonUtil.jsonToObject(json, new TypeToken<List<SystemConfig>>() {}.getType());
		System.out.println(object);
		
		//SystemConfig jsonToObject = JsonUtil.jsonToObject(json, SystemConfig.class);
		
	}
	
	@Test
	public void test13() {
		Map<String, Object> map = systemConfigService.getAllMap();
		System.out.println(map);
	}
	
	@Test
	public void test15() {
		List<SystemConfig> list = systemConfigService.getByPid(2);
		list.forEach(System.out::println);
		System.out.println("-----------------------------------------------------------");
		list.stream()
		.filter(systemConfig2 -> !systemConfig2.getKey().equals("upload_type"))
		.collect(Collectors.toList())
		.forEach(System.out::println);
	}
	
	@Test
	public void test16() {
		Map<String, Object> maps = systemConfigService.getUploadConfig();
		
		for(Map.Entry<String, Object> map : maps.entrySet()) {
			System.out.println(map);
		}
		
		System.out.println("-----------------------------------------------------");
		Iterator<Entry<String, Object>> iterator = maps.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, Object> next = iterator.next();
			System.out.println(next);
		}
		System.out.println("-----------------------------------------------------");
		for(String key : maps.keySet()) {
			System.out.println(key);
		}
		System.out.println("-----------------------------------------------------");
		for(Object value : maps.values()) {
			System.out.println(value);
		}
	}
	
	@Test
	public void test17() {
		List<String> asList = Arrays.asList("redis_host", "redis_port",
				"open_redis", "redis_timeout", "redis_max_idle", "redis_max_total", "redis_database", "redis_ssl");
		asList.forEach(System.out::println);
		List<SystemConfig> redisConfigs = systemConfigService.getBatchKeys(asList);
		redisConfigs.forEach(System.out::println);
	}
	
	@Test
	public void test18() {
		Map<String, Object> maps = systemConfigService.getUploadConfig();
		for(Object obj : maps.values()) {
			System.out.println(obj);
		}
	}
	
	class MyTypeToKen extends TypeToken<User>{
		
	}
}