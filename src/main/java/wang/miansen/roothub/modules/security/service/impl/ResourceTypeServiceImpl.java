package wang.miansen.roothub.modules.security.service.impl;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.common.service.impl.AbstractBaseServiceImpl;
import wang.miansen.roothub.common.util.BeanUtils;
import wang.miansen.roothub.modules.security.dao.ResourceTypeDao;
import wang.miansen.roothub.modules.security.dto.ResourceTypeDTO;
import wang.miansen.roothub.modules.security.model.ResourceType;
import wang.miansen.roothub.modules.security.service.ResourceTypeService;

/**
 * 资源类型 Service Impl
 * 
 * @author miansen.wang
 * @date 2020-03-14
 */
@Service
public class ResourceTypeServiceImpl extends AbstractBaseServiceImpl<ResourceType, ResourceTypeDTO>
		implements ResourceTypeService {

	@Autowired
	private ResourceTypeDao resourceTypeDao;

	@Override
	public Function<? super ResourceTypeDTO, ? extends ResourceType> getDTO2DOMapper() {
		return dto -> (ResourceType) BeanUtils.DTO2DO(dto, ResourceType.class);
	}

	@Override
	public Function<? super ResourceType, ? extends ResourceTypeDTO> getDO2DTOMapper() {
		return entity -> (ResourceTypeDTO) BeanUtils.DO2DTO(entity, ResourceTypeDTO.class);
	}

	@Override
	public BaseDao<ResourceType> getDao() {
		return resourceTypeDao;
	}

}
