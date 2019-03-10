package cn.roothub.config.service;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import cn.roothub.entity.SystemConfig;
import cn.roothub.service.SystemConfigService;
import redis.clients.jedis.JedisPool;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-10
 */
@Component
public class RedisService implements BaseService<JedisPool>{

	private Logger log = LoggerFactory.getLogger(RedisService.class);
	
	@Autowired
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
	
	public RedisService() {
		/*List<SystemConfig> redisConfigs = systemConfigService.getBatchKeys(
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

		this.host = Integer.parseInt(redisConfigs.get(0).getValue());
		this.port = Integer.parseInt(redisConfigs.get(1).getValue());
		this.password = Integer.parseInt(redisConfigs.get(2).getValue());
		this.timeout = Integer.parseInt(redisConfigs.get(3).getValue());
		this.maxIdle = Integer.parseInt(redisConfigs.get(4).getValue());
		this.maxTotal = Integer.parseInt(redisConfigs.get(5).getValue());
		this.database = Integer.parseInt(redisConfigs.get(6).getValue());
		this.ssl = redisConfigs.get(6).getValue().equals("1");*/
	}

	/**
	 * redis连接池初始化
	 */
	@Override
	public JedisPool instance() {
		if(redisConfig()) {
			log.debug("redis配置参数正确");
			log.debug(host.toString());
			log.debug(port.toString());
			log.debug(password.toString());
			log.debug(timeout.toString());
			log.debug(maxIdle.toString());
			log.debug(maxTotal.toString());
			log.debug(database.toString());
			log.debug(ssl.toString());
			return null;
		}
		return null;
	}

	/**
	 * 判断是否配置了redis
	 * @return
	 */
	public boolean redisConfig() {
		List<SystemConfig> redisConfigs = systemConfigService.getBatchKeys(Arrays.asList("redis_host","redis_port","redis_password","redis_timeout"));
		this.host = redisConfigs.get(0).getValue();
		this.port = Integer.parseInt(redisConfigs.get(1).getValue());
		this.password = redisConfigs.get(2).getValue();
		this.timeout = Integer.parseInt(redisConfigs.get(3).getValue());
		this.maxIdle = Integer.parseInt(redisConfigs.get(3).getValue());
		this.maxTotal = Integer.parseInt(redisConfigs.get(3).getValue());
		this.database = Integer.parseInt(redisConfigs.get(3).getValue());
		this.ssl = redisConfigs.get(3).getValue().equals("1");
		return !StringUtils.isEmpty(host) 
				&& !StringUtils.isEmpty(port) 
				&& !StringUtils.isEmpty(password) 
				&& !StringUtils.isEmpty(timeout)
				&& !StringUtils.isEmpty(maxIdle)
				&& !StringUtils.isEmpty(maxTotal)
				&& !StringUtils.isEmpty(database)
				&& !StringUtils.isEmpty(ssl);
	}

}
