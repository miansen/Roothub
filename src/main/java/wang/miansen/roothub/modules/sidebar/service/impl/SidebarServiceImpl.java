package wang.miansen.roothub.modules.sidebar.service.impl;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.common.service.impl.AbstractBaseServiceImpl;
import wang.miansen.roothub.common.util.BeanUtils;
import wang.miansen.roothub.modules.sidebar.dao.SidebarDao;
import wang.miansen.roothub.modules.sidebar.dto.SidebarDTO;
import wang.miansen.roothub.modules.sidebar.model.Sidebar;
import wang.miansen.roothub.modules.sidebar.service.SidebarService;

/**
 * @author miansen.wang
 * @date 2020-03-06
 */
@Service
public class SidebarServiceImpl extends AbstractBaseServiceImpl<Sidebar, SidebarDTO> implements SidebarService {

	@Autowired
	private SidebarDao sidebarDao;
	
	@Override
	public Function<? super SidebarDTO, ? extends Sidebar> getDTO2DOMapper() {
		return sidebarDTO -> (Sidebar) BeanUtils.DTO2DO(sidebarDTO, Sidebar.class);
	}

	
	@Override
	public Function<? super Sidebar, ? extends SidebarDTO> getDO2DTOMapper() {
		return sidebar -> (SidebarDTO) BeanUtils.DO2DTO(sidebar, SidebarDTO.class);
	}

	@Override
	public BaseDao<Sidebar> getDao() {
		return sidebarDao;
	}
	
}
