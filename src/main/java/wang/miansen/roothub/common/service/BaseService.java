package wang.miansen.roothub.common.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.dao.mapper.wrapper.update.UpdateWrapper;

/**
 * 该接口作为 Service 层的基础接口，定义了常用的业务增删改查方法，建议大部分的 Service 层接口继承。
 * 
 * @param <T> 数据库表映射实体类
 * 
 * @author: miansen.wang
 * @date: 2019-12-29
 * @since 3.0
 */
public interface BaseService<T> {

	/**
	 * 插入一条数据
	 * @param entity 实体对象
	 * @return boolean
	 */
	boolean save(T entity);

	/**
	 * 批量插入多条数据
	 * @param entityList 实体对象集合
	 * @return boolean
	 */
	boolean saveBatch(Collection<T> entityList);

	/**
	 * 删除满足条件的数据
	 * @param updateWrapper where 条件包装器
	 * @return boolean
	 */
	boolean remove(UpdateWrapper<T> updateWrapper);

	/**
	 * 根据 ID 删除一条数据
	 * @param id 主键 ID
	 * @return boolean
	 */
	boolean removeById(Serializable id);

	/**
	 * 根据 ID 集合，批量删除多条数据
	 * @param ids 主键 ID 集合
	 * @return boolean
	 */
	boolean removeBatchIds(Collection<? extends Serializable> ids);

	/**
	 * 更新满足条件的数据
	 * @param updateWrapper where 条件包装器
	 * @return boolean
	 */
	boolean update(T entity, UpdateWrapper<T> updateWrapper);

	/**
	 * 根据 ID 更新一条数据
	 * @param entity 实体对象
	 * @return boolean
	 */
	boolean updateById(T entity);

	/**
	 * 根据 ID 集合，批量更新多条数据
	 * @param entityList 实体对象集合
	 * @return boolean
	 */
	boolean updateBatch(Collection<T> entityList);

	/**
	 * 根据 ID 查询一条数据
	 * @param id 主键 ID
	 * @return T
	 */
	T getById(Serializable id);

	/**
	 * 查询满足条件的一条数据
	 * @param queryWrapper where 条件包装器
	 * @return T
	 */
	T getOne(QueryWrapper<T> queryWrapper);

	/**
	 * 查询总记录数
	 * @return Integer
	 */
	Integer count();

	/**
	 * 查询满足条件的总记录数
	 * @param queryWrapper where 条件包装器
	 * @return Integer
	 */
	Integer count(QueryWrapper<T> queryWrapper);

	/**
	 * 查询所有的数据
	 * @return List
	 */
	List<T> list();

	/**
	 * 查询满足条件的多条数据
	 * @param queryWrapper where 条件包装器
	 * @return List
	 */
	List<T> list(QueryWrapper<T> queryWrapper);

	/**
	 * 根据 ID 集合，批量查询多条数据
	 * @param ids 主键 ID 集合
	 * @return List
	 */
	List<T> listByIds(Collection<? extends Serializable> ids);
	
}
