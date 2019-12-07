package wang.miansen.roothub.modules.security.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import wang.miansen.roothub.modules.security.model.AdminUserRoleRel;

/**
 * @author miansen.wang
 * @date 2019年2月28日 上午10:28:26
 */
public interface AdminUserRoleRelDao {

	/**
	 * 根据后台用户ID查询所有的关联关系
	 * @param adminUserId
	 * @return
	 */
	List<AdminUserRoleRel> selectAllByAdminUserId(@Param("adminUserId") Integer adminUserId);
	
	/**
	 * 根据角色ID查询所有的关联关系
	 * @param roleId
	 * @return
	 */
	List<AdminUserRoleRel> selectAllByRoleId(@Param("roleId") Integer roleId);
	
	/**
	 * 批量新增后台用户与角色的关联关系
	 * @param adminUserRoleRels: AdminUserRoleRel实例列表（不能为null以及empty）
	 * @return
	 */
	int insertBatch(@Param("adminUserRoleRels") Collection<? extends Serializable> adminUserRoleRels);
	
	/**
	 * 删除后台用户与角色的关联关系
	 * @param adminUserId: 后台用户ID
	 * @return
	 */
	int deleteByAdminUserId(@Param("adminUserId") Integer adminUserId);
	
	/**
	 * 删除后台用户与角色的关联关系
	 * @param roleId: 角色ID
	 * @return
	 */
	int deleteByRoleId(@Param("roleId") Integer roleId);
}
