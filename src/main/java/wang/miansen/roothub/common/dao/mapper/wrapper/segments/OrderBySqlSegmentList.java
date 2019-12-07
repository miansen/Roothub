package wang.miansen.roothub.common.dao.mapper.wrapper.segments;

import wang.miansen.roothub.common.dao.mapper.enums.SqlKeyword;

import java.util.List;
import java.util.stream.Collectors;

/**
 * OrderBy
 * @Author: miansen.wang
 * @Date: 2019/9/23 21:13
 */
public class OrderBySqlSegmentList extends AbstractSqlSegmentList{

    @Override
    protected boolean transformList(List<ISqlSegment> sqlSegmentList) {
        // 先把 "ORDER BY" 删了，否则会重复
        sqlSegmentList.remove(0);
        // 如果不为空，则说明已经有 "order by" 语句了，所以用 "," 分割（order by id desc, name desc, age asc ...）
        if (!this.isEmpty()) {
            super.add(() -> ",");
        }
        return true;
    }

    // 因为前面删了 "ORDER BY"，所以取的时候再拼上
    @Override
    public String getSqlSegment() {
        return this.isEmpty() ? EMPTY : this.stream().map(ISqlSegment::getSqlSegment).collect(Collectors.joining(SPACE, SPACE + SqlKeyword.ORDER_BY.getSqlSegment() + SPACE, EMPTY));
    }
}
