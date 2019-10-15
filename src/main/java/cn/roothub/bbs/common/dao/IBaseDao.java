package cn.roothub.bbs.common.dao;

import cn.roothub.bbs.common.dao.wrapper.query.QueryWrapper;
import cn.roothub.bbs.common.model.IBaseModel;
import org.apache.ibatis.annotations.Param;
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
     * 查询满足条件的一条数据
     * @param queryWrapper
     * @return T
     */
    T selectOne(@Param("wrapper") QueryWrapper queryWrapper);



    /**
     * 单个查询
     * @param primaryKey
     * @return
     */
    IBaseModel findByPrimaryKey(@Param("primaryKey") Integer primaryKey);

    /**
     * lsit查询
     * @param start
     * @param limit
     * @param <T>
     * @return
     */
    <T extends IBaseModel> List<T> findList(@Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 添加
     * @param t
     * @param <T>
     * @return
     */
    <T extends IBaseModel> int insert(T t);

    /**
     * 更新
     * @param t
     * @param <T>
     * @return
     */
    <T extends IBaseModel> int update(T t);

    /**
     * 单个删除
     * @param primaryKey
     * @return
     */
    int deleteByPrimaryKey(@Param("primaryKey") Integer primaryKey);

    /**
     * 统计
     * @return
     */
    int count();

}
