package wang.miansen.roothub.third.service;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-10
 */
public interface BaseService<T> {

	/**
	 * 外接服务初始化实例方法
	 * @return
	 */
	T instance();
}
