package wang.miansen.roothub.modules.tab.service.impl;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.common.service.impl.AbstractBaseServiceImpl;
import wang.miansen.roothub.modules.post.service.TabService;
import wang.miansen.roothub.modules.tab.dao.TabDao;
import wang.miansen.roothub.modules.tab.dto.TabDTO;
import wang.miansen.roothub.modules.tab.model.Tab;
import wang.miansen.roothub.modules.user.model.User;

@Service
public class TabServiceImpl extends AbstractBaseServiceImpl<Tab, TabDTO> implements TabService{

	@Autowired
	private TabDao tabDao;
	
	/**
	 * 查询所有板块
	 */
	@Override
	public List<Tab> selectAll() {
		return tabDao.selectAll();
	}
	
	@Override
	public Function<? super TabDTO, ? extends Tab> getDTO2DOMapper() {
		return tabDTO -> {
			Tab tab = new Tab();
			BeanUtils.copyProperties(tabDTO, tab);
			return tab;
		};
	}
	
	@Override
	public Function<? super Tab, ? extends TabDTO> getDO2DTOMapper() {
		return tab -> {
			TabDTO tabDTO = new TabDTO();
			BeanUtils.copyProperties(tab, tabDTO);
			return tabDTO;
		};
	}

	@Override
	public BaseDao<Tab> getDao() {
		return tabDao;
	}

}
