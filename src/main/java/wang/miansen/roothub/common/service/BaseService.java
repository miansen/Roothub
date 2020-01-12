package wang.miansen.roothub.common.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import wang.miansen.roothub.common.entity.BaseDO;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.dao.mapper.wrapper.update.UpdateWrapper;

/**
 * 该接口作为 Service 层的基础接口，定义了常用的业务增删改查方法，建议大部分的 Service 层接口继承。
 * <p>注意：要继承该接口，对应的 {@code DO} 要继承 {@link BaseDO}，{@code DTO} 要继承 {@link BaseDTO}。
 * 
 * @param <DO> 数据库表映射实体类的类型
 * @param <DTO> 数据传输的类型
 * 
 * @author: miansen.wang
 * @date: 2019-12-29
 * @since 3.0
 */
public interface BaseService<DO extends BaseDO, DTO extends BaseDTO> {

	/**
	 * 插入一条数据
	 * @param dto 数据传输对象
	 * @return boolean
	 */
	boolean save(DTO dto);

	/**
	 * 批量插入多条数据
	 * @param dtoList 数据传输对象集合
	 * @return boolean
	 */
	boolean saveBatch(Collection<DTO> dtoList);

	/**
	 * 删除满足条件的数据
	 * @param updateWrapper 条件包装器
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
	 * @param idList 主键 ID 集合
	 * @return boolean
	 */
	boolean removeBatchIds(Collection<? extends Serializable> idList);

	/**
	 * 更新满足条件的数据
	 * @param updateWrapper 条件包装器
	 * @return boolean
	 */
	boolean update(DTO dto, UpdateWrapper<DO> updateWrapper);

	/**
	 * 根据主键 ID 更新一条数据
	 * @param dto 数据传输对象
	 * @return boolean
	 */
	boolean updateById(DTO dto);

	/**
	 * 根据主键 ID 集合，批量更新多条数据
	 * @param dtoList 数据传输对象集合
	 * @return boolean
	 */
	boolean updateBatchIds(Collection<DTO> dtoList);

	/**
	 * 根据主键 ID 查询一条数据
	 * @param id 主键 ID
	 * @return DTO
	 */
	DTO getById(Serializable id);

	/**
	 * 查询满足条件的一条数据
	 * @param queryWrapper 条件包装器
	 * @return DTO
	 */
	DTO getOne(QueryWrapper<DO> queryWrapper);

	/**
	 * 查询总记录数
	 * @return Integer
	 */
	Integer count();

	/**
	 * 查询满足条件的记录数
	 * @param queryWrapper 条件包装器
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
	 * @param queryWrapper 条件包装器
	 * @return List
	 */
	List<DTO> list(QueryWrapper<DO> queryWrapper);

	/**
	 * 根据主键 ID 集合，批量查询多条数据
	 * @param idList 主键 ID 集合
	 * @return List
	 */
	List<DTO> listBatchIds(Collection<? extends Serializable> idList);
	
	/**
	 * 查询所有的数据并翻页
	 * @param pageNumber 当前的页数
	 * @param pageSize 每页显示多少条数据量
	 * @return PageDataBody
	 */
	Page<DTO> page(Integer pageNumber, Integer pageSize);
	
	/**
	 * 查询满足条件的多条数据并翻页
	 * @param pageNumber 当前的页数
	 * @param pageSize 每页显示多少条数据量
	 * @param queryWrapper 条件包装器
	 * @return PageDataBody
	 */
	Page<DTO> page(Integer pageNumber, Integer pageSize, QueryWrapper<DO> queryWrapper);
	
	/**
	 * 获取 DTO 转换 DO 的函数
	 * @return
	 */
	Function<? super DTO, ? extends DO> getDTO2DOMapper();
	
	/**
	 * 取 DO 转换 DTO 的函数
	 * @return
	 */
	Function<? super DO, ? extends DTO> getDO2DTOMapper();
	
	/**
	 * 获取数据访问对象
	 * @return
	 */
	BaseDao<DO> getDao();
	
}
