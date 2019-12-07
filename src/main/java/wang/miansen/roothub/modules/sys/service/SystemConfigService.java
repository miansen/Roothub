package wang.miansen.roothub.modules.sys.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import wang.miansen.roothub.modules.sys.model.SystemConfig;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-07
 */
public interface SystemConfigService {

	SystemConfig getById(Integer id);
	
	SystemConfig getByKey(String key);
	
	/**
	 * 获取上传配置
	 * @return
	 */
	Map<String,Object> getUploadConfig();
	
	/**
	 * 获取所有的系统配置
	 * @return
	 */
	List<SystemConfig> getAllList();
	
	/**
	 * 根据pid获取子节点
	 * @param pid
	 * @return
	 */
	List<SystemConfig> getByPid(Integer pid);
	
	/**
	 * 获取所有的系统配置，格式为Map，key是父节点的description，value是对应的子节点
	 * @return
	 */
	Map<String,Object> getAllMap();
	
	/**
	 * 批量查询
	 * @param keys
	 * @return
	 */
	List<SystemConfig> getBatchKeys(Collection<? extends Serializable> keys);
	
	List<SystemConfig> edit(Integer pid);
	
	void update(SystemConfig systemConfig);
	
	void update(String key,String value);
	
	void update(List<Map<String, String>> list);
	
	/**
	 * 更新上传配置
	 * @param pid
	 */
	void updateUploadConfig(Integer id);
	
	Integer getAge();
	
	void setAge(Integer age);
}
