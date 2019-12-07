package wang.miansen.roothub.modules.sys.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import wang.miansen.roothub.modules.sys.model.SystemConfig;
import wang.miansen.roothub.modules.sys.service.SystemConfigService;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-04-02
 */
public abstract class AbstractUploadConfig implements SystemConfigService {

	@Override
	public SystemConfig getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemConfig getByKey(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getUploadConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SystemConfig> getAllList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SystemConfig> getByPid(Integer pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getAllMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SystemConfig> getBatchKeys(Collection<? extends Serializable> keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SystemConfig> edit(Integer pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SystemConfig systemConfig) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String key, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(List<Map<String, String>> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUploadConfig(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getAge() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAge(Integer age) {
		// TODO Auto-generated method stub
		
	}

}
