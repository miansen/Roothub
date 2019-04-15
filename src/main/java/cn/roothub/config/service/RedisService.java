package cn.roothub.config.service;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import cn.roothub.entity.SystemConfig;
import cn.roothub.service.SystemConfigService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <p>
 * </p>
 * 
 * @author: miansen.wang
 * @date: 2019-03-10
 */
@Component
public class RedisService implements BaseService<JedisPool> {

	private Logger log = LoggerFactory.getLogger(RedisService.class);

	@Autowired
	@Qualifier("systemConfigServiceImpl")
	private SystemConfigService systemConfigService;

	// redis服务地址
	private String host;
	// redis服务端口（默认: 6379）
	private Integer port;
	// redis服务密码
	private String password;
	// 网站连接redis服务超时时间，单位毫秒
	private Integer timeout;
	// 连接池最多空闲实例
	private Integer maxIdle;
	// 连接池最多创建实例
	private Integer maxTotal;
	// 网站连接redis服务的哪个数据库，默认0号数据库，取值范围0-15
	private Integer database;
	// redis服务是否开启认证连接
	private Boolean ssl;
	// 是否开启Redis
	private Boolean openRedis;

	private JedisPool jedisPool;

	static {
		System.out.println("redisService初始化。。。");
	}
	
	
	
	public RedisService() {
		System.out.println("redisService构造器初始化。。。");
	}

	/**
	 * redis连接池初始化
	 */
	@Override
	public JedisPool instance() {
		try {
			if (jedisPool != null)
				return jedisPool;

			if (!redisConfig()) {
				log.info("redis配置信息不全或没有配置...");
				return null;
			}

			JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			// 配置jedis连接池最多空闲多少个实例
			jedisPoolConfig.setMaxIdle(this.maxIdle);
			// 配置jedis连接池最多创建多少个实例
			jedisPoolConfig.setMaxTotal(this.maxTotal);
			jedisPool = new JedisPool(jedisPoolConfig, this.host, this.port, this.timeout, this.password, this.database,
					null, this.ssl);
			log.info("redis连接对象获取成功...");
			return jedisPool;

		} catch (Exception e) {
			log.error("配置redis连接池报错，错误信息: {}", e.getMessage());
			return null;
		}
	}

	/**
	 * 判断是否配置了redis并初始化参数
	 * 
	 * @return
	 */
	public boolean redisConfig() {
		List<SystemConfig> redisConfigs = systemConfigService.getBatchKeys(Arrays.asList("redis_host", "redis_port",
				"open_redis", "redis_timeout", "redis_max_idle", "redis_max_total", "redis_database", "redis_ssl"));
		this.host = redisConfigs.get(0).getValue();
		this.port = Integer.parseInt(redisConfigs.get(1).getValue());
		this.openRedis = redisConfigs.get(7).getValue().equals("1");
		this.timeout = Integer.parseInt(redisConfigs.get(2).getValue());
		this.maxIdle = Integer.parseInt(redisConfigs.get(3).getValue());
		this.maxTotal = Integer.parseInt(redisConfigs.get(4).getValue());
		this.database = Integer.parseInt(redisConfigs.get(5).getValue());
		this.ssl = redisConfigs.get(6).getValue().equals("1");
		return !StringUtils.isEmpty(host) && !StringUtils.isEmpty(port) && !StringUtils.isEmpty(timeout)
				&& !StringUtils.isEmpty(maxIdle) && !StringUtils.isEmpty(maxTotal) && !StringUtils.isEmpty(database)
				&& !StringUtils.isEmpty(ssl) && openRedis;
	}

	/**
	 * 获取Jedis客户端
	 * 
	 * @return
	 */
	public Jedis getJedis() {
		try {
			Jedis jedis = null;
			jedisPool = instance();
			if (jedisPool != null) {
				jedis = jedisPool.getResource();
			}
			return jedis;
		} catch (Exception e) {
			log.error("获取Jedis客户端报错，错误信息: {}", e.getMessage());
			return null;
		}
	}

	/**
	 * 获取String值
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		Jedis jedis = getJedis();
		if (StringUtils.isEmpty(key) || jedis == null)
			return null;
		String value = jedis.get(key);
		jedis.close();
		return value;
	}

	/**
	 * 设置String
	 * 
	 * @param key
	 * @param value
	 */
	public void setString(String key, String value) {
		Jedis jedis = getJedis();
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value) || jedis == null)
			return;
		jedis.set(key, value);
		jedis.close();
	}

	/**
	 * 删除String值
	 * 
	 * @param key
	 */
	public void delString(String key) {
		Jedis jedis = getJedis();
		if (StringUtils.isEmpty(key) || jedis == null)
			return;
		jedis.del(key);
		jedis.close();

	}

}
