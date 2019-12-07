package wang.miansen.roothub.modules.security.dao;

import java.util.List;

import wang.miansen.roothub.modules.security.model.AdminUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author miansen.wang
 * @date 2019年2月26日 下午1:24:17
 */
public interface AdminUserDao {
	
	// 根据用户名查询后台用户
	AdminUser selectByName(@Param("name") String name);
	
	// 根据ID查询后台用户
	AdminUser selectById(@Param("id") Integer id);
	
	// 查询所有的后台用户
	List<AdminUser> selectAll(@Param("start") Integer start,@Param("limit") Integer limit);
	
	// 新增后台用户
	int insert(AdminUser adminUser);
	
	// 更新后台用户
	int update(AdminUser adminUser);
	
	// 根据ID删除后台用户
	int deleteById(@Param("id") Integer id);
	
	// 统计所有后台用户
	int countAll();
	
}
