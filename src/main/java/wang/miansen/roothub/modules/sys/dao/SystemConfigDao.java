package wang.miansen.roothub.modules.sys.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import wang.miansen.roothub.modules.sys.model.SystemConfig;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-07
 */
public interface SystemConfigDao {

	SystemConfig selectById(@Param("id") Integer id);
	
	SystemConfig selectByKey(@Param("key") String key);
	
	List<SystemConfig> selectAll();
	
	List<SystemConfig> selectByPid(@Param("pid") Integer pid);
	
	List<SystemConfig> selectBatchKeys(@Param("keys") Collection<? extends Serializable> keys);
	
	/**
	 * 根据key更新value
	 * @param systemConfig
	 * @return
	 */
	int update(SystemConfig systemConfig);
}
