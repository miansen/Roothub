package wang.miansen.roothub.common.dao;

import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.dao.mapper.wrapper.update.UpdateWrapper;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 该接口作为 Dao 层的基础接口，定义了常用的数据库增删改查方法，建议大部分的 Dao 层接口继承。
 * <p>继承该接口后即可拥有简单的 CRUD 能力。
 * 
 * @param <T> 数据库表映射实体类
 *
 * @author miansen.wang
 * @date 2019/8/19 22:44
 * @since 3.0
 */
public interface BaseDao<T> {

	/**
	 * 插入一条数据
	 * @param model 实体对象
	 * @return int
	 */
	int insert(@Param("model") T model);

	/**
	 * 删除满足条件的数据
	 * @param updateWrapper where 条件包装器
	 * @return int
	 */
	int delete(@Param("wrapper") UpdateWrapper<T> updateWrapper);

	/**
	 * 根据 ID 删除一条数据
	 * @param id 主键 ID
	 * @return int
	 */
	int deleteById(Serializable id);

	/**
	 * 根据 ID 集合，批量删除多条数据
	 * @param ids 主键 ID 集合
	 * @return int
	 */
	int deleteBatchIds(@Param("ids") Collection<? extends Serializable> ids);

	/**
	 * 更新满足条件的数据
	 * @param model 实体对象
	 * @param updateWrapper where 条件包装器
	 * @return int
	 */
	int update(@Param("model") T model, @Param("wrapper") UpdateWrapper<T> updateWrapper);

	/**
	 * 根据 ID 更新一条数据
	 * @param model 实体对象
	 * @return int
	 */
	int updateById(@Param("model") T model);

	/**
	 * 根据 ID 查询一条数据
	 * @param id 主键 ID
	 * @return T
	 */
	T selectById(Serializable id);

	/**
	 * 根据 ID 集合，批量查询多条数据
	 * @param ids 主键 ID 集合
	 * @return List<T>
	 */
	List<T> selectBatchIds(@Param("ids") Collection<? extends Serializable> ids);

	/**
	 * 查询满足条件的一条数据
	 * @param queryWrapper where 条件包装器
	 * @return T
	 */
	T selectOne(@Param("wrapper") QueryWrapper<T> queryWrapper);

	/**
	 * 查询满足条件的多条数据
	 * @param queryWrapper where 条件包装器
	 * @return List<T>
	 */
	List<T> selectList(@Param("wrapper") QueryWrapper<T> queryWrapper);

	/**
	 * 查询满足条件的总记录数
	 * @param queryWrapper where 条件包装器
	 * @return Integer
	 */
	Integer selectCount(@Param("wrapper") QueryWrapper<T> queryWrapper);

}
