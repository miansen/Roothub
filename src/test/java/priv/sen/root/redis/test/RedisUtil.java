package priv.sen.root.redis.test;

import java.nio.charset.Charset;

import redis.clients.jedis.Jedis;

public class RedisUtil {

	private static final Charset UTF_8 = Charset.forName("utf-8");  
	  
    /** 
     * 向redis新增字符串键值对 
     * @param key 
     * @param value 
     */  
    public static boolean setString(String key, String value) {  
        if(null == key || value == null ) {  
            return false;  
        }  
          
        return setBytes(key.getBytes(UTF_8), value.getBytes(UTF_8));  
    }  
      
    /** 
     * 向Redis中储存键值对的byte数组，最长不能超过1GB的字节 
     * @param key 键 
     * @param value 值 
     * @return 
     */  
    public static boolean setBytes(byte[] key, byte[] value) {  
        if(null == key || null == value) {  
            return false;  
        }  
          
        Jedis jedis = RedisTool.getJedis();  
        String statusCode = jedis.set(key, value);  
        System.out.println("状态码:(" + statusCode + ")");  
        RedisTool.returnResource(jedis);  
        return true;  
    }  
      
    /** 
     * 获取String类型的值 
     * @param key 键的值 
     * @return 
     */  
    public static String getString(String key) {  
        if(null == key) {  
            return null;  
        }  
          
        byte[] val = getBytes(key.getBytes(UTF_8));  
        if(val == null) {  
            return null;  
        }  
        return new String(val, UTF_8);  
    }  
      
    /** 
     * 获取Redis中的缓存值 
     * @param key 
     * @return 
     */  
    public static byte[] getBytes(byte[] key) {  
        if(null == key) {  
            return null;  
        }  
          
        Jedis jedis = RedisTool.getJedis();  
        byte[] val = jedis.get(key);  
        RedisTool.returnResource(jedis);  
        return val;  
    }  
      
    /** 
     * 删除某个键，如果键被删除，再次请求相同键时，返回null 
     * @param key 
     */  
    private static boolean del(byte[] key) {  
        if(null == key) {  
            return true;  
        }  
          
        Jedis jedis = RedisTool.getJedis();  
        jedis.del(key);  
        return true;  
    }  
      
    /** 
     * 操作字符串类型(String),删除键 
     * @param key 
     * @return 
     */  
    public static boolean delString(String key) {  
        if(null == key) {  
            return true;  
        }  
          
        byte[] k = key.getBytes(UTF_8);  
        return del(k);  
    }  
      
}
