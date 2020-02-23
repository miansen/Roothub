package wang.miansen.roothub.modules.role.service.impl;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.common.service.impl.AbstractBaseServiceImpl;
import wang.miansen.roothub.common.util.BeanUtils;
import wang.miansen.roothub.modules.role.dao.RolePermissionRelDao;
import wang.miansen.roothub.modules.role.dto.RolePermissionRelDTO;
import wang.miansen.roothub.modules.role.model.RolePermissionRel;
import wang.miansen.roothub.modules.role.service.RolePermissionRelService;

/**
 * 角色权限关联关系 Service Impl
 * 
 * @author miansen.wang
 * @date 2020-02-23
 */
@Service
public class RolePermissionRelServiceImpl extends AbstractBaseServiceImpl<RolePermissionRel, RolePermissionRelDTO>
		implements RolePermissionRelService {

	@Autowired
	private RolePermissionRelDao rolePermissionRelDao;

	@Override
	public Function<? super RolePermissionRelDTO, ? extends RolePermissionRel> getDTO2DOMapper() {
		return dto -> (RolePermissionRel) BeanUtils.DTO2DO(dto, RolePermissionRel.class);
	}

	@Override
	public Function<? super RolePermissionRel, ? extends RolePermissionRelDTO> getDO2DTOMapper() {
		return entity -> (RolePermissionRelDTO) BeanUtils.DO2DTO(entity, RolePermissionRelDTO.class);
	}

	@Override
	public BaseDao<RolePermissionRel> getDao() {
		return rolePermissionRelDao;
	}

}
