package cn.roothub.bbs.common.dao.injector;

import cn.roothub.bbs.common.dao.injector.methods.AbstractMethod;
import cn.roothub.bbs.common.dao.injector.methods.SelectOne;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 默认的 SQL 注入器，提供了基本的 SQL 注入
 */
public class AutoSqlInjector extends AbstractSqlInjector{

    @Override
    public List<AbstractMethod> getMethodList() {
        return Stream.of(
                new SelectOne()
        ).collect(Collectors.toList());
    }
}
