package wang.miansen.roothub.common.dao.mapper.wrapper.segments;

import wang.miansen.roothub.common.dao.mapper.enums.SqlKeyword;

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
        // 如果不为空，则说明已经有 "GROUP BY" 语句了，所以用 "," 分割（GROUP BY id , name , age ...）
        if (!this.isEmpty()) {
            super.add(() -> ",");
        }
        return true;
    }

    // 因为前面删了 "GROUP BY"，所以取的时候再拼上
    @Override
    public String getSqlSegment() {
        return this.isEmpty() ? EMPTY : this.stream().map(ISqlSegment::getSqlSegment).collect(Collectors.joining(SPACE, SPACE + SqlKeyword.GROUP_BY.getSqlSegment() + SPACE, EMPTY));
    }
}
