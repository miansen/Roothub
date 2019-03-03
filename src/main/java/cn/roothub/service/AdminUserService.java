package cn.roothub.service;

import java.util.List;
import java.util.Map;
import cn.roothub.dto.PageDataBody;
import cn.roothub.entity.AdminUser;
import cn.roothub.entity.Role;

/**
 * @author miansen.wang
 * @date 2019年2月26日 下午22:21:57
 */
public interface AdminUserService {

	// 根据用户名获取后台用户
	AdminUser getByName(String name);
	
	// 根据ID获取后台用户
	AdminUser getById(Integer id);
	
	// 获取所有的后台用户
	PageDataBody<AdminUser> page(Integer pageNumber, Integer pageSize);
	
	// 获取所有的后台用户以及对应的角色列表
	PageDataBody<AdminUser> pageRoles(Integer pageNumber, Integer pageSize);
	
	// 新增后台用户
	AdminUser save(AdminUser adminUser);
	
	// 更新后台用户
	void update(AdminUser adminUser);
	
	// 删除后台用户
	void removeById(Integer id);
	
	// 统计所有后台用户
	int countAll();
}
