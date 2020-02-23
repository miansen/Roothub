package wang.miansen.roothub.modules.user.service.impl;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.common.service.impl.AbstractBaseServiceImpl;
import wang.miansen.roothub.common.util.BeanUtils;
import wang.miansen.roothub.modules.user.dao.UserRoleRelDao;
import wang.miansen.roothub.modules.user.dto.UserRoleRelDTO;
import wang.miansen.roothub.modules.user.model.UserRoleRel;
import wang.miansen.roothub.modules.user.service.UserRoleRelService;

/**
 * 用户和角色多对多关联 Service Impl
 * 
 * @author miansen.wang
 * @date 2020-02-23
 */
@Service
public class UserRoleRelServiceImpl extends AbstractBaseServiceImpl<UserRoleRel, UserRoleRelDTO>
		implements UserRoleRelService {

	@Autowired
	private UserRoleRelDao userRoleRelDao;

	@Override
	public Function<? super UserRoleRelDTO, ? extends UserRoleRel> getDTO2DOMapper() {
		return dto -> (UserRoleRel) BeanUtils.DTO2DO(dto, UserRoleRel.class);
	}

	@Override
	public Function<? super UserRoleRel, ? extends UserRoleRelDTO> getDO2DTOMapper() {
		return entity -> (UserRoleRelDTO) BeanUtils.DO2DTO(entity, UserRoleRelDTO.class);
	}

	@Override
	public BaseDao<UserRoleRel> getDao() {
		return userRoleRelDao;
	}

}
