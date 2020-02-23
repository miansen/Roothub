package wang.miansen.roothub.modules.permission.service.impl;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.common.service.impl.AbstractBaseServiceImpl;
import wang.miansen.roothub.common.util.BeanUtils;
import wang.miansen.roothub.modules.permission.dao.PermissionDao;
import wang.miansen.roothub.modules.permission.dto.PermissionDTO;
import wang.miansen.roothub.modules.permission.model.Permission;
import wang.miansen.roothub.modules.permission.service.PermissionService;

/**
 * 权限 Service Impl
 * 
 * @author miansen.wang
 * @date 2020-02-23
 */
@Service
public class PermissionServiceImpl extends AbstractBaseServiceImpl<Permission, PermissionDTO>
		implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public Function<? super PermissionDTO, ? extends Permission> getDTO2DOMapper() {
		return permissionDTO -> (Permission) BeanUtils.DTO2DO(permissionDTO, Permission.class);
	}

	@Override
	public Function<? super Permission, ? extends PermissionDTO> getDO2DTOMapper() {
		return permission -> (PermissionDTO) BeanUtils.DO2DTO(permission, PermissionDTO.class);
	}

	@Override
	public BaseDao<Permission> getDao() {
		return permissionDao;
	}

}
