package wang.miansen.roothub.common.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import wang.miansen.roothub.common.dao.mapper.BaseMapper;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.dao.mapper.wrapper.update.UpdateWrapper;
import wang.miansen.roothub.common.service.BaseService;

/**
 * 该类是 Service impl 层的基础父类，实现了常用的业务增删改查方法，建议大部分的 Service impl 层继承。
 * <p>继承该类后即可拥有简单的 CRUD 能力。
 * 
 * @param <M>  mapper 对象
 * @param <T> 数据库表映射实体类
 * 
 * @author miansen.wang
 * @date 2019-12-29
 * @since 3.0
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> implements BaseService<M, T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected M baseMapper;

	@Override
	public boolean save(T entity) {
		return retBool(baseMapper.insert(entity));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean saveBatch(Collection<T> entityList) {
		entityList.forEach(entity -> save(entity));
		return true;
	}

	@Override
	public boolean remove(UpdateWrapper<T> updateWrapper) {
		return retBool(baseMapper.delete(updateWrapper));
	}

	@Override
	public boolean removeById(Serializable id) {
		return retBool(baseMapper.deleteById(id));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean removeBatchIds(Collection<? extends Serializable> ids) {
		ids.forEach(id -> removeById(id));
		return true;
	}

	@Override
	public boolean update(T entity, UpdateWrapper<T> updateWrapper) {
		return retBool(baseMapper.update(entity, updateWrapper));
	}

	@Override
	public boolean updateById(T entity) {
		return retBool(baseMapper.updateById(entity));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateBatchIds(Collection<T> entityList) {
		entityList.forEach(entity -> updateById(entity));
		return true;
	}

	@Override
	public T getById(Serializable id) {
		return baseMapper.selectById(id);
	}

	@Override
	public T getOne(QueryWrapper<T> queryWrapper) {
		return baseMapper.selectOne(queryWrapper);
	}

	@Override
	public Integer count() {
		return baseMapper.selectCount(null);
	}

	@Override
	public Integer count(QueryWrapper<T> queryWrapper) {
		return baseMapper.selectCount(queryWrapper);
	}

	@Override
	public List<T> list() {
		return baseMapper.selectList(null);
	}

	@Override
	public List<T> list(QueryWrapper<T> queryWrapper) {
		return baseMapper.selectList(queryWrapper);
	}

	@Override
	public List<T> listBatchIds(Collection<? extends Serializable> ids) {
		return baseMapper.selectBatchIds(ids);
	}

	@Override
	public M getBaseMapper() {
		return baseMapper;
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