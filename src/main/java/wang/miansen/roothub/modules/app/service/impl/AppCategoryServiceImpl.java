package wang.miansen.roothub.modules.app.service.impl;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.common.service.impl.AbstractBaseServiceImpl;
import wang.miansen.roothub.common.util.BeanUtils;
import wang.miansen.roothub.modules.app.dao.AppCategoryDao;
import wang.miansen.roothub.modules.app.dto.AppCategoryDTO;
import wang.miansen.roothub.modules.app.model.AppCategory;
import wang.miansen.roothub.modules.app.service.AppCategoryService;

/**
 * 应用类别 Service Impl
 * 
 * @author miansen.wang
 * @date 2020-03-08
 */
@Service
public class AppCategoryServiceImpl extends AbstractBaseServiceImpl<AppCategory, AppCategoryDTO> implements AppCategoryService {

	@Autowired
	private AppCategoryDao appCategoryDao;
	
	@Override
	public Function<? super AppCategoryDTO, ? extends AppCategory> getDTO2DOMapper() {
		return dto -> (AppCategory) BeanUtils.DTO2DO(dto, AppCategory.class);
	}

	@Override
	public Function<? super AppCategory, ? extends AppCategoryDTO> getDO2DTOMapper() {
		return entity -> (AppCategoryDTO) BeanUtils.DO2DTO(entity, AppCategoryDTO.class);
	}

	@Override
	public BaseDao<AppCategory> getDao() {
		return appCategoryDao;
	}

}
