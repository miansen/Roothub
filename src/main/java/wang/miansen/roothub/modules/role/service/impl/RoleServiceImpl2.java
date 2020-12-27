package wang.miansen.roothub.modules.role.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.service.impl.AbstractBaseServiceImpl;
import wang.miansen.roothub.common.util.BeanUtils;
import wang.miansen.roothub.common.util.CollectionUtils;
import wang.miansen.roothub.modules.permission.dto.PermissionDTO;
import wang.miansen.roothub.modules.permission.service.PermissionBakService;
import wang.miansen.roothub.modules.role.dao.RoleDao;
import wang.miansen.roothub.modules.role.dto.RoleDTO;
import wang.miansen.roothub.modules.role.dto.RolePermissionRelDTO;
import wang.miansen.roothub.modules.role.model.Role;
import wang.miansen.roothub.modules.role.model.RolePermissionRel;
import wang.miansen.roothub.modules.role.service.RolePermissionRelService;
import wang.miansen.roothub.modules.role.service.RoleService2;

/**
 * Role Service Impl
 * 
 * @author miansen.wang
 * @date 2020-02-23
 */
@Service
public class RoleServiceImpl2 extends AbstractBaseServiceImpl<Role, RoleDTO> implements RoleService2 {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PermissionBakService permissionService;

	@Autowired
	private RolePermissionRelService rolePermissionRelService;

	@Override
	public Function<? super RoleDTO, ? extends Role> getDTO2DOMapper() {
		return roleDTO -> (Role) BeanUtils.DTO2DO(roleDTO, Role.class);
	}

	@Override
	public Function<? super Role, ? extends RoleDTO> getDO2DTOMapper() {
		return role -> {
			RoleDTO roleDTO = (RoleDTO) BeanUtils.DO2DTO(role, RoleDTO.class);
			if (roleDTO != null) {
				QueryWrapper<RolePermissionRel> queryWrapper = new QueryWrapper<>();
				queryWrapper.eq("role_id", roleDTO.getRoleId());
				List<RolePermissionRelDTO> rolePermissionRelDTOs = this.rolePermissionRelService.list(queryWrapper);
				List<String> permissionIds = rolePermissionRelDTOs.stream().filter(Objects::nonNull)
						.map(dto -> dto.getPermissionId()).collect(Collectors.toList());
				if (CollectionUtils.isNotEmpty(permissionIds)) {
					List<PermissionDTO> permissionDTOs = this.permissionService.listBatchIds(permissionIds);
					roleDTO.setPermissionDTOs(permissionDTOs);
				}
			}
			return roleDTO;
		};
	}

	@Override
	public BaseDao<Role> getDao() {
		return roleDao;
	}

}
