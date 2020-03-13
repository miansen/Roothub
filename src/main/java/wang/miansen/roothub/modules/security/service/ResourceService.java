package wang.miansen.roothub.modules.security.service;

import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.modules.security.dto.ResourceDTO;
import wang.miansen.roothub.modules.security.model.Resource;

/**
 * 资源 Service
 * 
 * @author miansen.wang
 * @date 2020-03-13
 */
public interface ResourceService extends BaseService<Resource, ResourceDTO> {

	/**
	 * 根据资源名称或者资源类别名称查找资源
	 * 
	 * @param pageNumber 第几页
	 * @param resourceName 资源名称
	 * @param resourceCategoryName 资源类别名称
	 * @return 资源 Page
	 */
	Page<ResourceDTO> pageByNameOrCategoryName(Integer pageNumber, String resourceName, String resourceCategoryName);
	
}
