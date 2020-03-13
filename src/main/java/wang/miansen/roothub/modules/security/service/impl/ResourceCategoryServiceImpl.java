package wang.miansen.roothub.modules.security.service.impl;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.common.service.impl.AbstractBaseServiceImpl;
import wang.miansen.roothub.common.util.BeanUtils;
import wang.miansen.roothub.modules.security.dao.ResourceCategoryDao;
import wang.miansen.roothub.modules.security.dto.ResourceCategoryDTO;
import wang.miansen.roothub.modules.security.model.ResourceCategory;
import wang.miansen.roothub.modules.security.service.ResourceCategoryService;

/**
 * 资源类别 Service Impl
 * 
 * @author miansen.wang
 * @date 2020-03-13
 */
@Service
public class ResourceCategoryServiceImpl extends AbstractBaseServiceImpl<ResourceCategory, ResourceCategoryDTO>
		implements ResourceCategoryService {

	@Autowired
	private ResourceCategoryDao resourceCategoryDao;

	@Override
	public Function<? super ResourceCategoryDTO, ? extends ResourceCategory> getDTO2DOMapper() {
		return dto -> (ResourceCategory) BeanUtils.DTO2DO(dto, ResourceCategory.class);
	}

	@Override
	public Function<? super ResourceCategory, ? extends ResourceCategoryDTO> getDO2DTOMapper() {
		return entity -> (ResourceCategoryDTO) BeanUtils.DO2DTO(entity, ResourceCategoryDTO.class);
	}

	@Override
	public BaseDao<ResourceCategory> getDao() {
		return resourceCategoryDao;
	}

}
