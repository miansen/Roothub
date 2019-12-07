package cn.roothub.bbs.common.dao.mapper.enums;

import cn.roothub.bbs.common.dao.mapper.wrapper.segments.ISqlSegment;

import java.util.function.Predicate;

/**
 * 匹配 {@link SqlKeyword}
 * @Author: miansen.wang
 * @Date: 2019/9/23 21:34
 */
public enum MatchSegment {

    ADN(i -> i == SqlKeyword.AND),

    OR(i -> i == SqlKeyword.OR),

    ADN_OR(i -> i == SqlKeyword.AND || i == SqlKeyword.OR),

    NOT(i -> i == SqlKeyword.NOT),

    GROUP_BY(i -> i == SqlKeyword.GROUP_BY),

    ORDER_BY(i -> i == SqlKeyword.ORDER_BY),

    HAVING(i -> i == SqlKeyword.HAVING),

    EXISTS(i -> i == SqlKeyword.EXISTS);

    private final Predicate<ISqlSegment> predicate;

    private MatchSegment(Predicate<ISqlSegment> predicate) {
        this.predicate = predicate;
    }

    /**
     * 传入一个 SQL 片段，返回是否匹配到该片段。
     * @param sqlSegment
     * @return
     */
    public boolean match(ISqlSegment sqlSegment) {
        return predicate.test(sqlSegment);
    }

}
