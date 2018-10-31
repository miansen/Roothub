package priv.sen.root.redis.test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cn.roothub.entity.User;
import cn.roothub.util.Base64Util;
import cn.roothub.util.CookieAndSessionUtil;
import cn.roothub.util.JsonUtil;
import priv.sen.root.dao.test.BaseTest;

public class RedisUtilTest extends BaseTest{

    private static final Charset UTF_8 = Charset.forName("utf-8");  
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, List<String>> redisTemplate;
    
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
	
	@Test
	public void springRedisTest() throws Exception{
		//StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		opsForValue.set("suer", "public");
		String string = opsForValue.get("suer");
		String string2 = opsForValue.get("user");
		System.out.println("springRedisGetKey:"+string);
		logger.debug(string2);
	}
	
	@Test
	public void Test01() throws Exception{
		User rootUser = new User();
		rootUser.setUserName("void");
		rootUser.setPassword("123456");
		rootUser.setUserSex("男");
		rootUser.setUserAddr("长沙");
		rootUser.setScore(100);
		rootUser.setAvatar("无");
		rootUser.setEmail("123456@qq.com");
		rootUser.setUrl("www.baidu.com");
		rootUser.setSignature("哈哈");
		rootUser.setThirdId("1158827539");
		rootUser.setReceiveMsg(false);
		rootUser.setCreateDate(new Date());
		rootUser.setUpdateDate(new Date());
		rootUser.setIsBlock(false);
		rootUser.setStatusCd("1000");
		rootUser.setLoginIp("127.0.1");
		rootUser.setLastLoginIp("127.0.1");
		rootUser.setUserType("0");
		RedisUtil.setString("user", rootUser.toString());
		String string = RedisUtil.getString("user");
		System.out.println("redis=="+string);
		JSONObject jsonObject = new JSONObject();
		JSON json = (JSON) JSON.toJSON(string);
		System.out.println("json=="+json);
		User javaObject = JSON.toJavaObject(json, User.class);
		System.out.println("javaObject=="+javaObject);
	}
	
	@Test
	public void Test02() throws Exception{
		User rootUser = new User();
		rootUser.setUserName("aavoid");
		rootUser.setPassword("123456");
		rootUser.setUserSex("男");
		rootUser.setUserAddr("长沙");
		rootUser.setScore(100);
		rootUser.setAvatar("无");
		rootUser.setEmail("123456@qq.com");
		rootUser.setUrl("www.baidu.com");
		rootUser.setSignature("哈哈");
		rootUser.setThirdId("1158827539");
		rootUser.setReceiveMsg(false);
		rootUser.setCreateDate(new Date());
		rootUser.setUpdateDate(new Date());
		rootUser.setIsBlock(false);
		rootUser.setStatusCd("1000");
		rootUser.setLoginIp("127.0.1");
		rootUser.setLastLoginIp("127.0.1");
		rootUser.setUserType("0");
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		opsForValue.set("userTest", rootUser.toString());
		logger.debug(opsForValue.get("userTest"));
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void test03() throws Exception{
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		String str = opsForValue.get("user");
		String string = new String(str);
		logger.debug(str);
		logger.debug(string);
		Gson gson = new Gson();
		//String json = gson.toJson(str);
		User fromJson = gson.fromJson(str, new TypeToken<User>() {}.getType());
		logger.debug(fromJson.toString());
	}
	
	@Test
	public void test04() throws Exception{
		String str = "userName:aaa";
		Gson gson = new Gson();
		String json = gson.toJson(str);
		User fromJson = gson.fromJson(json, new TypeToken<User>() {}.getType());
		logger.debug(fromJson.toString());
	}
	
	@Test
	public void test05() throws Exception{
		User rootUser = new User();
		rootUser.setUserName("aavoid");
		rootUser.setPassword("123456");
		rootUser.setUserSex("男");
		rootUser.setUserAddr("长沙");
		rootUser.setScore(100);
		rootUser.setAvatar("无");
		rootUser.setEmail("123456@qq.com");
		rootUser.setUrl("www.baidu.com");
		rootUser.setSignature("哈哈");
		rootUser.setThirdId("1158827539");
		rootUser.setReceiveMsg(false);
		rootUser.setCreateDate(new Date());
		rootUser.setUpdateDate(new Date());
		rootUser.setIsBlock(false);
		rootUser.setStatusCd("1000");
		rootUser.setLoginIp("127.0.1");
		rootUser.setLastLoginIp("127.0.1");
		rootUser.setUserType("0");
		Gson gson = new Gson();
		//对象转json
		//String json = gson.toJson(rootUser);
		String json = JsonUtil.objectToJson(rootUser);
		logger.debug(json);
		//json转对象
		//RootUser fromJson = gson.fromJson(json, RootUser.class);
		User fromJson = JsonUtil.jsonToObject(json, User.class);
		logger.debug(fromJson.toString());
		logger.debug(fromJson.getUserName());
	}
	
	@Test
	public void test06() throws Exception{
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		String string = opsForValue.get("user");
		stringRedisTemplate.delete("user");
		logger.debug(string);
	}
	
	@Test
	public void test07() throws Exception{
		String string = RedisUtil.getString("foo");
		logger.debug(string);
	}
	
	//78d770aa-7c8b-4bab-bdb0-5b72beb8a8bc
	//3f60e78a-7bcb-433a-a015-bb97e70be0df
	//bfa7317d-ff83-4ffa-b999-56805e757182
	//d2aa2b73-a11f-401f-8e72-1eb2604a2997
	//d5493882-93e7-470c-824b-e6f864bb1cca
	@Test
	public void test08() throws Exception{
		String string = UUID.randomUUID().toString();
		logger.debug(string);
		UUID randomUUID = UUID.randomUUID();
		System.out.println(randomUUID);
	}
	
	@Test
	public void test09() throws Exception{
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		String string = opsForValue.get("0b194aea-f94d-451e-8639-d9ff4e53176c");
		logger.debug(string);
	}
	
	@Test
	public void test10() throws Exception{
		String decode = Base64Util.decode("YzkwNDU4YjQ4ZWY0NDNlY2JmM2ZlM2ZkMGQyNDE3NjY=");
		logger.debug(decode);
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		//opsForValue.set("poo", "bool");
		String string = opsForValue.get(decode);
		logger.debug(string);
	}
	
	@Test
	public void test11() throws Exception{
		List<String> list = new ArrayList<String>();
		ListOperations<String, List<String>> opsForList = redisTemplate.opsForList();
		list.add("123");
		list.add("456");
		opsForList.leftPush("redisList", list);
		List<String> leftPop = opsForList.leftPop("redisList");
		logger.debug(leftPop.toString());
	}
	
	@Test
	public void test12() throws Exception{
		List<String> list = new ArrayList<String>();
		Map<String,Object> map = new HashedMap();
		map.put("aa","bb"); 
		HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
		opsForHash.putAll("redisMap", map);
		Map<Object, Object> entries = opsForHash.entries("redisMap");
		List<Object> values = opsForHash.values("redisMap");
		Set<Object> keys = opsForHash.keys("redisMap");
		String get = (String) opsForHash.get("redisMap", "key1");
		logger.debug("entries=="+entries);
		logger.debug("values=="+values);
		logger.debug("keys=="+keys);
		logger.debug("get=="+get);
	}
	
	@Test
	public void test13() {
		HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
		List<Object> values = opsForHash.values("feedback");
		Map<Object, Object> entries = opsForHash.entries("feedback");
		logger.debug(values.toString());
		logger.debug(entries.toString());
	}
	
	@Test
	public void test14() throws Exception {
		String str2 = "周前";
		byte[] bytes2 = str2.getBytes();
		str2 = new String(bytes2,"UTF-8");
		System.out.println(str2);
	}
}
