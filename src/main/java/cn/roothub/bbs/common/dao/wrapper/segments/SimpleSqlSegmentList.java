package cn.roothub.bbs.common.dao.wrapper.segments;

import cn.roothub.bbs.common.dao.wrapper.ISqlSegment;
import java.util.stream.Collectors;

/**
 * 简单的 SQL 片段集合
 * <p>可以理解为只有 AND 、OR 、EXISTS 、NOT EXISTS 这些简单的连接词
 * <p>没有 GROUP BY 、HAVING 、ORDER BY 等复杂的关键词
 *
 * @Author: miansen.wang
 * @Date: 2019/9/15 16:12
 */
public class SimpleSqlSegmentList extends AbstractSqlSegmentList {

    @Override
    public String getSqlSegment() {
        return this.stream().map(ISqlSegment::getSqlSegment).collect(Collectors.joining(" "));
    }
}
