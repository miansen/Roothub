package priv.sen.root.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTool {

	//redis服务器地址
	private static String address = "localhost";
	
	//redis服务器端口号
	private static int port = 6379;
	
	 //访问密码  
    private static String auth = null;  
    //可用连接实例的最大数目，默认值为8；  
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。  
    //private static final int MAX_ACTIVE = 1024;  
      
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。  
    private static int MAX_IDLE = 10;  
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；  
    private static int MAX_WAIT = 10000;  
    private static int TIMEOUT = 10000;  
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
    private static boolean TEST_ON_BORROW = true;  
    private static JedisPool jedisPool;  
      
    static {  
        JedisPoolConfig config = new JedisPoolConfig();  
        config.setMaxIdle(MAX_IDLE);  
        config.setMaxWaitMillis(MAX_WAIT);  
        config.setTestOnBorrow(TEST_ON_BORROW);  
        //config.setMaxTotal(MAX_ACTIVE);  
        jedisPool = new JedisPool(config, address, port, TIMEOUT);  
    }  
      
    /** 
     * 获取Jedis客户端 
     * @return 
     */  
    public static Jedis getJedis() {  
        Jedis jedis = null;  
        if(null != jedisPool) {  
            jedis = jedisPool.getResource();  
        }  
        return jedis;  
    }  
      
    /** 
     * 返还资源 
     * @param jedis 
     */  
    public static void returnResource(Jedis jedis) {  
        jedisPool.returnBrokenResource(jedis);  
    }  
}
