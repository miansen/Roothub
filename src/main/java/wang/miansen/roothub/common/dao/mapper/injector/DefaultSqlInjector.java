package wang.miansen.roothub.common.dao.mapper.injector;

import wang.miansen.roothub.common.dao.mapper.injector.methods.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 默认的 SQL 注入器，提供了基本的 SQL 注入
 */
public class DefaultSqlInjector extends AbstractSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList() {
        return Stream.of(
                new Insert(),
                new Delete(),
                new DeleteById(),
                new DeleteBatchIds(),
                new Update(),
                new UpdateById(),
                new SelectOne(),
                new SelectList(),
                new SelectById(),
                new SelectBatchIds(),
                new SelectCount()
        ).collect(Collectors.toList());
    }
}
