package wang.miansen.roothub.common.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import wang.miansen.roothub.common.entity.BaseDO;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.dao.mapper.wrapper.update.UpdateWrapper;

/**
 * 该接口作为 Service 层的基础接口，定义了常用的业务增删改查方法，建议大部分的 Service 层接口继承。
 * 
 * @param <T> 数据访问层的类型
 * @param <T> 数据库表映射实体类的类型
 * 
 * @author: miansen.wang
 * @date: 2019-12-29
 * @since 3.0
 */
public interface BaseService<DO extends BaseDO, DTO extends BaseDTO> {

	/**
	 * 插入一条数据
	 * @param dto 实体对象
	 * @return boolean
	 */
	boolean save(DTO dto);

	/**
	 * 批量插入多条数据
	 * @param entityList 实体对象集合
	 * @return boolean
	 */
	boolean saveBatch(Collection<DTO> entityList);

	/**
	 * 删除满足条件的数据
	 * @param updateWrapper where 条件包装器
	 * @return boolean
	 */
	boolean remove(UpdateWrapper<DO> updateWrapper);

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
	boolean update(DTO entity, UpdateWrapper<DO> updateWrapper);

	/**
	 * 根据 ID 更新一条数据
	 * @param entity 实体对象
	 * @return boolean
	 */
	boolean updateById(DTO entity);

	/**
	 * 根据 ID 集合，批量更新多条数据
	 * @param entityList 实体对象集合
	 * @return boolean
	 */
	boolean updateBatchIds(Collection<DTO> entityList);

	/**
	 * 根据 ID 查询一条数据
	 * @param id 主键 ID
	 * @return T
	 */
	DTO getById(Serializable id);

	/**
	 * 查询满足条件的一条数据
	 * @param queryWrapper where 条件包装器
	 * @return T
	 */
	DTO getOne(QueryWrapper<DO> queryWrapper);

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
	Integer count(QueryWrapper<DO> queryWrapper);

	/**
	 * 查询所有的数据
	 * @return List
	 */
	List<DTO> list();

	/**
	 * 查询满足条件的多条数据
	 * @param queryWrapper where 条件包装器
	 * @return List
	 */
	List<DTO> list(QueryWrapper<DO> queryWrapper);

	/**
	 * 根据 ID 集合，批量查询多条数据
	 * @param ids 主键 ID 集合
	 * @return List
	 */
	List<DTO> listBatchIds(Collection<? extends Serializable> ids);
	
	/**
	 * 获取数据库表映射的实体类
	 * @param dto
	 * @return
	 */
	DO getBaseDO(DTO dto);
	
	/**
	 * 获取数据访问层
	 * @return
	 */
	BaseDao<DO> getBaseDao();
	
	DTO getBaseDTO(DO entity);
	
}
