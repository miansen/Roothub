package wang.miansen.roothub.common.util;

import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.entity.BaseDO;
import wang.miansen.roothub.common.service.BaseService;

/**
 * @author miansen.wang
 * @date 2020-01-04
 */
public class BeanUtils {

	public static <T, R> R conver(T t, Class<R> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void id2DTO(Object source, String propertyName, String id, Class<BaseService<BaseDO, BaseDTO>> serviceClass) {
		BaseService<BaseDO, BaseDTO> service = ApplicationContextUtil.getBean(serviceClass);
		BaseDTO dto = service.getById(id);
	}
}
