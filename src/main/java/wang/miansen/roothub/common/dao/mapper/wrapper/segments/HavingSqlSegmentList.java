package wang.miansen.roothub.common.dao.mapper.wrapper.segments;

import wang.miansen.roothub.common.dao.mapper.enums.SqlKeyword;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: miansen.wang
 * @Date: 2019/10/4 17:31
 */
public class HavingSqlSegmentList extends AbstractSqlSegmentList{

    @Override
    protected boolean transformList(List<ISqlSegment> sqlSegmentList) {
        // 先把 "HAVING" 删了，否则会重复
        sqlSegmentList.remove(0);
        return true;
    }

    // 因为前面删了 "HAVING"，所以取的时候再拼上
    @Override
    public String getSqlSegment() {
        return this.isEmpty() ? EMPTY : this.stream().map(ISqlSegment::getSqlSegment).collect(Collectors.joining(SPACE, SPACE + SqlKeyword.HAVING.getSqlSegment() + SPACE,EMPTY));
    }
}
