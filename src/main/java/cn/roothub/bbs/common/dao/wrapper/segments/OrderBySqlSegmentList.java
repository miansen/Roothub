package cn.roothub.bbs.common.dao.wrapper.segments;

import cn.roothub.bbs.common.dao.wrapper.ISqlSegment;
import java.util.List;

/**
 * @Author: miansen.wang
 * @Date: 2019/9/23 21:13
 */
public class OrderBySqlSegmentList extends AbstractSqlSegmentList{

    @Override
    protected boolean transformList(List<ISqlSegment> sqlSegmentList) {
        return false;
    }

    @Override
    public String getSqlSegment() {
        return null;
    }
}
