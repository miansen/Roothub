package wang.miansen.roothub.common.dao.mapper.wrapper;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.ibatis.reflection.property.PropertyNamer;

import wang.miansen.roothub.common.dao.mapper.util.StringPool;
import wang.miansen.roothub.common.util.LambdaUtils;
import wang.miansen.roothub.common.util.StringUtils;

/**
 * Lambda 条件包装器的抽象父类
 *
 * @param <T> 实体类的类型
 * @param <R> 返回结果的类型
 * @author miansen.wang
 * @date 2021-04-13 17:21
 */
public abstract class AbstractLambdaWrapper<T, R extends AbstractLambdaWrapper<T, R>> extends
    AbstractWrapper<T, R, SFunction<T, ?>, Object> {

    @Override
    protected String columnToString(SFunction<T, ?> column) {
        return StringUtils.camelToUnderline(PropertyNamer.methodToProperty(LambdaUtils.resolve(column).getImplMethodName()));
    }

    @Override
    protected String columnsToString(SFunction<T, ?>... columns) {
        return Arrays.stream(columns).map(this::columnToString).collect(Collectors.joining(StringPool.COMMA));
    }
}
