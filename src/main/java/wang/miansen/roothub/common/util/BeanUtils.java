package wang.miansen.roothub.common.util;

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
}
