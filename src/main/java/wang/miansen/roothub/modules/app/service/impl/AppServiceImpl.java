package wang.miansen.roothub.modules.app.service.impl;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.common.service.impl.AbstractBaseServiceImpl;
import wang.miansen.roothub.common.util.BeanUtils;
import wang.miansen.roothub.modules.app.dao.AppDao;
import wang.miansen.roothub.modules.app.dto.AppDTO;
import wang.miansen.roothub.modules.app.model.App;
import wang.miansen.roothub.modules.app.service.AppService;

/**
 * 应用 Service 实现
 * 
 * @author miansen.wang
 * @date 2020-03-08
 */
@Service
public class AppServiceImpl extends AbstractBaseServiceImpl<App, AppDTO> implements AppService {

	@Autowired
	private AppDao appDao;
	
	@Override
	public Function<? super AppDTO, ? extends App> getDTO2DOMapper() {
		return dto -> (App) BeanUtils.DTO2DO(dto, App.class);
	}

	@Override
	public Function<? super App, ? extends AppDTO> getDO2DTOMapper() {
		return entity -> (AppDTO) BeanUtils.DO2DTO(entity, AppDTO.class);
	}

	@Override
	public BaseDao<App> getDao() {
		return appDao;
	}
	
}
