package wang.miansen.roothub.modules.menu.service.impl;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.common.service.impl.AbstractBaseServiceImpl;
import wang.miansen.roothub.common.util.BeanUtils;
import wang.miansen.roothub.modules.menu.dao.MenuDao;
import wang.miansen.roothub.modules.menu.dto.MenuDTO;
import wang.miansen.roothub.modules.menu.model.Menu;
import wang.miansen.roothub.modules.menu.service.MenuService;

/**
 * 菜单接口实现
 * 
 * @author miansen.wang
 * @date 2020-03-06
 */
@Service
public class MenuServiceImpl extends AbstractBaseServiceImpl<Menu, MenuDTO> implements MenuService {

	@Autowired
	private MenuDao sidebarDao;
	
	@Override
	public Function<? super MenuDTO, ? extends Menu> getDTO2DOMapper() {
		return sidebarDTO -> (Menu) BeanUtils.DTO2DO(sidebarDTO, Menu.class);
	}

	
	@Override
	public Function<? super Menu, ? extends MenuDTO> getDO2DTOMapper() {
		return sidebar -> (MenuDTO) BeanUtils.DO2DTO(sidebar, MenuDTO.class);
	}

	@Override
	public BaseDao<Menu> getDao() {
		return sidebarDao;
	}
	
}
