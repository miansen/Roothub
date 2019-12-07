package wang.miansen.roothub.common.dao.mapper.injector;

import wang.miansen.roothub.common.dao.mapper.injector.methods.AbstractMethod;
import wang.miansen.roothub.common.dao.mapper.util.ArrayUtils;

import org.apache.ibatis.builder.MapperBuilderAssistant;

import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;

/**
 * 抽象的 SQL 注入器，提供了 inspectInject() 这个基础方法，getMethodList() 方法由子类实现
 *
 * @Author: miansen.wang
 * @Date: 2019/8/30
 */
public abstract class AbstractSqlInjector implements ISqlInjector {

    /**
     * 根据 mapperClass 注入 SQL，需要检查 SQL 是否已注入(已经注入过不再注入)
     *
     * @param builderAssistant
     * @param mapperClass
     */
    @Override
    public void inspectInject(MapperBuilderAssistant builderAssistant, Class<?> mapperClass) {
        List<AbstractMethod> methodList = this.getMethodList();
        Assert.notEmpty(methodList);
        Class<?> modelClass = this.extractModelClass(mapperClass);
        methodList.forEach(m -> m.inject(builderAssistant, mapperClass, modelClass));
    }

    /**
     * 获取注入的方法
     *
     * @return 注入的方法集合
     */
    public abstract List<AbstractMethod> getMethodList();

    /**
     * 该方法的作用是提取 Mapper 的泛型模型。
     * 因为继承 IBaseDao 需要将 Mapper 对应的 model 添加到泛型里面，这时候我们需要将其提取出来，
     * 提取出来后还需要将其初始化成一个 TableInfo 对象，TableInfo 存储了数据库对应的 model 所有的信息，
     * 包括表主键 ID 类型、表名称、表字段信息列表等等信息，这些信息通过反射获取。
     *
     * @param mapperClass
     * @return
     */
    protected Class<?> extractModelClass(Class<?> mapperClass) {
        Type[] types = mapperClass.getGenericInterfaces();
        ParameterizedType target = null;
        Type[] var4 = types;
        int var5 = types.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            Type type = var4[var6];
            if (type instanceof ParameterizedType) {
                Type[] typeArray = ((ParameterizedType) type).getActualTypeArguments();
                if (ArrayUtils.isNotEmpty(typeArray)) {
                    int var10 = typeArray.length;
                    byte var11 = 0;
                    if (var11 < var10) {
                        Type t = typeArray[var11];
                        if (!(t instanceof TypeVariable) && !(t instanceof WildcardType)) {
                            target = (ParameterizedType) type;
                        }
                    }
                }
                break;
            }
        }

        return target == null ? null : (Class) target.getActualTypeArguments()[0];
    }
}
