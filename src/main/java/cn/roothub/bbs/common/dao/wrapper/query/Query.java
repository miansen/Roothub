package cn.roothub.bbs.common.dao.wrapper.query;

import cn.roothub.bbs.common.dao.metadata.TableFieldInfo;
import java.io.Serializable;
import java.util.function.Predicate;

/**
 * 查询包装器的接口
 * Children: 查询包装器
 * T: 实体类
 * R: 两种方式，String 或者 函数
 * @Author: miansen.wang
 * @Date: 2019/9/10 22:43
 */
public interface Query<Children, T, R> extends Serializable{

    /**
     *  设置查询字段
     * @param columns 字段数组
     * @return 子类
     */
    Children select(R... columns);

    /**
     * 过滤查询条件
     * 注意：当查询包装器里面的 modelClass 字段初始化后才能用此方法，否则只能用下面的重载方法
     * @param predicate
     * @return
     */
    Children select(Predicate<TableFieldInfo> predicate);

    /**
     * 过滤查询条件
     * 当查询包装器里面的 modelClass 字段没有初始化时，可以使用此方法
     * @param modelClass
     * @param predicate
     * @return
     */
    Children select(Class<T> modelClass, Predicate<TableFieldInfo> predicate);
}
