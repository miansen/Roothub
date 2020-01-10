package wang.miansen.roothub.common.dao.mapper.wrapper.conditions;

/**
 * 分页接口
 * 
 * @param <R> 返回结果的类型
 * 
 * @author miansen.wang
 * @date 2020-01-05
 * @since 3.0
 */
public interface Page<R> {

	/**
	 * limit 语句
	 * 
	 * <p>例：limit("0", "10") -> limit 0,10
	 * <p>注意：仅支持 MySQL 数据库
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	R limit(Integer start, Integer end);
	
}
