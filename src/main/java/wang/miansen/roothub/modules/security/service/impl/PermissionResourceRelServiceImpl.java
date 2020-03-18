package wang.miansen.roothub.modules.security.service.impl;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.common.service.impl.AbstractBaseServiceImpl;
import wang.miansen.roothub.common.util.BeanUtils;
import wang.miansen.roothub.modules.security.dao.PermissionResourceRelDao;
import wang.miansen.roothub.modules.security.dto.PermissionResourceRelDTO;
import wang.miansen.roothub.modules.security.model.PermissionResourceRel;
import wang.miansen.roothub.modules.security.service.PermissionResourceRelService;

/**
 * 权限与资源多对多关联关系实现类
 * 
 * @author miansen.wang
 * @date 2020-03-18
 */
@Service
public class PermissionResourceRelServiceImpl
		extends AbstractBaseServiceImpl<PermissionResourceRel, PermissionResourceRelDTO>
		implements PermissionResourceRelService {

	@Autowired
	private PermissionResourceRelDao permissionResourceRelDao;

	@Override
	public Function<? super PermissionResourceRelDTO, ? extends PermissionResourceRel> getDTO2DOMapper() {
		return dto -> (PermissionResourceRel) BeanUtils.DTO2DO(dto, PermissionResourceRel.class);
	}

	@Override
	public Function<? super PermissionResourceRel, ? extends PermissionResourceRelDTO> getDO2DTOMapper() {
		return entity -> (PermissionResourceRelDTO) BeanUtils.DO2DTO(entity, PermissionResourceRelDTO.class);
	}

	@Override
	public BaseDao<PermissionResourceRel> getDao() {
		return permissionResourceRelDao;
	}

}
