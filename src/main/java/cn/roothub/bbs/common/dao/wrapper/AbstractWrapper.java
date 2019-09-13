package cn.roothub.bbs.common.dao.wrapper;

import cn.roothub.bbs.common.dao.wrapper.conditions.Compare;
import cn.roothub.bbs.common.dao.wrapper.conditions.Func;
import cn.roothub.bbs.common.dao.wrapper.conditions.Join;
import java.util.Map;

/**
 * @param <T> 数据库表映射实体类
 * @param <R> 返回结果
 * @param <K> 实体类属性
 * @param <V> 实体类属性的值
 *
 * @Author: miansen.wang
 * @Date: 2019/9/12 10:04
 */
public abstract class AbstractWrapper<T extends AbstractWrapper<T, R, K, V>, R, K, V> implements Compare<R, K, V>, Join<R>, Func<R, K, V>, ISqlSegment{

    /**
     * 数据库表映射实体类
     */
    protected T model;

    /**
     * 实体类型
     */
    protected Class<T> modelClass;

    /**
     * K: 实体类属性名
     * V: 实体类属性值
     */
    protected Map<String, Object> paramNameValuePairs;

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public Class<T> getModelClass() {
        return modelClass;
    }

    public void setModelClass(Class<T> modelClass) {
        this.modelClass = modelClass;
    }
}
