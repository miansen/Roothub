package wang.miansen.roothub.common.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.transaction.annotation.Transactional;

import wang.miansen.roothub.common.entity.BaseDO;
import wang.miansen.roothub.common.dto.BaseDTO;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.dao.mapper.wrapper.update.UpdateWrapper;

/**
 * 该类是 Service impl 层的基础父类，实现了常用的业务增删改查方法，建议大部分的 Service impl 层继承。
 * <p>继承该类后即可拥有简单的 CRUD 能力。
 * 
 * @param <D> 数据访问层的类型
 * @param <T> 数据库表映射实体类的类型
 * 
 * @author miansen.wang
 * @date 2019-12-29
 * @since 3.0
 */
public abstract class AbstractBaseServiceImpl<DO extends BaseDO, DTO extends BaseDTO> implements BaseService<DO, DTO> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean save(DTO entity) {
		return retBool(getBaseDao().insert(getBaseDO(entity)));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean saveBatch(Collection<DTO> entityList) {
		entityList.forEach(entity -> save(entity));
		return true;
	}

	@Override
	public boolean remove(UpdateWrapper<DO> updateWrapper) {
		return retBool(getBaseDao().delete(updateWrapper));
	}

	@Override
	public boolean removeById(Serializable id) {
		return retBool(getBaseDao().deleteById(id));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean removeBatchIds(Collection<? extends Serializable> ids) {
		ids.forEach(id -> removeById(id));
		return true;
	}

	@Override
	public boolean update(DTO dto, UpdateWrapper<DO> updateWrapper) {
		return retBool(getBaseDao().update(getBaseDO(dto), updateWrapper));
	}

	@Override
	public boolean updateById(DTO dto) {
		return retBool(getBaseDao().updateById(getBaseDO(dto)));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateBatchIds(Collection<DTO> dtoList) {
		dtoList.forEach(dto -> updateById(dto));
		return true;
	}

	@Override
	public DTO getById(Serializable id) {
		return getBaseDTO(getBaseDao().selectById(id));
	}

	@Override
	public DTO getOne(QueryWrapper<DO> queryWrapper) {
		return getBaseDTO(getBaseDao().selectOne(queryWrapper));
	}

	@Override
	public Integer count() {
		return getBaseDao().selectCount(null);
	}

	@Override
	public Integer count(QueryWrapper<DO> queryWrapper) {
		return getBaseDao().selectCount(queryWrapper);
	}

	@Override
	public List<DTO> list() {
		return getBaseDao().selectList(null).stream().map(entity -> getBaseDTO(entity)).collect(Collectors.toList());
	}

	@Override
	public List<DTO> list(QueryWrapper<DO> queryWrapper) {
		return getBaseDao().selectList(queryWrapper).stream().map(entity -> getBaseDTO(entity))
				.collect(Collectors.toList());
	}

	@Override
	public List<DTO> listBatchIds(Collection<? extends Serializable> ids) {
		return getBaseDao().selectBatchIds(ids).stream().map(entity -> getBaseDTO(entity)).collect(Collectors.toList());
	}

	/**
	 * 判断数据库操作是否成功
	 * @param result  数据库操作返回影响条数
	 * @return boolean
	 */
	private static boolean retBool(Integer result) {
		return result != null && result >= 1;
	}

}