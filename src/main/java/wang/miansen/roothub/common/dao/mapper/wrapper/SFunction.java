package wang.miansen.roothub.common.dao.mapper.wrapper;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 支持序列化的 Function
 *
 * @author miansen.wang
 * @date 2021-04-12 21:09
 */
@FunctionalInterface
public interface SFunction<T, R> extends Function<T, R>, Serializable {
}
