package cn.roothub.bbs.common.dao;

import cn.roothub.bbs.common.dao.wrapper.query.QueryWrapper;
import cn.roothub.bbs.common.model.IBaseModel;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 该接口作为 Dao 层的基础接口，定义了常用的数据库增删改查方法，建议大部分的 Dao 层接口继承
 *
 * @param <T> 数据库表映射实体类
 *
 * @Author: miansen.wang
 * @Date: 2019/8/19 22:44
 */
public interface IBaseDao<T extends IBaseModel> {

    /**
     * 根据 ID 查询一条数据
     * @param id
     * @return
     */
    T selectById(Serializable id);

    /**
     * 根据 ID 集合，批量查询多条数据
     * @param idList
     * @return
     */
    List<T> selectBatchIds(@Param("coll") Collection<? extends Serializable> idList);

    /**
     * 查询满足条件的一条数据
     * @param queryWrapper
     * @return T
     */
    T selectOne(@Param("wrapper") QueryWrapper<T> queryWrapper);

    /**
     * 查询满足条件的多条数据
     * @param queryWrapper
     * @return
     */
    List<T> selectList(@Param("wrapper") QueryWrapper<T> queryWrapper);

    /**
     * 查询满足条件的总记录数
     * @param queryWrapper
     * @return
     */
    Integer selectCount(@Param("wrapper") QueryWrapper<T> queryWrapper);

}
