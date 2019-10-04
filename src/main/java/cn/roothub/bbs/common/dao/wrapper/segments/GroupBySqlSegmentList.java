package cn.roothub.bbs.common.dao.wrapper.segments;

import cn.roothub.bbs.common.dao.enums.SqlKeyword;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: miansen.wang
 * @Date: 2019/10/4 15:53
 */
public class GroupBySqlSegmentList extends AbstractSqlSegmentList{

    @Override
    protected boolean transformList(List<ISqlSegment> sqlSegmentList) {
        // 先把 "GROUP BY" 删了，否则会重复
        sqlSegmentList.remove(0);
        return true;
    }

    // 因为前面删了 "GROUP BY"，所以取的时候再拼上
    @Override
    public String getSqlSegment() {
        return this.isEmpty() ? "" : this.stream().map(ISqlSegment::getSqlSegment).collect(Collectors.joining(",", " " + SqlKeyword.GROUP_BY.getSqlSegment() + " ", ""));
    }
}
