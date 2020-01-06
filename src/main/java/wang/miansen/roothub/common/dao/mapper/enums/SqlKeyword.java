package wang.miansen.roothub.common.dao.mapper.enums;

import wang.miansen.roothub.common.dao.mapper.wrapper.segments.ISqlSegment;

/**
 * 常用的 SQL 片段
 * @Author: miansen.wang
 * @Date: 2019/9/15 16:07
 */
public enum SqlKeyword implements ISqlSegment {

    AND("AND"),
    OR("OR"),
    IN("IN"),
    NOT("NOT"),
    LIKE("LIKE"),
    EQ("="),
    NE("<>"),
    GT(">"),
    GE(">="),
    LT("<"),
    LE("<="),
    IS_NULL("IS NULL"),
    IS_NOT_NULL("IS NOT NULL"),
    GROUP_BY("GROUP BY"),
    HAVING("HAVING"),
    ORDER_BY("ORDER BY"),
    EXISTS("EXISTS"),
    BETWEEN("BETWEEN"),
    ASC("ASC"),
    DESC("DESC"),
    LIMIT("LIMIT");

    private String keyword;

    SqlKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String getSqlSegment() {
        return this.keyword;
    }
}
