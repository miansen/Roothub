package wang.miansen.roothub.modules.security.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import wang.miansen.roothub.modules.security.model.Permission;
import org.apache.ibatis.annotations.Param;

/**
 * @author miansen.wang
 * @date 2019年2月26日 下午20:41:45
 */
public interface PermissionDao {

	Permission selectById(@Param("id") Integer id);
	
	Permission selectByName(@Param("name") String name);
	
	/**
	 * 批量查询
	 * @param roleList: 包含多个角色对象的List
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Permission> selectBatchByRoleList(@Param("roleList") Collection<? extends Serializable> roleList,
										   @Param("start") Integer start,@Param("limit") Integer limit);
	
	List<Permission> selectAllByRoleId(@Param("roleId") Integer roleId);
	
	List<Permission> selectAllByPid(@Param("pid") Integer pid,@Param("start") Integer start,@Param("limit") Integer limit);
	
	int insert(Permission permission);
	
	int update(Permission permission);
	
	int deleteById(Integer id);
	
	int deleteByPid(Integer pid);
}
