package cn.roothub.bbs.common.dao.wrapper.segments;

import cn.roothub.bbs.common.dao.enums.MatchSegment;
import cn.roothub.bbs.common.dao.enums.SqlKeyword;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 正常的 SQL 片段集合
 * <p>可以理解为只有 AND 、OR 、EXISTS 、NOT EXISTS 这些简单的连接词
 * <p>没有 GROUP BY 、HAVING 、ORDER BY 等复杂的关键词
 *
 * @Author: miansen.wang
 * @Date: 2019/9/15 16:12
 */
public class NormalSqlSegmentList extends AbstractSqlSegmentList {

    /**
     * 根据第一个和最后一个 SQL 片段判断是否需要 AND 连接
     * @param sqlSegmentList
     * @return boolean
     */
    @Override
    protected boolean transformList(List<ISqlSegment> sqlSegmentList) {
        ISqlSegment firstSqlSegment = sqlSegmentList.get(0);
        if (lastSqlSegment != null && !MatchSegment.ADN_OR.match(lastSqlSegment) && !MatchSegment.ADN_OR.match(firstSqlSegment)) {
            // 如果第一个和最后一个 SQL 片段不是 "AND" 或者 "OR"，则默认用 AND 连接
            super.add(SqlKeyword.AND);
        }
        return true;
    }

    /**
     * 返回拼接好的 SQL 语句
     * @return
     */
    @Override
    public String getSqlSegment() {
        return this.stream().map(ISqlSegment::getSqlSegment).collect(Collectors.joining(" "));
    }
}
