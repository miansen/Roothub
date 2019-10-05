package cn.roothub.bbs.common.dao.wrapper.segments;

import cn.roothub.bbs.common.dao.enums.MatchSegment;

import java.util.Arrays;
import java.util.List;

/**
 * sql 片段生成器，封装了 add 和 get 方法
 * @Author: miansen.wang
 * @Date: 2019/10/3 17:29
 */
public class SqlSegmentBuilder implements ISqlSegment{

    private final NormalSqlSegmentList normal = new NormalSqlSegmentList();

    private final GroupBySqlSegmentList groupBy = new GroupBySqlSegmentList();

    private final HavingSqlSegmentList having = new HavingSqlSegmentList();

    private final OrderBySqlSegmentList orderBy = new OrderBySqlSegmentList();

    /**
     * 根据 sql 片段数组的第一个元素，匹配到对应的容器
     * @param sqlSegments
     */
    public void add(ISqlSegment... sqlSegments) {
        List<ISqlSegment> sqlSegmentList = Arrays.asList(sqlSegments);
        ISqlSegment firstSqlSegment = sqlSegmentList.get(0);
        if (MatchSegment.ORDER_BY.match(firstSqlSegment)) {
            orderBy.addAll(sqlSegmentList);
        } else if (MatchSegment.HAVING.match(firstSqlSegment)) {
            having.addAll(sqlSegmentList);
        } else if (MatchSegment.GROUP_BY.match(firstSqlSegment)) {
            groupBy.addAll(sqlSegmentList);
        } else {
            normal.addAll(sqlSegmentList);
        }
    }

    /**
     * 组装各个容器的 sql 片段
     * @return
     */
    @Override
    public String getSqlSegment() {
        return normal.getSqlSegment() + groupBy.getSqlSegment() + having.getSqlSegment() + orderBy.getSqlSegment();
    }
}
