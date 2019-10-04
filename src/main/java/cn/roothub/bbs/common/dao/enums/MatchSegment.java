package cn.roothub.bbs.common.dao.enums;

import cn.roothub.bbs.common.dao.wrapper.segments.ISqlSegment;

import java.util.function.Predicate;

/**
 * 匹配 SqlKeyword
 * @Author: miansen.wang
 * @Date: 2019/9/23 21:34
 */
public enum MatchSegment {

    ADN_OR(i -> i == SqlKeyword.AND || i == SqlKeyword.OR);

    private final Predicate<ISqlSegment> predicate;

    private MatchSegment(Predicate<ISqlSegment> predicate) {
        this.predicate = predicate;
    }

    public boolean match(ISqlSegment sqlSegment) {
        return predicate.test(sqlSegment);
    }


}
