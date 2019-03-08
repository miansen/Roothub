package cn.roothub.test.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import cn.roothub.dao.SystemConfigDao;
import cn.roothub.entity.SystemConfig;
import cn.roothub.test.base.BaseTest;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-07
 */
public class SystemConfigDaoTest extends BaseTest{

	@Autowired
	private SystemConfigDao systemConfigDao;
	
	@Test
	public void test01() {
		List<SystemConfig> list = systemConfigDao.selectByPid(0);
		list.forEach(System.out::println);
		Map<String,Object> map = new LinkedHashMap<>();
		Map<String,Object> map2 = new LinkedHashMap<>();
		list.forEach(systemConfig -> {
			map.put(systemConfig.getDescription(), systemConfigDao.selectByPid(systemConfig.getSystemConfigId()));
		});
		System.out.println(map);
		map2 = list.stream()
			.filter(systemConfig -> systemConfig.getPid() != 0)
			.collect(Collectors.toMap(SystemConfig::getKey, SystemConfig::getValue));
		System.out.println(map2);
	}
	
	@Test
	public void test02() {
		List<SystemConfig> list = systemConfigDao.selectByPid(0);
		Stream<SystemConfig> stream = list.stream()
			.filter(systemConfig -> {
				return !systemConfig.getDescription().equals("基础配置");
			});
		// stream.forEach(System.out::println);
		stream.forEach(systemConfig -> {
			System.out.println(systemConfig);
		});
	}
	
	@Test
	public void test03() {
		List<SystemConfig> list = systemConfigDao.selectByPid(0);
		List<SystemConfig> list2 = list.stream()
			.filter(systemConfig -> !systemConfig.getDescription().equals("基础配置"))
			.collect(Collectors.toList());
		list2.forEach(System.out::println);
	}
	
	@Test
	public void test04() {
		List<SystemConfig> list = systemConfigDao.selectByPid(0);
		Map<String, String> map = list.stream()
			.filter(systemConfig -> !systemConfig.getDescription().equals("基础配置"))
			.collect(Collectors.toMap(SystemConfig::getKey, SystemConfig::getValue));
		System.out.println(map.get("base_url"));
		
	}
	
	@Test
	public void test05() {
		List<String> list = Arrays.asList(new String[] {"Ni","Hao","Lambda"});
		Stream<String> map = list.stream().map(String::toLowerCase);
		List<String> list2 = list.stream().map(String::toLowerCase).collect(Collectors.toList());
		map.forEach(System.out::println);
		System.out.println(list2);
		
	}
}
