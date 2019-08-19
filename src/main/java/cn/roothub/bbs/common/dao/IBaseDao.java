package cn.roothub.bbs.common.dao;

import cn.roothub.bbs.common.model.IBaseModel;

import java.util.List;

/**
 * @Author: miansen.wang
 * @Date: 2019/8/19 22:44
 * 该接口作为Dao层的基础接口，定义了常用的数据库增删改查方法，建议大部分的Dao层接口继承
 */
public interface IBaseDao {

    /**
     * 根据主键查询Model
     * @return
     */
    IBaseModel findByPrimaryKey(Integer PrimaryKey);

    /**
     * 查询List
     * @return
     */
    List<IBaseModel> findList();

}
