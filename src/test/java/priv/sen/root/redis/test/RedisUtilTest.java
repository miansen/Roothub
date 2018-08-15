package priv.sen.root.redis.test;

import java.nio.charset.Charset;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.roothub.entity.RootUser;
import cn.roothub.util.CookieAndSessionUtil;
import cn.roothub.util.JsonUtil;
import priv.sen.root.dao.test.BaseTest;

public class RedisUtilTest extends BaseTest{

    private static final Charset UTF_8 = Charset.forName("utf-8");  
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
	
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
		RootUser rootUser = new RootUser();
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
		RootUser javaObject = JSON.toJavaObject(json, RootUser.class);
		System.out.println("javaObject=="+javaObject);
	}
	
	@Test
	public void Test02() throws Exception{
		RootUser rootUser = new RootUser();
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
		RootUser fromJson = gson.fromJson(str, new TypeToken<RootUser>() {}.getType());
		logger.debug(fromJson.toString());
	}
	
	@Test
	public void test04() throws Exception{
		String str = "userName:aaa";
		Gson gson = new Gson();
		String json = gson.toJson(str);
		RootUser fromJson = gson.fromJson(json, new TypeToken<RootUser>() {}.getType());
		logger.debug(fromJson.toString());
	}
	
	@Test
	public void test05() throws Exception{
		RootUser rootUser = new RootUser();
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
		RootUser fromJson = JsonUtil.jsonToObject(json, RootUser.class);
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
}
